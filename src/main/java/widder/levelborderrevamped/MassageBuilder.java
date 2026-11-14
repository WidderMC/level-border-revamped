package widder.levelborderrevamped;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

public class MassageBuilder {
    private static final Style INFO = Style.EMPTY.withColor(ChatFormatting.DARK_AQUA);
    private static final Style ERROR = Style.EMPTY.withColor(ChatFormatting.DARK_RED);

    private static MutableComponent prefix() {
        return Component.empty()
                .append(Component.literal("[Level=Border] ")
                        .withStyle(style ->
                                style.withColor(ChatFormatting.BLUE)
                                        .withBold(true)));
    }

    private static MutableComponent info(String text) {
        return Component.empty()
                .append(prefix())
                .append(Component.literal(text).withStyle(INFO));
    }

    private static MutableComponent error(String text) {
        return Component.empty()
                .append(prefix())
                .append(Component.literal(text)).withStyle(ERROR);
    }

    public static void sendMassage(CommandContext<CommandSourceStack> context, String text, boolean error) {
        if (error) {
            context.getSource().sendSuccess(() -> error(text),false);
        }else {
            context.getSource().sendSuccess(() -> info(text),false);
        }
    }
}