package widder.levelborderrevamped.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import widder.levelborderrevamped.Config;
import widder.levelborderrevamped.MassageBuilder;

public class AutoExpand {
    public static int AutoExpandCommand(CommandContext<CommandSourceStack> context, boolean info, boolean edit) {
        if (info) {
            MassageBuilder.send(context,"AutoExpand is {}",MassageBuilder.bool(Config.INSTANCE.AutoExpand));
            MassageBuilder.send(context,"If {} is {} you can use /Level-Border Expand to expand the Border",MassageBuilder.text("AutoExpand"),MassageBuilder.bool(true));
        }else {
            MassageBuilder.send(context,"AutoExpand set now {}",MassageBuilder.bool(edit));
            MassageBuilder.send(context,"It was previously {}",MassageBuilder.bool(Config.INSTANCE.AutoExpand));
            if (!edit) {
                MassageBuilder.send(context,"Now you can use the /Level-Border Expand command to manly expand the mod");
            }else {
                MassageBuilder.send(context,"Now is will automatically expand");
            }
            Config.INSTANCE.AutoExpand = edit;
        }
        return 0;
    }
}