package net.funkpla.smallships_upgrades;

import static net.funkpla.smallships_upgrades.Constants.MOD_ID;

import me.shedaniel.autoconfig.AutoConfig;
import net.funkpla.smallships_upgrades.config.UpgradeConfig;
import net.funkpla.smallships_upgrades.config.UpgradeConfigSerializer;
import net.funkpla.smallships_upgrades.item.ModItemGroups;
import net.funkpla.smallships_upgrades.item.ModItems;
import net.funkpla.smallships_upgrades.platform.registration.RegistrationProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class CommonClass {

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(BuiltInRegistries.ITEM, MOD_ID);
    public static final RegistrationProvider<CreativeModeTab> CREATIVE_MODE_TAB =
            RegistrationProvider.get(BuiltInRegistries.CREATIVE_MODE_TAB,
            MOD_ID);

    public static ResourceLocation locate(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static void init() {
        AutoConfig.register(UpgradeConfig.class, UpgradeConfigSerializer::new);
        ModItems.init();
        ModItemGroups.init();
    }
}