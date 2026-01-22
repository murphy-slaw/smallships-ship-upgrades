package net.funkpla.smallships_upgrades.capabiiity;

import net.funkpla.smallships_upgrades.platform.services.ShipUpgradeAccessor;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class UpgradeCapability {

    public static final Capability<IUpgradeCapability> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(ShipUpgradeAccessor.class);
    }

    private UpgradeCapability() {
    }
}
