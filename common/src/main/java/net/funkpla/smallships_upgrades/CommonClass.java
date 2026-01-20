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

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class CommonClass {

    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(BuiltInRegistries.ITEM, MOD_ID);
    public static final RegistrationProvider<CreativeModeTab> CREATIVE_MODE_TAB =
            RegistrationProvider.get(BuiltInRegistries.CREATIVE_MODE_TAB,
            MOD_ID);

    public static ResourceLocation locate(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static void init() {
        AutoConfig.register(UpgradeConfig.class, UpgradeConfigSerializer::new);
        ModItems.init();
        ModItemGroups.init();
    }
}