package widder.levelborderrevamped.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import widder.levelborderrevamped.Config;
import widder.levelborderrevamped.FakeBorder;
import widder.levelborderrevamped.MassageBuilder;
import widder.levelborderrevamped.XPManager;

public class Expand {
    public static int ExpandCommand(CommandContext<CommandSourceStack> context) {
        if (!Config.INSTANCE.Enabled) {
            MassageBuilder.send(context, "The Mod is {}, enable it first!", MassageBuilder.bool(false));
            return 0;
        }
        if (Config.INSTANCE.AutoExpand) {
            MassageBuilder.send(context,"AutoExpand is {}, this command only works if it is {}",MassageBuilder.bool(true),MassageBuilder.bool(false));
            return 0;
        }

        MinecraftServer server = context.getSource().getServer();
        int level = XPManager.getLevel(server);

        if (level <= Config.INSTANCE.CurrentLevel) {
            MassageBuilder.send(context,"The Border is already on Level {}",MassageBuilder.value(Config.INSTANCE.CurrentLevel));
            return 0;
        }

        FakeBorder.setBorder(server,level,true);
        Config.save();
        MassageBuilder.send(context,"Expanded the Border to Level {} ({} Blocks)",MassageBuilder.value(level),MassageBuilder.value(FakeBorder.getBorderSize(false)));
        return 0;
    }
}