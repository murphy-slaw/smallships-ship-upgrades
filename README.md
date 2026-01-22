# Small Ships Upgrades

A simple implmentation of upgrade items for [Small Ships](https://github.com/talhanation/smallships)

## Items

There are three items implemented, one for each major ship attribute: speed, health, and cargo.

- `smallships_upgrades:speed_upgrade`
- `smallships_upgrades:health_upgrade`
- `smallships_upgrades:cargo_upgrade`

To apply an upgrade, right click on a ship with it. If the ship has enough upgrade slots to
accept the upgrade, the upgrade item will be consumed and the appropriate attribute will be increased.

### Note

The mod adds these items, but does not add recipes for them or add them to loot tables. You'll have to do that yourself.

## Configuration

### upgradeRecyclePercentage
The percentage of upgrade items to drop when the ship is destroyed, rounding up. Defaults to 100.

### speedIncrement
The number of points to increase a ship's speed for each upgrade applied. Defaults to 10.

### healthIncrement
The number of points to increase a ship's health for each upgrade applied. Defaults to 20.

### cargoIncrement
The number of 9-slot rows to add to the ship's cargo capacity for each upgrade applied. Defaults to 6 (54 slots, one 
full page).

### caps

A list of upgrade caps for each ship type, in the format

```
    {
        "name": "cog",
        "speed": 8,
        "health": 2,
        "cargo": 6
    }
```

Unknown ship types will be ignored. If there are duplicate entries for a ship type, the last entry wins. 
