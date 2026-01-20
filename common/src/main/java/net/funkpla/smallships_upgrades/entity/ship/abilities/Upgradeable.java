package net.funkpla.smallships_upgrades.entity.ship.abilities;

import static com.talhanation.smallships.world.entity.ship.ContainerShip.CONTAINER_SIZE;
import static net.funkpla.smallships_upgrades.item.ModItems.getItemForUpgrade;

import com.talhanation.smallships.world.entity.ship.*;
import com.talhanation.smallships.world.entity.ship.abilities.Ability;
import java.util.Map;
import java.util.stream.Collectors;
import me.shedaniel.autoconfig.AutoConfig;
import net.funkpla.smallships_upgrades.UpgradeType;
import net.funkpla.smallships_upgrades.config.UpgradeConfig;
import net.funkpla.smallships_upgrades.config.UpgradeConfig.UpgradeCapsConfig;
import net.funkpla.smallships_upgrades.item.UpgradeItem;
import net.funkpla.smallships_upgrades.mixin.UpdatePagingInvoker;
import net.funkpla.smallships_upgrades.platform.services.ShipUpgradeAccessor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface Upgradeable extends Ability {
  UpgradeConfig config = AutoConfig.getConfigHolder(UpgradeConfig.class).getConfig();

  default boolean interactUpgrade(Player player, InteractionHand interactionHand) {
    ItemStack stack = player.getItemInHand(interactionHand);
    if (stack.getItem() instanceof UpgradeItem item) {
      ShipUpgradeAccessor upgrades = this.getUpgrades();

      if (upgrades.getUpgradeCount(item.getType()) >= getCap(item.getType())) {
        return true;
      }

      upgrades.addUpgrade(item.getType());
      if (!player.isCreative()) stack.shrink(1);

      var attributes = self().getAttributes();
      switch (item.getType()) {
        case SPEED:
          attributes.maxSpeed += config.speedIncrement;
          self().setData(Ship.ATTRIBUTES, attributes.getSaveData());
          break;
        case CARGO:
          if (self() instanceof ContainerShip container) {
            var containerSize = container.getContainerSize() + (config.cargoIncrement * 9);
            ((UpdatePagingInvoker) container).smallships_upgrades$invokeUpdatePaging(containerSize);
            container.setData(CONTAINER_SIZE, containerSize);
            container.resizeContainer(containerSize);
          }
          break;
        case HEALTH:
          attributes.maxHealth += config.healthIncrement;
          self().setData(Ship.ATTRIBUTES, attributes.getSaveData());
          break;
      }

      this.self()
          .level()
          .playSound(
              player,
              this.self().getX(),
              this.self().getY() + 4.0,
              this.self().getZ(),
              SoundEvents.NOTE_BLOCK_DIDGERIDOO.value(),
              this.self().getSoundSource(),
              15.0F,
              1.0F);
      return true;
    }
    return false;
  }

  default Map<String, UpgradeCapsConfig> getConfigMap() {
    return config.caps.stream()
        .collect(Collectors.toMap(UpgradeConfig.UpgradeCapsConfig::getName, entry -> entry));
  }

  default int getCap(UpgradeType type) {
    var config = getConfigMap().get(getShipType());
    if (config == null) return 0;
    switch (type) {
      case CARGO -> {
        return config.getCargo();
      }
      case HEALTH -> {
        return config.getHealth();
      }
      case SPEED -> {
        return config.getSpeed();
      }
    }
    return 0;
  }

  default String getShipType() {
    if (self() instanceof BriggEntity) return BriggEntity.ID;
    if (self() instanceof CogEntity) return CogEntity.ID;
    if (self() instanceof DrakkarEntity) return DrakkarEntity.ID;
    if (self() instanceof GalleyEntity) return GalleyEntity.ID;
    return "";
  }

  default void dropUpgrades() {
    ShipUpgradeAccessor upgrades = this.getUpgrades();
    for (var type : UpgradeType.values()) {
      var count = upgrades.getUpgradeCount(type);
      Containers.dropItemStack(
          self().level(),
          self().getX(),
          self().getY(),
          self().getZ(),
          new ItemStack(getItemForUpgrade(type).get(), getRecycledCount(count)));
    }
  }

  default int getRecycledCount(int count){
      return Math.round(count * (config.upgradeRecyclePercentage / 100f));
  }

  ShipUpgradeAccessor getUpgrades();
}
