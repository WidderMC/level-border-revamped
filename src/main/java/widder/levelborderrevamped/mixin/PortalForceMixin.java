package widder.levelborderrevamped.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.portal.PortalForcer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import widder.levelborderrevamped.Config;
import widder.levelborderrevamped.FakeBorder;

@Mixin(PortalForcer.class)
public class PortalForceMixin {
    @WrapOperation(method = "createPortal",at = @At(value = "INVOKE",
            target = "Lnet/minecraft/server/level/ServerLevel;getWorldBorder()Lnet/minecraft/world/level/border/WorldBorder;"))
    private WorldBorder useFakeBorder(ServerLevel level, Operation<WorldBorder> original) {
        if (Config.INSTANCE.Enabled) {
            WorldBorder fake = FakeBorder.forDimension(level.dimension());
            if (fake != null) return fake;
        }
        return original.call(level);
    }
}