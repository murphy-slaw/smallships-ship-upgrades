package net.funkpla.smallships_upgrades.mixin;

import com.talhanation.smallships.world.entity.ship.ContainerShip;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ContainerShip.class)
public interface UpdatePagingInvoker{
    @Invoker("updatePaging")
    void smallships_upgrades$invokeUpdatePaging(int containerSize);

}
