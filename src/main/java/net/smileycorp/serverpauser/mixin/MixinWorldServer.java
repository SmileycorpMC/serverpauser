package net.smileycorp.serverpauser.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.profiler.Profiler;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

@Mixin(WorldServer.class)
public abstract class MixinWorldServer {

	@Shadow @Final private MinecraftServer mcServer;

	@Inject(at=@At("HEAD"), method = "tick()V", cancellable = true)
	public void serverpauser$tick(CallbackInfo callback) {
		if (mcServer.getCurrentPlayerCount() <= 0) callback.cancel();
	}

}
