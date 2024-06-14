package net.hyper_pigeon.wacky_wheel.register;

import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.hyper_pigeon.wacky_wheel.spell.SpellManager;
import net.hyper_pigeon.wacky_wheel.spell.SpellType;
import net.hyper_pigeon.wacky_wheel.spell.SpellTypeRegistry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class WheelOfWackyCommands {
    public static void init(){
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("wheel")
                    .requires(source -> source.hasPermissionLevel(2))
//                    .executes(context -> {
//                        context.getSource().sendFeedback(() -> Text.literal("Called /wheel with no arguments."), false);
//                        return 1;
//                    })
//                    .then(CommandManager.literal("spell")
////                            .executes(context -> {
////                        context.getSource()
////                                .sendFeedback(
////                                        () -> Text.literal("Called spell subcommand with no arguments."), false);
////                        return 1;
////                    })
//                    )
                    .then(CommandManager.argument("spell_type", StringArgumentType.string())
                            .executes(context -> {
                                String spell_name = StringArgumentType.getString(context, "spell_type");

                                try {
                                    SpellType spellType = SpellTypeRegistry.get(Identifier.of("wacky_wheel:" + spell_name));
                                    ServerPlayerEntity serverPlayerEntity = context.getSource().getPlayerOrThrow();

                                    SpellManager.addSpell(spellType,serverPlayerEntity);
//                                    String parsedCommand = "execute as " + serverPlayerEntity.getUuidAsString() + " run function " + "wacky_wheel:" + spellType.onCastFunction();
//                                    ServerCommandSource commandSource = serverPlayerEntity.getServer().getCommandSource();
//                                    ParseResults<ServerCommandSource> parseResults = commandSource.getDispatcher().parse(parsedCommand, commandSource);
//                                    serverPlayerEntity.getServer().getCommandManager().execute(parseResults, parsedCommand);

                                    context.getSource()
                                            .sendFeedback(
                                                    () -> Text.literal(
                                                            "Casted spell = %s".formatted(spell_name)),
                                                    false);
                                }
                                catch(Error e) {
                                    context.getSource()
                                            .sendError(
                                                    Text.literal(
                                                            "Failed to cast spell = %s.".formatted(spell_name) + "Error: " + e));
                                }


                                return 1;
                            })));
        });
    }
}
