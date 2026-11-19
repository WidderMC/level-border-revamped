package widder.levelborderrevamped.mixin;

import net.minecraft.network.protocol.game.ClientboundInitializeBorderPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.level.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import widder.levelborderrevamped.Config;
import widder.levelborderrevamped.FakeBorder;

@Mixin(PlayerList.class)
public class WorldBorderMixin {
    @Inject(method = "sendLevelInfo", at = @At("TAIL"))
    private void sendFackeWorldborder(ServerPlayer player, ServerLevel level, CallbackInfo ci) {
        if (!Config.INSTANCE.Enabled) return;
        WorldBorder facke = FakeBorder.forDimension(level.dimension());
        if (facke == null) return;
        player.connection.send(new ClientboundInitializeBorderPacket(facke));
    }
}