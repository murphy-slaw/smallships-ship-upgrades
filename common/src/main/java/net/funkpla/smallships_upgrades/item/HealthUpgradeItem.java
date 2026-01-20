package net.funkpla.smallships_upgrades.item;

import net.funkpla.smallships_upgrades.UpgradeType;

public class HealthUpgradeItem extends UpgradeItem {
  public HealthUpgradeItem(Properties properties) {
    super(properties);
    type = UpgradeType.HEALTH;
  }
}
