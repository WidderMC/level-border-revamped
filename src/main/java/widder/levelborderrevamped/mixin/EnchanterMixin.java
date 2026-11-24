package widder.levelborderrevamped.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import widder.levelborderrevamped.XPManager;

@Mixin(Player.class)
public class EnchanterMixin {

    @Inject(method = "onEnchantmentPerformed", at = @At("TAIL"))
    private void onEnchant(ItemStack item, int cost, CallbackInfo ci) {
        if (!((Object) this instanceof ServerPlayer player)) return;
        if (player.hasInfiniteMaterials()) return;
        XPManager.SpendLevels(player,cost);
    }
}