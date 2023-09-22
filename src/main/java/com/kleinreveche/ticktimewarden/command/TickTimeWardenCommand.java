package com.kleinreveche.ticktimewarden.command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class TickTimeWardenCommand {

    public static boolean stopWhenNoPlayers = true;

    public static int run(@NotNull CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();

        if (source.hasPermissionLevel(2)) {
            TickTimeWardenCommand.stopWhenNoPlayers = !TickTimeWardenCommand.stopWhenNoPlayers;

            if (!TickTimeWardenCommand.stopWhenNoPlayers)
                source.sendFeedback(() -> Text.literal("Temporarily, the server will not suspend when there are no players."), true);
            else
                source.sendFeedback(() -> Text.literal("The server will suspend when there are no players."), true);

            return 1;
        } else {
            source.sendError(Text.literal("You must be an operator to use this command."));
            return -1;
        }
    }
}
