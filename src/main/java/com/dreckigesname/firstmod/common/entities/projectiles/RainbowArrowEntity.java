package com.dreckigesname.firstmod.common.entities.projectiles;

import com.dreckigesname.firstmod.core.init.ParticleInit;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class RainbowArrowEntity extends ArrowEntity {

	public RainbowArrowEntity(World worldIn, LivingEntity shooter) {
		super(worldIn, shooter);
	}


	@Override
	protected void doPostHurtEffects(LivingEntity living) {
		super.doPostHurtEffects(living);

		living.setSecondsOnFire(10);
		living.addEffect(new EffectInstance(Effects.GLOWING, 200, 0));
	}

	@Override
	public void tick() {

		if (!this.inGround) {
			ServerWorld serverWorld = (ServerWorld) this.level;
			serverWorld.sendParticles(ParticleInit.RED_PARTICLE.get(), this.getX(), this.getY(), this.getZ(), 1, 0, 0, 0, 0);
			serverWorld.sendParticles(ParticleInit.YELLOW_PARTICLE.get(), this.getX(), this.getY(), this.getZ(), 1, 0, 0, 0, 0);
		}
		super.tick();
	}
}
