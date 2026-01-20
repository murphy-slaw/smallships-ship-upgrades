package net.funkpla.smallships_upgrades.item;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.funkpla.smallships_upgrades.CommonClass;
import net.funkpla.smallships_upgrades.Constants;
import net.funkpla.smallships_upgrades.UpgradeType;
import net.minecraft.world.item.Item;

public class ModItems {
  private static final Map<UpgradeType, Supplier<Item>> upgradeToItem = new HashMap<>(3);
  public static final Supplier<Item> SPEED_UPGRADE =
      register("speed_upgrade", () -> new SpeedUpgradeItem(new Item.Properties().stacksTo(16)));
  public static final Supplier<Item> CARGO_UPGRADE =
      register("cargo_upgrade", () -> new CargoUpgradeItem(new Item.Properties().stacksTo(16)));
  public static final Supplier<Item> HEALTH_UPGRADE =
      register("health_upgrade", () -> new HealthUpgradeItem(new Item.Properties().stacksTo(16)));

  static {
    upgradeToItem.put(UpgradeType.SPEED, SPEED_UPGRADE);
    upgradeToItem.put(UpgradeType.CARGO, CARGO_UPGRADE);
    upgradeToItem.put(UpgradeType.HEALTH, HEALTH_UPGRADE);
  }

  public static Supplier<Item> register(String id, Supplier<Item> item) {
    return CommonClass.ITEMS.register(id, item);
  }

  public static void init() {
    Constants.LOG.info("Loaded items.");
  }

  public static Supplier<Item> getItemForUpgrade(UpgradeType type){
      return upgradeToItem.get(type);
  }
}
