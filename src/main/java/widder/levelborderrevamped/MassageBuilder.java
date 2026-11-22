package widder.levelborderrevamped;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

public class MassageBuilder {
    private static final Style INFO = Style.EMPTY.withColor(ChatFormatting.DARK_AQUA);
    private static final Style DISABLE = Style.EMPTY.withColor(ChatFormatting.DARK_RED);
    private static final Style ENABLE = Style.EMPTY.withColor(ChatFormatting.GREEN);
    private static final Style TEXT = Style.EMPTY.withColor(ChatFormatting.BLUE);
    private static final Style VALUE = Style.EMPTY.withColor(ChatFormatting.YELLOW);

    public static MutableComponent value(Object value) {
        return Component.literal(String.valueOf(value)).withStyle(VALUE);
    }

    public static MutableComponent text(Object value) {
        return Component.literal(String.valueOf(value)).withStyle(TEXT);
    }

    public static MutableComponent bool(boolean b) {
        return b ? Component.literal("enabled").withStyle(ENABLE)
                 : Component.literal("disabled").withStyle(DISABLE);
    }

    public static void send(CommandContext<CommandSourceStack> context, String text, MutableComponent... values) {
        MutableComponent msg = Component.empty();
        String[] parts = text.split("\\{\\}", -1);

        for (int i = 0; i < parts.length; i++) {
            msg.append(Component.literal(parts[i]).withStyle(INFO));
            if (i < values.length && i < parts.length -1) {
                msg.append(values[i]);
            }
        }
        context.getSource().sendSuccess(() -> msg, false);
    }
}