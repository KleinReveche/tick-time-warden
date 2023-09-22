package com.kleinreveche.ticktimewarden.mixins;

import com.kleinreveche.ticktimewarden.command.TickTimeWardenCommand;
import net.minecraft.server.MinecraftServer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(MinecraftServer.class)
class MinecraftServerMixin {

    @Unique
    private static final long LONG_TICK_MS = 333L;

    @Redirect(method = "runServer()V", at = @At(value = "FIELD", target = "Lnet/minecraft/server/MinecraftServer;timeReference:J", opcode = Opcodes.PUTFIELD))
    private void injectTimeReference(MinecraftServer server, long val) {
        if (TickTimeWardenCommand.stopWhenNoPlayers && server.getCurrentPlayerCount() == 0)
            ((MinecraftServerAccessor) server).setTimeReference(server.getTimeReference() + LONG_TICK_MS);
        else ((MinecraftServerAccessor) server).setTimeReference(val);
    }

    @Redirect(method = "runServer()V", at = @At(value = "FIELD", target = "Lnet/minecraft/server/MinecraftServer;nextTickTimestamp:J", opcode = Opcodes.PUTFIELD))
    private void injectNextTime(MinecraftServer server, long val) {
        if (TickTimeWardenCommand.stopWhenNoPlayers && server.getCurrentPlayerCount() == 0)
            ((MinecraftServerAccessor) server).setNextTickTimestamp(server.getTimeReference());
        else
            ((com.kleinreveche.ticktimewarden.mixins.MinecraftServerAccessor) server).setNextTickTimestamp(val);
    }
}

