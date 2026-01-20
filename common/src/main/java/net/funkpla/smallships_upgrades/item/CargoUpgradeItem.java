package net.funkpla.smallships_upgrades.item;

import net.funkpla.smallships_upgrades.UpgradeType;

public class CargoUpgradeItem extends UpgradeItem {
  public CargoUpgradeItem(Properties properties) {
    super(properties);
    type = UpgradeType.CARGO;
  }
}
