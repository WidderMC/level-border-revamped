package widder.levelborderrevamped.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import widder.levelborderrevamped.LevelBorderRevamped;
import widder.levelborderrevamped.MassageBuilder;

public class Version {
    public static int VersionCommand(CommandContext<CommandSourceStack> context) {
        MassageBuilder.send(context,"{} runs on version {}",MassageBuilder.text("[Level = Border Revamped]"), MassageBuilder.text("V"+ LevelBorderRevamped.VERSION));
        return 0;
    }
}