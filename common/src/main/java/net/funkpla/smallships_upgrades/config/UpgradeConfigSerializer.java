package net.funkpla.smallships_upgrades.config;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.funkpla.smallships_upgrades.config.UpgradeConfig.UpgradeCapsConfig;

public class UpgradeConfigSerializer extends JanksonConfigSerializer<UpgradeConfig> {
    public UpgradeConfigSerializer(Config definition, Class<UpgradeConfig> configClass) {
        super(definition, configClass);
    }

    @Override
    public UpgradeConfig createDefault(){
        UpgradeConfig config = super.createDefault();

        config.caps.add(new UpgradeCapsConfig("cog",8,2,6));
        config.caps.add( new UpgradeCapsConfig("brigg",10,4,9));
        config.caps.add( new UpgradeCapsConfig("drakkar",6,1,3));
        config.caps.add( new UpgradeCapsConfig("galley",5,1,3));
        return config;
    }
}
