package widder.levelborderrevamped.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import widder.levelborderrevamped.Config;
import widder.levelborderrevamped.FakeBorder;
import widder.levelborderrevamped.MassageBuilder;

public class Info {
    public static int InfoCommand(CommandContext<CommandSourceStack> context) {
        MassageBuilder.sendMassage(context, "Infos about the World", false);
        String t1 = Config.INSTANCE.Enabled ? "enable" : "disable";
        MassageBuilder.sendMassage(context, "Level=Border is: " + t1, false);
        if (!Config.INSTANCE.Enabled) return 0;
        String t2 = Config.INSTANCE.resetBorderByDeath ? "enable" : "disable";
        MassageBuilder.sendMassage(context, "ResetBorderByDeath is: " + t2, false);
        String t3 = Config.INSTANCE.resetXPByDeath ? "enable" : "disable";
        MassageBuilder.sendMassage(context, "ResetXPByDeath is: " + t3, false);
        String t4 = Config.INSTANCE.AutoExpand ? "enable" : "disable";
        MassageBuilder.sendMassage(context, "AutoExpand is: " + t4, false);
        String t5 = Config.INSTANCE.OriginalNetherBorder ? "enable" : "disable";
        MassageBuilder.sendMassage(context, "OriginalNetherBorder is: " + t5, false);
        String t6 = String.valueOf(Config.INSTANCE.globalXp);
        MassageBuilder.sendMassage(context, "Your GlobalXp is: " + t6, false);
        String t7 = String.valueOf(Config.INSTANCE.currentLevel);
        MassageBuilder.sendMassage(context, "Your CurrentLevel is: " + t7, false);
        String t8 = String.valueOf(Config.INSTANCE.startSize);
        MassageBuilder.sendMassage(context, "Your StartSize is: " + t8, false);
        String t9 = String.valueOf(Config.INSTANCE.blocksPerLevel);
        MassageBuilder.sendMassage(context, "Your BlocksPerLevel is: " + t9, false);
        String t10 = String.valueOf(FakeBorder.getBorderSize(context.getSource().getPlayer(),false));
        MassageBuilder.sendMassage(context, "The Overworld Border is on: " + t10, false);
        String t11 = String.valueOf(FakeBorder.getBorderSize(context.getSource().getPlayer(),true));
        MassageBuilder.sendMassage(context, "The Nether Border is on: " + t11, false);
        MassageBuilder.sendMassage(context, "You can change the values via command bevor you enable the mod", false);
        return 0;
    }
}