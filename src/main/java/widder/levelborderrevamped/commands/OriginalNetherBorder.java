package widder.levelborderrevamped.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import widder.levelborderrevamped.Config;
import widder.levelborderrevamped.MassageBuilder;

public class OriginalNetherBorder {
    public static int OriginalNetherBorderCommand(CommandContext<CommandSourceStack> context,boolean info, boolean edit) {
        if (info) {
            MassageBuilder.send(context,"OriginalNetherBorder is {}",MassageBuilder.bool(Config.INSTANCE.OriginalNetherBorder));
        }else {
            if (Config.INSTANCE.Enabled) {
                MassageBuilder.send(context,"The Mod is {}, you cant change any settings!",MassageBuilder.bool(true));
                return 0;
            }
            MassageBuilder.send(context,"OriginalNetherBorder set now {}",MassageBuilder.bool(edit));
            MassageBuilder.send(context,"It was previously {}",MassageBuilder.bool(Config.INSTANCE.OriginalNetherBorder));
            Config.INSTANCE.OriginalNetherBorder = edit;
        }
        return 0;
    }
}