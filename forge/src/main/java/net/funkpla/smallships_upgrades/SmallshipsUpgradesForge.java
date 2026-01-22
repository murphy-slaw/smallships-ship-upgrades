package net.funkpla.smallships_upgrades;

import com.talhanation.smallships.world.entity.ship.Ship;
import net.funkpla.smallships_upgrades.capabiiity.IUpgradeCapability;
import net.funkpla.smallships_upgrades.capabiiity.UpgradeCapabilityAttacher;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class SmallshipsUpgradesForge {
    
    public SmallshipsUpgradesForge() {
    
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
    
        // Use Forge to bootstrap the Common mod.
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();
        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, this::onAttachingCapabilities);
    }
    @SubscribeEvent
    public void registerCaps(RegisterCapabilitiesEvent event) {
        event.register(IUpgradeCapability.class);
    }

    @SubscribeEvent
    public void onAttachingCapabilities(final AttachCapabilitiesEvent<Entity> event) {
        if (!(event.getObject() instanceof Ship))
            return;
        UpgradeCapabilityAttacher.attach(event);
    }
}