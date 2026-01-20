package net.funkpla.smallships_upgrades.mixin;

import com.talhanation.smallships.world.entity.ship.Ship;
import net.funkpla.smallships_upgrades.entity.ship.abilities.Upgradeable;
import net.funkpla.smallships_upgrades.platform.Services;
import net.funkpla.smallships_upgrades.platform.services.ShipUpgradeAccessor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Ship.class)
public abstract class ShipMixin extends Entity implements Upgradeable {

    public ShipMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    private final ShipUpgradeAccessor smallships_upgrades$upgradeAccessor =
            Services.SHIP_UPGRADE_ACCESSOR.from(this);

    @Unique
    public ShipUpgradeAccessor getUpgrades(){
        return this.smallships_upgrades$upgradeAccessor;
    }

    @Inject(at = @At(value="INVOKE", target="Lcom/talhanation/smallships/world/entity/ship/Ship;isLocked()Z", shift = At.Shift.AFTER),
            method="interact", cancellable = true)
    public void smallships_upgrades$interactUpgrade(Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir){
        if (this.interactUpgrade(player,interactionHand)){
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
