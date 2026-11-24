package widder.levelborderrevamped.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import widder.levelborderrevamped.XPManager;

@Mixin(AnvilMenu.class)
public class AnvilMixin {

    @WrapOperation(method = "onTake", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;giveExperienceLevels(I)V"))
    private void onAnvil(Player player, int cost, Operation<Void> operation) {
        operation.call(player,cost);
        if (player instanceof ServerPlayer serverPlayer) {
            XPManager.SpendLevels(serverPlayer, -cost);
        }
    }
}