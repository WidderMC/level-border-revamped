package widder.levelborderrevamped.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import widder.levelborderrevamped.Config;
import widder.levelborderrevamped.MassageBuilder;

public class BlocksPerLevel {
    public static int BlocksPerLevelCommand(CommandContext<CommandSourceStack> context,boolean info,int edit) {
        if (info) {
            MassageBuilder.send(context,"BlocksPerLevel is on {}",MassageBuilder.value(Config.INSTANCE.BlocksPerLevel));
        }else {
            if (Config.INSTANCE.Enabled) {
                MassageBuilder.send(context,"The Mod is {}, you cant change any settings!",MassageBuilder.bool(true));
                return 0;
            }
            MassageBuilder.send(context,"BlocksPerLevel set now {}",MassageBuilder.text(edit));
            MassageBuilder.send(context,"It was previously {}",MassageBuilder.text(Config.INSTANCE.BlocksPerLevel));
            Config.INSTANCE.BlocksPerLevel = edit;
        }
        return 0;
    }
}