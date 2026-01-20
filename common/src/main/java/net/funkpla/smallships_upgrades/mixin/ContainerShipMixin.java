package net.funkpla.smallships_upgrades.mixin;

import com.talhanation.smallships.world.entity.ship.ContainerShip;
import net.funkpla.smallships_upgrades.entity.ship.abilities.Upgradeable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerShip.class)
public abstract class ContainerShipMixin extends Entity implements Upgradeable {
  public ContainerShipMixin(EntityType<?> entityType, Level level) {
    super(entityType, level);
  }

  @Inject(
      at =
          @At(
              value = "INVOKE",
              target =
                  "Lnet/minecraft/world/Containers;dropContents(Lnet/minecraft/world/level/Level;"
                      + "Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/Container;)V",
              shift = At.Shift.AFTER),
      method = "remove")
  public void smallships_upgrades$dropUpgrades(RemovalReason removalReason, CallbackInfo ci) {
    this.dropUpgrades();
  }
}
