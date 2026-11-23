package widder.levelborderrevamped.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import widder.levelborderrevamped.Config;
import widder.levelborderrevamped.FakeBorder;
import widder.levelborderrevamped.MassageBuilder;

public class Info {
    public static int InfoCommand(CommandContext<CommandSourceStack> context) {
        MassageBuilder.send(context,"World settings from {}:",MassageBuilder.text("[Level = Border Revamped]"));
        MassageBuilder.send(context," - [Level = Border Revamped] is {}",MassageBuilder.bool(Config.INSTANCE.Enabled));
        MassageBuilder.send(context," - Reset Border on Death: {}",MassageBuilder.bool(Config.INSTANCE.ResetBorderByDeath));
        MassageBuilder.send(context," - Reset XP on Death: {}",MassageBuilder.bool(Config.INSTANCE.ResetXPByDeath));
        MassageBuilder.send(context," - Auto Expand: {}",MassageBuilder.bool(Config.INSTANCE.AutoExpand));
        MassageBuilder.send(context," - Original Nether Border: {}",MassageBuilder.bool(Config.INSTANCE.OriginalNetherBorder));
        MassageBuilder.send(context," - Global XP: {}",MassageBuilder.value(Config.INSTANCE.GlobalXp));
        MassageBuilder.send(context," - Current Level: {}",MassageBuilder.value(Config.INSTANCE.CurrentLevel));
        MassageBuilder.send(context," - Starting Size: {}",MassageBuilder.value(Config.INSTANCE.StartSize));
        MassageBuilder.send(context," - Blocks per Level: {}",MassageBuilder.value(Config.INSTANCE.BlocksPerLevel));
        MassageBuilder.send(context," - Overworld Border: {}",MassageBuilder.value(String.format("%.0f",FakeBorder.getBorderSize(false))));
        MassageBuilder.send(context," - Nether Border: {}",MassageBuilder.value(String.format("%.2f",FakeBorder.getBorderSize(true))));
        MassageBuilder.send(context,"");
        MassageBuilder.send(context,"You can change these settings using commands before enabling the mod.");


        /*              Old Info Massage

        MassageBuilder.send(context,"World settings from {}:",MassageBuilder.text("[Level = Border Revamped]"));
        MassageBuilder.send(context," - {} is {}",MassageBuilder.text("[Level = Border Revamped]"),MassageBuilder.bool(Config.INSTANCE.Enabled));
        MassageBuilder.send(context," - {} is {}", MassageBuilder.text("ResetBorderByDeath"),MassageBuilder.bool(Config.INSTANCE.ResetBorderByDeath));
        MassageBuilder.send(context," - {} is {}",MassageBuilder.text("ResetXPByDeath"), MassageBuilder.bool(Config.INSTANCE.ResetXPByDeath));
        MassageBuilder.send(context," - {} is {}",MassageBuilder.text("AutoExpand"),MassageBuilder.bool(Config.INSTANCE.AutoExpand));
        MassageBuilder.send(context," - {} is {}",MassageBuilder.text("OriginalNetherBorder"), MassageBuilder.bool(Config.INSTANCE.OriginalNetherBorder));
        MassageBuilder.send(context," - Your {} is on {}", MassageBuilder.text("GlobalXp"),MassageBuilder.value(Config.INSTANCE.GlobalXp));
        MassageBuilder.send(context," - Your {} is on {}",MassageBuilder.text("CurrentLevel"),MassageBuilder.value(Config.INSTANCE.CurrentLevel));
        MassageBuilder.send(context," - The {} is on {}", MassageBuilder.text("StartSize"),MassageBuilder.value(Config.INSTANCE.StartSize));
        MassageBuilder.send(context," - The {} is on {} Blocks",MassageBuilder.text("BlocksPerLevel"),MassageBuilder.value(Config.INSTANCE.BlocksPerLevel));
        MassageBuilder.send(context," - The {} in {} Blocks",MassageBuilder.text("Overworld Border"), MassageBuilder.value(FakeBorder.getBorderSize(context.getSource().getPlayer(),false)));
        MassageBuilder.send(context," - The {} in {} Blocks",MassageBuilder.text("Nether Border"), MassageBuilder.value(FakeBorder.getBorderSize(context.getSource().getPlayer(),false)));
        MassageBuilder.send(context,"");
        MassageBuilder.send(context,"You can change these settings using commands before enabling the mod.");
         */
        return 0;
    }
}