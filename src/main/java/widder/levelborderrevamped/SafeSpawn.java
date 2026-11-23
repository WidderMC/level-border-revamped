package widder.levelborderrevamped;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.Vec3;

import java.util.Set;

public class SafeSpawn {
    private static boolean isFree(ServerLevel level,BlockPos pos) {
        return level.noCollision(null, EntityType.PLAYER.getDimensions().makeBoundingBox(pos.getBottomCenter()),true);
    }

    private static Vec3 findSafeSpawn(ServerLevel level) {
        BlockPos spawn = level.getRespawnData().pos();
        level.getChunk(spawn);

        BlockPos.MutableBlockPos pos = spawn.mutable();
        while (isFree(level,pos) && pos.getY() > level.getMinY()) {
            pos.move(Direction.DOWN);
        }
        while (!isFree(level, pos) && pos.getY() < level.getMaxY()) {
            pos.move(Direction.UP);
        }
        return Vec3.atBottomCenterOf(pos);
    }

    public static void tpToSafeSpawn(ServerPlayer player) {
        Vec3 pos = findSafeSpawn(player.level().getServer().overworld());
        player.teleportTo(player.level().getServer().overworld(),pos.x,pos.y,pos.z, Set.of(),player.getYRot(),player.getXRot(),true);
    }

    public static boolean isOutSide(ServerPlayer player) {
        WorldBorder border = FakeBorder.forDimension(player.level().dimension());
        if (border == null) return false;
        return !border.isWithinBounds(player.getBoundingBox());
    }
}