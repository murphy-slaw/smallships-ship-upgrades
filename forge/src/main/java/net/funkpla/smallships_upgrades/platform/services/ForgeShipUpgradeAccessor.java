package net.funkpla.smallships_upgrades.platform.services;

import net.funkpla.smallships_upgrades.UpgradeType;
import net.funkpla.smallships_upgrades.capabiiity.IUpgradeCapability;
import net.funkpla.smallships_upgrades.capabiiity.UpgradeCapability;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

public class ForgeShipUpgradeAccessor implements ShipUpgradeAccessor {
  private LazyOptional<IUpgradeCapability> upgradeCapability;

  public ForgeShipUpgradeAccessor() {}

  public ForgeShipUpgradeAccessor(ICapabilityProvider entity) {
    upgradeCapability = entity.getCapability(UpgradeCapability.INSTANCE);
  }

  public ShipUpgradeAccessor from(Entity entity) {
    return new ForgeShipUpgradeAccessor(entity);
  }

  @Override
  public Integer getUpgradeCount(UpgradeType type) {
    return upgradeCapability.resolve().map(cap -> cap.getUpgradeCount(type)).orElse(0);
  }

  @Override
  public void addUpgrade(UpgradeType type) {
    upgradeCapability.resolve().ifPresent(cap -> cap.addUpgrade(type));
  }

  @Override
  public void removeUpgrade(UpgradeType type) {
    upgradeCapability.resolve().ifPresent(cap -> cap.removeUpgrade(type));
  }
}
