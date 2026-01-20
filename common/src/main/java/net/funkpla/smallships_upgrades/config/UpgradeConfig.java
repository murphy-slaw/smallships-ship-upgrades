package net.funkpla.smallships_upgrades.config;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.funkpla.smallships_upgrades.Constants;

@Config(name = Constants.MOD_ID)
public class UpgradeConfig implements ConfigData {
  @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
  public int upgradeRecyclePercentage = 100;
  @ConfigEntry.BoundedDiscrete(min=1, max=100)
  public int speedIncrement = 10;
  @ConfigEntry.BoundedDiscrete(min=1, max=200)
  public int healthIncrement = 20;
  @ConfigEntry.BoundedDiscrete(min=1, max=12)
  public int cargoIncrement = 6;
  public List<UpgradeCapsConfig> caps = new ArrayList<>();


    @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class UpgradeCapsConfig {
    private String name;
    private int speed;
    private int health;
    private int cargo;
  }
}
