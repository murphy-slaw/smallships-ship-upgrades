package net.funkpla.smallships_upgrades.item;

import lombok.Getter;
import net.funkpla.smallships_upgrades.UpgradeType;
import net.minecraft.world.item.Item;

@Getter
public class UpgradeItem extends Item {
    protected UpgradeType type;
    public UpgradeItem(Properties properties) {
        super(properties);
    }
}
