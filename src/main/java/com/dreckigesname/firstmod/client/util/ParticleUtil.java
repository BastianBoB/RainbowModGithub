package com.dreckigesname.firstmod.client.util;

import com.dreckigesname.firstmod.FirstMod;
import com.dreckigesname.firstmod.common.particles.InstantParticle;
import com.dreckigesname.firstmod.core.init.ParticleInit;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = FirstMod.MOD_ID, bus = Bus.MOD)
@OnlyIn(Dist.CLIENT)
public class ParticleUtil {
	
	@SuppressWarnings("resource")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void registerParticle(ParticleFactoryRegisterEvent event) {
		Minecraft.getInstance().particleEngine.register(ParticleInit.RED_PARTICLE.get(), InstantParticle.Factory::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.ORANGE_PARTICLE.get(), InstantParticle.Factory::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.YELLOW_PARTICLE.get(), InstantParticle.Factory::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.GREEN_PARTILE.get(), InstantParticle.Factory::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.BLUE_PARTICLE.get(), InstantParticle.Factory::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.PURPLE_PARTICLE.get(), InstantParticle.Factory::new);
		Minecraft.getInstance().particleEngine.register(ParticleInit.WHITE_PARTICLE.get(), InstantParticle.Factory::new);
	}
}
