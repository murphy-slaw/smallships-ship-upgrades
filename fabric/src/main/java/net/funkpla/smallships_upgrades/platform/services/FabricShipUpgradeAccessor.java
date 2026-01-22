package net.funkpla.smallships_upgrades.platform.services;

import net.funkpla.smallships_upgrades.UpgradeType;
import net.funkpla.smallships_upgrades.components.UpgradesComponent;
import net.minecraft.world.entity.Entity;

import static net.funkpla.smallships_upgrades.components.UpgradesComponent.SHIP_UPGRADES;

public class FabricShipUpgradeAccessor implements ShipUpgradeAccessor{
    private UpgradesComponent shipUpgrades;

    public FabricShipUpgradeAccessor(){}

    public FabricShipUpgradeAccessor(Entity e) {
       shipUpgrades = SHIP_UPGRADES.get(e);
    }

    public ShipUpgradeAccessor from(Entity e){
        return new FabricShipUpgradeAccessor(e);
    }

    @Override
    public Integer getUpgradeCount(UpgradeType type) {
        return shipUpgrades.getUpgradeCount(type);
    }

    @Override
    public void addUpgrade(UpgradeType type) {
        shipUpgrades.addUpgrade(type);

    }

    @Override
    public void removeUpgrade(UpgradeType type) {
        shipUpgrades.removeUpgrade(type);
    }

    @Override
    public void setUpgradeCount(UpgradeType type, int count) {
        shipUpgrades.setUpgradeCount(type,count);
    }
}
