package widder.levelborderrevamped.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import widder.levelborderrevamped.XPManager;

@Mixin(Player.class)
public class XPMixin {

    @Unique
    private static boolean levelbordermerging = false;

    @Inject(method = "giveExperiencePoints", at = @At("TAIL"))
    private void XPMixin(CallbackInfo ci){
        if (levelbordermerging) return;
        if (!((Object) this instanceof ServerPlayer player)) return;

        MinecraftServer server = player.level().getServer();
        if (server == null) return;

        levelbordermerging = true;
        try {
            XPManager.MergeXP(server, false, null);
        } finally {
            levelbordermerging = false;
        }
    }
}