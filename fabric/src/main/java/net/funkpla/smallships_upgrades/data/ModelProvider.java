package net.funkpla.smallships_upgrades.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.funkpla.smallships_upgrades.item.ModItems;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

public class ModelProvider extends FabricModelProvider {

  public ModelProvider(FabricDataOutput output) {
    super(output);
  }

  @Override
  public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {}

  @Override
  public void generateItemModels(ItemModelGenerators itemModelGenerators) {
    itemModelGenerators.generateFlatItem(ModItems.CARGO_UPGRADE.get(), ModelTemplates.FLAT_ITEM);
    itemModelGenerators.generateFlatItem(ModItems.SPEED_UPGRADE.get(), ModelTemplates.FLAT_ITEM);
    itemModelGenerators.generateFlatItem(ModItems.HEALTH_UPGRADE.get(), ModelTemplates.FLAT_ITEM);
  }
}
