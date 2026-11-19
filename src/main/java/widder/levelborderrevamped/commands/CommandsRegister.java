package widder.levelborderrevamped.commands;

import com.mojang.brigadier.arguments.BoolArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;

public class CommandsRegister {

    public static void Register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, selection) ->
                dispatcher.register(Commands.literal("Level-Border")
                        .then(Commands.literal("Version")
                                .executes(context -> Version.VersionCommand(context)))

                        .then(Commands.literal("Info")
                                .executes(context -> Info.InfoCommand(context)))

                        .then(Commands.literal("ResetXPAfterDeath")
                                .executes(context ->
                                        ResetXPAfterDeath.ResetXPAfterDeathCommand(context,true,false))
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .executes(context ->
                                                ResetXPAfterDeath.ResetXPAfterDeathCommand(
                                                        context,false, BoolArgumentType.getBool(context,"value")))))

                        .then(Commands.literal("ResetBorderByDeath")
                                .executes(context ->
                                        ResetBorderByDeath.ResetBorderByDeathCommand(context,true,false))
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .executes(context ->
                                                ResetBorderByDeath.ResetBorderByDeathCommand(
                                                        context,false,BoolArgumentType.getBool(context,"value")))))

                        .then(Commands.literal("EnableMod")
                                .executes(context ->
                                        EnableMod.EnableModCommand(context,true,false))
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .executes(context ->
                                                EnableMod.EnableModCommand(
                                                        context,false,BoolArgumentType.getBool(context,"value")))))

                        .then(Commands.literal("AutoExpand")
                                .executes(context ->
                                        AutoExpand.AutoExpandCommand(context,true,false))
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .executes(context ->
                                                AutoExpand.AutoExpandCommand(
                                                        context,false,BoolArgumentType.getBool(context,"value")))))

                        .then(Commands.literal("Expand")
                                .executes(context ->
                                        Expand.ExpandCommand(context)))
                )
        );
    }
}