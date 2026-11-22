package widder.levelborderrevamped;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundInitializeBorderPacket;
import net.minecraft.network.protocol.game.ClientboundSetBorderLerpSizePacket;
import net.minecraft.network.protocol.game.ClientboundSetBorderSizePacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.border.WorldBorder;

public class FakeBorder {
    private static double speedBlocksPerSecond = 2.0;

    private static final WorldBorder OVERWORLD = new WorldBorder();
    private static final WorldBorder NETHER = new WorldBorder();

    private static double sizeForLevel(int level) {
        return Config.INSTANCE.StartSize + level * Config.INSTANCE.BlocksPerLevel;
    }

    private static double netherFactor() {
        return Config.INSTANCE.OriginalNetherBorder ? 1.0 / 8.0 : 1.0;
    }

    private static void resize(MinecraftServer server, WorldBorder border, double to, ResourceKey<Level> dim, boolean animate, long tick) {
        if (animate) {
            border.lerpSizeBetween(border.getSize(), to, tick, server.overworld().getGameTime());
            server.getPlayerList().broadcastAll(new ClientboundSetBorderLerpSizePacket(border), dim);
        }else {
            border.setSize(to);
            server.getPlayerList().broadcastAll(new ClientboundSetBorderSizePacket(border), dim);
        }
    }

    public static double getBorderSize(boolean nether) {
        WorldBorder border = nether ? NETHER : OVERWORLD;
        return border.getSize();
    }

    public static WorldBorder forDimension(ResourceKey<Level> dim) {
        if (dim == Level.OVERWORLD) return OVERWORLD;
        if (dim == Level.NETHER) return NETHER;
        return null;
    }

    public static void initialize(MinecraftServer server) {
        BlockPos spawn = server.overworld().getRespawnData().pos();
        double x = spawn.getX() + 0.5;
        double z = spawn.getZ() + 0.5;
        OVERWORLD.setCenter(x,z);
        NETHER.setCenter(x/8.0,z/8.0);
        for (WorldBorder BORDER : new WorldBorder[]{OVERWORLD,NETHER}) {;
            BORDER.setSafeZone(0.0);
            BORDER.setDamagePerBlock(0.2);
            BORDER.setWarningBlocks(1);
            BORDER.setWarningTime(0);
        }
        double size = sizeForLevel(Config.INSTANCE.CurrentLevel);
        OVERWORLD.setSize(size);
        NETHER.setSize(Math.max(1.0,size*netherFactor()));
    }

    public static void tick(MinecraftServer server) {
        OVERWORLD.tick();
        NETHER.tick();

        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            WorldBorder border = forDimension(player.level().dimension());
            if (border == null) continue;
            if (!player.isAlive()) continue;
            if (border.isWithinBounds(player.getBoundingBox())) continue;

            double dist = border.getDistanceToBorder(player) + border.getSafeZone();
            if (dist > -1) continue;
            double dmgPerBlock = border.getDamagePerBlock();
            if (dmgPerBlock <= 0) continue;

            player.hurtServer(player.level(), player.damageSources().outOfBorder(),Math.max(1, Mth.floor(-dist * dmgPerBlock)));
        }
    }

    public static void setBorder(MinecraftServer server, int level, boolean animate) {
        Config.INSTANCE.CurrentLevel = level;
        double to = sizeForLevel(level);

        long ticks = Math.max(1, (long) (Math.abs(to - OVERWORLD.getSize()) / speedBlocksPerSecond * 20));
        resize(server, OVERWORLD, to, Level.OVERWORLD, animate, ticks);
        resize(server, NETHER, Math.max(1.0, to * netherFactor()), Level.NETHER, animate, ticks);
    }

    public static void resendAll(MinecraftServer server) {
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            WorldBorder border;
            if (Config.INSTANCE.Enabled) {
                border = forDimension(player.level().dimension());
            } else {
                border = null;
            }
            if (border == null) {
                border = player.level().getWorldBorder();
            }
            player.connection.send(new ClientboundInitializeBorderPacket(border));
        }
    }
}