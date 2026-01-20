package net.funkpla.smallships_upgrades;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum UpgradeType implements StringRepresentable {
  SPEED("speed"),
  CARGO("cargo"),
  HEALTH("health");

  private final String name;

  UpgradeType(String name) {
    this.name = name;
  }

  @Override
  public @NotNull String getSerializedName() {
    return name;
  }
}
