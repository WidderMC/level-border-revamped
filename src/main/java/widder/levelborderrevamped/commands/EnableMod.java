package widder.levelborderrevamped.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import widder.levelborderrevamped.Config;
import widder.levelborderrevamped.FakeBorder;
import widder.levelborderrevamped.MassageBuilder;
import widder.levelborderrevamped.SafeSpawn;

public class EnableMod {
    public static int EnableModCommand(CommandContext<CommandSourceStack> context, boolean info, boolean edit) {
        if (Config.INSTANCE.Enabled) {
            MassageBuilder.send(context,"The Mod is {}, you cant change any settings!",MassageBuilder.bool(true));
        }
        if (info) {
            MassageBuilder.send(context,"The Mod is {}",MassageBuilder.bool(Config.INSTANCE.Enabled));
            MassageBuilder.send(context,"If you enable the Mod you cant change any settings!");
        }else {
            MassageBuilder.send(context,"The Mod is now {}",MassageBuilder.bool(edit));
            MassageBuilder.send(context,"It was previously {}",MassageBuilder.bool(Config.INSTANCE.Enabled));
            Config.INSTANCE.Enabled = edit;
            for (ServerPlayer player : context.getSource().getServer().getPlayerList().getPlayers()) {
                SafeSpawn.tpToSafeSpawn(player);
            }
            FakeBorder.resendAll(context.getSource().getServer());
        }
        return 0;
    }
}