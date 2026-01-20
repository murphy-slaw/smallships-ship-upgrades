package net.funkpla.smallships_upgrades.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.funkpla.smallships_upgrades.item.ModItemGroups;
import net.funkpla.smallships_upgrades.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;

public class LangProvider extends FabricLanguageProvider {
  protected LangProvider(FabricDataOutput dataOutput) {
    super(dataOutput);
  }

  @Override
  public void generateTranslations(TranslationBuilder translationBuilder) {
    translationBuilder.add(ModItems.SPEED_UPGRADE.get(), "Ship Speed Upgrade");
    translationBuilder.add(ModItems.CARGO_UPGRADE.get(), "Ship Cargo Upgrade");
    translationBuilder.add(ModItems.HEALTH_UPGRADE.get(), "Ship Damage Upgrade");
    translationBuilder.add(
        BuiltInRegistries.CREATIVE_MODE_TAB
            .getResourceKey(ModItemGroups.ITEMS.get())
            .orElseThrow(() -> new IllegalStateException("Item group not registered")),
        "Small Ships Upgrades");
    translationBuilder.add("text.autoconfig.smallships_upgrades.title", "Small Ships Upgrades");
    translationBuilder.add("text.autoconfig.smallships_upgrades.option.caps", "Upgrade Caps");
    translationBuilder.add(
        "text.autoconfig.smallships_upgrades.option.UpgradeCapsConfig", "Ship Caps");
    translationBuilder.add(
        "text.autoconfig.smallships_upgrades.option.UpgradeCapsConfig.name", "Ship Type");
    translationBuilder.add(
        "text.autoconfig.smallships_upgrades.option.UpgradeCapsConfig.speed",
        "Max Speed Upgrades");
    translationBuilder.add(
        "text.autoconfig.smallships_upgrades.option.UpgradeCapsConfig.health",
        "Max Health Upgrades");
    translationBuilder.add(
        "text.autoconfig.smallships_upgrades.option.UpgradeCapsConfig.cargo",
        "Max Cargo Upgrades");
    translationBuilder.add(
        "text.autoconfig.smallships_upgrades.option.upgradeRecyclePercentage",
        "Percentage of upgrades to drop on break");
  }
}
