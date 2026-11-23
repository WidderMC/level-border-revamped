package widder.levelborderrevamped.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import widder.levelborderrevamped.Config;
import widder.levelborderrevamped.MassageBuilder;

public class StartSize {
    public static int StartSizeCommand(CommandContext<CommandSourceStack> context,boolean info, int edit) {
        if (info) {
            MassageBuilder.send(context,"StartSize is on {}",MassageBuilder.value(Config.INSTANCE.StartSize));
        }else {
            if (Config.INSTANCE.Enabled) {
                MassageBuilder.send(context,"The Mod is {}, you cant change any settings!",MassageBuilder.bool(true));
                return 0;
            }
            MassageBuilder.send(context,"StartSize set now {}",MassageBuilder.text(edit));
            MassageBuilder.send(context,"It was previously {}",MassageBuilder.text(Config.INSTANCE.StartSize));
            Config.INSTANCE.StartSize = edit;
        }
        return 0;
    }
}