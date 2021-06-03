package com.dreckigesname.firstmod.core.init;

import com.dreckigesname.firstmod.FirstMod;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ParticleInit {
	
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, FirstMod.MOD_ID);
	
	public static final RegistryObject<BasicParticleType> RED_PARTICLE = PARTICLES.register("red_particle",
			() -> new BasicParticleType(true));
	
	public static final RegistryObject<BasicParticleType> ORANGE_PARTICLE = PARTICLES.register("orange_particle",
			() -> new BasicParticleType(true));
	
	public static final RegistryObject<BasicParticleType> YELLOW_PARTICLE = PARTICLES.register("yellow_particle",
			() -> new BasicParticleType(true));
	
	public static final RegistryObject<BasicParticleType> GREEN_PARTILE = PARTICLES.register("green_particle",
			() -> new BasicParticleType(true));
	
	public static final RegistryObject<BasicParticleType> BLUE_PARTICLE = PARTICLES.register("blue_particle",
			() -> new BasicParticleType(true));
	
	public static final RegistryObject<BasicParticleType> PURPLE_PARTICLE = PARTICLES.register("purple_particle",
			() -> new BasicParticleType(true));
	
	public static final RegistryObject<BasicParticleType> WHITE_PARTICLE = PARTICLES.register("white_particle",
			() -> new BasicParticleType(true));
}
