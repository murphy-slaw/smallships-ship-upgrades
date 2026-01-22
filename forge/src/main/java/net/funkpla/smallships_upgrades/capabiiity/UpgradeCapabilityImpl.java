package net.funkpla.smallships_upgrades.capabiiity;

import java.util.HashMap;
import java.util.Map;
import net.funkpla.smallships_upgrades.UpgradeType;
import net.minecraft.nbt.CompoundTag;

public class UpgradeCapabilityImpl implements IUpgradeCapability {

    private static final String NBT_KEY_UPGRADES = "upgrades";
    private final Map<UpgradeType,Integer> upgradeCounts = new HashMap<>();


    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        CompoundTag upgrades = new CompoundTag();
        for (UpgradeType type: UpgradeType.values()){
            upgrades.putInt(type.toString(), upgradeCounts.getOrDefault(type,0));
        }
        tag.put("upgrades", upgrades);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        CompoundTag upgrades = tag.getCompound(NBT_KEY_UPGRADES);
        for (UpgradeType type : UpgradeType.values()){
            upgradeCounts.put( type, upgrades.getInt(type.toString()));
        }
    }

    @Override
    public Integer getUpgradeCount(UpgradeType type){
        return upgradeCounts.getOrDefault(type,0);
    }

    @Override
    public void addUpgrade(UpgradeType type){
        upgradeCounts.put(type,getUpgradeCount(type)+1);
    }

    @Override
    public void removeUpgrade(UpgradeType type){
        if (getUpgradeCount(type) > 0){
            upgradeCounts.put(type,getUpgradeCount(type)-1);
        }
    }

    @Override
    public void setUpgradeCount(UpgradeType type, int count) {
        upgradeCounts.put(type,count);
    }



}
