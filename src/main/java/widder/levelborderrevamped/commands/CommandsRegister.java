package widder.levelborderrevamped.commands;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
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

                        .then(Commands.literal("ResetXPbyDeath")
                                .executes(context ->
                                        ResetXPbyDeath.ResetXPAfterDeathCommand(context,true,false))
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .executes(context ->
                                                ResetXPbyDeath.ResetXPAfterDeathCommand(
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
                                        EnableMod.EnableModCommand(context,true,false,""))
                                .then(Commands.argument("value", BoolArgumentType.bool())
                                        .executes(context ->
                                                EnableMod.EnableModCommand(
                                                        context,false,BoolArgumentType.getBool(context,"value"),"")))
                                .then(Commands.literal("confirm")
                                        .executes(context ->
                                                EnableMod.EnableModCommand(context,false,true,"confirm"))))

                        .then(Commands.literal("OriginalNetherBorder")
                                .executes(context ->
                                        OriginalNetherBorder.OriginalNetherBorderCommand(context,true,false))
                                .then(Commands.argument("value",BoolArgumentType.bool())
                                        .executes(context ->
                                                OriginalNetherBorder.OriginalNetherBorderCommand(
                                                        context,false,BoolArgumentType.getBool(context,"value")))))

                        .then(Commands.literal("StartSize")
                                .executes(context ->
                                        StartSize.StartSizeCommand(context,true,0))
                                .then(Commands.argument("value", IntegerArgumentType.integer(1))
                                        .executes(context ->
                                                StartSize.StartSizeCommand(
                                                        context,false,IntegerArgumentType.getInteger(context,"value")))))

                        .then(Commands.literal("BlocksPerLevel")
                                .executes(context ->
                                        BlocksPerLevel.BlocksPerLevelCommand(context,true,0))
                                .then(Commands.argument("value",IntegerArgumentType.integer(1))
                                        .executes(context ->
                                                BlocksPerLevel.BlocksPerLevelCommand(
                                                        context,false,IntegerArgumentType.getInteger(context,"value")))))

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