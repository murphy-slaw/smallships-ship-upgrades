package net.funkpla.smallships_upgrades;

import java.util.function.Supplier;
import net.funkpla.smallships_upgrades.entity.ship.abilities.Upgradeable;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketListener;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class UpgradePacketHandler {
  private static final String PROTOCOL_VERSION = "1";
  public static final SimpleChannel INSTANCE =
      NetworkRegistry.newSimpleChannel(
          CommonClass.locate("main"),
          () -> PROTOCOL_VERSION,
          PROTOCOL_VERSION::equals,
          PROTOCOL_VERSION::equals);
  public static int messageId;

  public static void registerPackets() {
    INSTANCE.registerMessage(
        messageId++,
        SetUpgradePacket.class,
        SetUpgradePacket::encoder,
        SetUpgradePacket::new,
        SetUpgradePacket::handle);
  }

  public static void handle(SetUpgradePacket msg, Supplier<NetworkEvent.Context> ctx) {
    ctx.get()
        .enqueueWork(
            () ->
                // Make sure it's only executed on the physical client
                DistExecutor.unsafeRunWhenOn(
                    Dist.CLIENT, () -> () -> handleSetUpgradePacket(msg, ctx)));
    ctx.get().setPacketHandled(true);
  }

  public static void handleSetUpgradePacket(
      SetUpgradePacket packet, Supplier<NetworkEvent.Context> ctx) {
    PacketListener listener = ctx.get().getNetworkManager().getPacketListener();
    if (listener instanceof ClientPacketListener) {
      Entity ship = ((ClientPacketListener) listener).getLevel().getEntity(packet.entityId);
      if (ship instanceof Upgradeable upgradableShip) {
        var upgrades = upgradableShip.getUpgrades();
        upgrades.setUpgradeCount(packet.type, packet.count);
      }
    }
  }

  public static void sendAddUpgradePacket(Entity ship, UpgradeType type, int count) {
    if (ship.getCommandSenderWorld().isClientSide) return;
    INSTANCE.send(
        PacketDistributor.TRACKING_ENTITY.with(() -> ship),
        new SetUpgradePacket(ship.getId(), type, count));
  }

  public static class SetUpgradePacket {
    public final int entityId;
    public final UpgradeType type;
    public final int count;

    public SetUpgradePacket(int entityId, UpgradeType type, int count) {
      this.entityId = entityId;
      this.type = type;
      this.count = count;
    }

    public SetUpgradePacket(FriendlyByteBuf buffer) {
      entityId = buffer.readInt();
      type = buffer.readEnum(UpgradeType.class);
      count = buffer.readInt();
    }

    public void encoder(FriendlyByteBuf buffer) {
      buffer.writeInt(entityId);
      buffer.writeEnum(type);
      buffer.writeInt(count);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
      ctx.get()
          .enqueueWork(
              () ->
                  DistExecutor.unsafeRunWhenOn(
                      Dist.CLIENT, () -> () -> UpgradePacketHandler.handle(this, ctx)));
      ctx.get().setPacketHandled(true);
    }
  }
}
