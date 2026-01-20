package net.funkpla.smallships_upgrades.components;

import static net.funkpla.smallships_upgrades.components.UpgradesComponent.SHIP_UPGRADES;
import com.talhanation.smallships.world.entity.ship.Ship;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;

public final class ComponentInitializer implements EntityComponentInitializer {
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(Ship.class, SHIP_UPGRADES, UpgradesComponent::new);
    }
}
