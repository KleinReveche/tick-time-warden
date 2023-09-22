package com.kleinreveche.ticktimewarden.mixins;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MinecraftServer.class)
public interface MinecraftServerAccessor {
    @Accessor("timeReference")
    void setTimeReference(long val);

    @Accessor("nextTickTimestamp")
    void setNextTickTimestamp(long val);
}
