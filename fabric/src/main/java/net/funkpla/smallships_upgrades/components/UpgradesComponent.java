package net.funkpla.smallships_upgrades.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import java.util.HashMap;
import java.util.Map;
import net.funkpla.smallships_upgrades.CommonClass;
import net.funkpla.smallships_upgrades.UpgradeType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class UpgradesComponent implements AutoSyncedComponent {

  public static final ComponentKey<UpgradesComponent> SHIP_UPGRADES =
      ComponentRegistry.getOrCreate(CommonClass.locate("ship_upgrades"), UpgradesComponent.class);
  private final Entity provider;
  private final Map<UpgradeType, Integer> upgradeCounts = new HashMap<>();

  public UpgradesComponent(Entity e) {
    this.provider = e;
    for (UpgradeType type : UpgradeType.values()) {
      upgradeCounts.put(type, 0);
    }
  }

  public Integer getUpgradeCount(UpgradeType type) {
    return upgradeCounts.getOrDefault(type, 0);
  }

  public void addUpgrade(UpgradeType type) {
    upgradeCounts.put(type, getUpgradeCount(type) + 1);
    SHIP_UPGRADES.sync(this.provider);
  }

  public void removeUpgrade(UpgradeType type) {
    if (getUpgradeCount(type) > 0) {
      upgradeCounts.put(type, getUpgradeCount(type) - 1);
    }
  }

  public void setUpgradeCount(UpgradeType type, int count) {
    upgradeCounts.put(type, count);
  }

  @Override
  public void readFromNbt(CompoundTag tag) {
    CompoundTag upgrades = tag.getCompound("upgrades");
    for (UpgradeType type : UpgradeType.values()) {
      upgradeCounts.put(type, upgrades.getInt(type.toString()));
    }
  }

  @Override
  public void writeToNbt(@NotNull CompoundTag tag) {
    CompoundTag upgrades = new CompoundTag();
    for (UpgradeType type : UpgradeType.values()) {
      upgrades.putInt(type.toString(), upgradeCounts.get(type));
    }

    tag.put("upgrades", upgrades);
  }
}
