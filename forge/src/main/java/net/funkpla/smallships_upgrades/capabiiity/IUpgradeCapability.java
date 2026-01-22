package net.funkpla.smallships_upgrades.capabiiity;

import net.funkpla.smallships_upgrades.UpgradeType;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IUpgradeCapability extends INBTSerializable<CompoundTag> {
    Integer getUpgradeCount(UpgradeType type);
    void addUpgrade(UpgradeType type);
    void removeUpgrade(UpgradeType type);
    void setUpgradeCount(UpgradeType type, int count);
}
