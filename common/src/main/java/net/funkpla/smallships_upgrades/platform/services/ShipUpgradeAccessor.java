package net.funkpla.smallships_upgrades.platform.services;

import net.funkpla.smallships_upgrades.UpgradeType;
import net.minecraft.world.entity.Entity;

public interface ShipUpgradeAccessor {
    ShipUpgradeAccessor from(Entity e);
    Integer getUpgradeCount(UpgradeType type);
    void addUpgrade(UpgradeType type);
    void removeUpgrade(UpgradeType type);
}
