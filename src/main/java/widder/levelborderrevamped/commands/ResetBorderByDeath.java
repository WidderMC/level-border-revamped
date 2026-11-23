package widder.levelborderrevamped.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import widder.levelborderrevamped.Config;
import widder.levelborderrevamped.MassageBuilder;

public class ResetBorderByDeath {
    public static int ResetBorderByDeathCommand(CommandContext<CommandSourceStack> context, boolean info, boolean edit ) {
        if (info) {
            MassageBuilder.send(context,"ResetBorderByDeath is {}",MassageBuilder.bool(Config.INSTANCE.ResetBorderByDeath));
            MassageBuilder.send(context,"{} has to be {} to work",MassageBuilder.text("ResetXPAfterDeath"),MassageBuilder.bool(true));
        }else {
            if (Config.INSTANCE.Enabled) {
                MassageBuilder.send(context,"The Mod is {}, you cant change any settings!",MassageBuilder.bool(true));
                return 0;
            }
            MassageBuilder.send(context,"ResetBorderByDeath set now {}",MassageBuilder.bool(edit));
            MassageBuilder.send(context,"It was previously {}",MassageBuilder.bool(Config.INSTANCE.ResetBorderByDeath));
            if (edit) {
                MassageBuilder.send(context, "{} has to be {} to work", MassageBuilder.text("ResetXPAfterDeath"), MassageBuilder.bool(true));
            }
            Config.INSTANCE.ResetBorderByDeath = edit;
        }
        return 0;
    }
}