package net.funkpla.smallships_upgrades.item;

import net.funkpla.smallships_upgrades.CommonClass;
import net.funkpla.smallships_upgrades.Constants;
import net.funkpla.smallships_upgrades.platform.registration.RegistryObject;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModItemGroups {
  public static final RegistryObject<CreativeModeTab> ITEMS =
      register(
          "items",
          CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
              .title(Component.translatable("itemGroup." + Constants.MOD_ID + ".items"))
              .icon(() -> new ItemStack(ModItems.CARGO_UPGRADE.get()))
              .displayItems(
                  (_unused, entries) -> {
                    entries.accept(ModItems.CARGO_UPGRADE.get());
                    entries.accept(ModItems.SPEED_UPGRADE.get());
                    entries.accept(ModItems.HEALTH_UPGRADE.get());
                  })
              .build());

  private static RegistryObject<CreativeModeTab> register(String id, CreativeModeTab itemGroup) {
    return CommonClass.CREATIVE_MODE_TAB.register(id, () -> itemGroup);
  }

  public static void init() {
    Constants.LOG.info("Loading creative tabs");
  }
}
