package widder.levelborderrevamped.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import widder.levelborderrevamped.LevelBorderRevamped;
import widder.levelborderrevamped.MassageBuilder;

public class Version {
    public static int VersionCommand(CommandContext<CommandSourceStack> context) {
        String text = "runs on version V" + LevelBorderRevamped.VERSION;
        MassageBuilder.sendMassage(context, text, false);
        return 0;
    }
}