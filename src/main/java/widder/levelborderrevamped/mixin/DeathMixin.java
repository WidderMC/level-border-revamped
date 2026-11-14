package widder.levelborderrevamped.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import widder.levelborderrevamped.Config;
import widder.levelborderrevamped.XPManager;

@Mixin(LivingEntity.class)
public class DeathMixin {

    @Inject(method = "dropExperience", at = @At("HEAD"), cancellable = true)
    private void dropExperienceMixin(CallbackInfo ci) {
        if ((Object) this instanceof ServerPlayer player) {
            MinecraftServer server = player.level().getServer();
            if (player == null) return;
            if (!Config.INSTANCE.resetXPByDeath) {
               XPManager.MergeXP(server, false, null);
               ci.cancel();
            }else {
                XPManager.MergeXP(server, true, null);
            }
        }
    }
}