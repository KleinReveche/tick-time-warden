package com.kleinreveche.ticktimewarden;

import com.kleinreveche.ticktimewarden.command.TickTimeWardenCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TickTimeWarden implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("tick-time-warden");

    @Override
    public void onInitialize() {
        LOGGER.info("Guarding the time-stream for idleness...");

        CommandRegistrationCallback.EVENT.register(
                (dispatcher, registryAccess, environment) -> dispatcher
                        .register(CommandManager.literal("ticktimewarden")
                                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                                .executes(TickTimeWardenCommand::run)
                        )
        );
    }
}