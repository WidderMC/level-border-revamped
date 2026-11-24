package widder.levelborderrevamped.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import widder.levelborderrevamped.Config;
import widder.levelborderrevamped.FakeBorder;
import widder.levelborderrevamped.MassageBuilder;
import widder.levelborderrevamped.SafeSpawn;

public class EnableMod {
    public static int EnableModCommand(CommandContext<CommandSourceStack> context, boolean info, boolean edit,String c) {
        MinecraftServer server = context.getSource().getServer();

        if (info) {
            MassageBuilder.send(context,"The mod is {}",MassageBuilder.bool(Config.INSTANCE.Enabled));
            MassageBuilder.send(context,"Once the mod is enabled, you can't change any settings!");
            return 0;
        }

        if (edit == Config.INSTANCE.Enabled){
            MassageBuilder.send(context,"The mod is already {}",MassageBuilder.bool(edit));
            return 0;
        }

        if (!edit) {
            MassageBuilder.send(context, "The mod can't be disabled once it is enabled!");
            return 0;
        }

        if (!"confirm".equals(c)) {
            MassageBuilder.send(context,"Be careful: once the mod is enabled, you can't change any settings!");
            MassageBuilder.send(context,"Use {} to enable the mod.",MassageBuilder.text("/Level-Border EnableMod confirm"));
            return 0;
        }

        Config.INSTANCE.Enabled = true;
        FakeBorder.setBorder(server, Config.INSTANCE.CurrentLevel,false);
        FakeBorder.resendAll(server);
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            SafeSpawn.tpToSafeSpawn(player);
        }
        Config.save();
        MassageBuilder.send(context,"The mod is now {}",MassageBuilder.bool(true));
        return 0;
    }
}