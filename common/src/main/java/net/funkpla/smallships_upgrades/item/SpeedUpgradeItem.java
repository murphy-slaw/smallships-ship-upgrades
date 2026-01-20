package net.funkpla.smallships_upgrades.item;

import net.funkpla.smallships_upgrades.UpgradeType;

public class SpeedUpgradeItem extends UpgradeItem {
  public SpeedUpgradeItem(Properties properties) {
    super(properties);
    type = UpgradeType.SPEED;
  }
}
