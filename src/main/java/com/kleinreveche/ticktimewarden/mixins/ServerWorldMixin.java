package com.kleinreveche.ticktimewarden.mixins;

import com.kleinreveche.ticktimewarden.command.TickTimeWardenCommand;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
class ServerWorldMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void onTick(final CallbackInfo info) {
        if (TickTimeWardenCommand.stopWhenNoPlayers) {
            if (((ServerWorld) (Object) this).getServer().getCurrentPlayerCount() == 0) {
                info.cancel();
            }
        }
    }

}

