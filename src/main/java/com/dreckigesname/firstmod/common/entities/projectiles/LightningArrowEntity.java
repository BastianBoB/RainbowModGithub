package com.dreckigesname.firstmod.common.entities.projectiles;

import com.dreckigesname.firstmod.core.init.ParticleInit;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class LightningArrowEntity extends ArrowEntity{
	public LightningArrowEntity(World worldIn, LivingEntity shooter) {
		super(worldIn, shooter);
	}

	@Override
	protected void doPostHurtEffects(LivingEntity living) {
		super.doPostHurtEffects(living);
		living.addEffect(new EffectInstance(Effects.GLOWING, 200, 0));
	}

	@Override
	public void tick() {
		if (!this.inGround) {
			ServerWorld serverWorld = (ServerWorld) this.level;
			serverWorld.sendParticles(ParticleInit.WHITE_PARTICLE.get(), this.getX(), this.getY(), this.getZ(), 1, 0, 0, 0, 0);
			serverWorld.sendParticles(ParticleInit.YELLOW_PARTICLE.get(), this.getX(), this.getY(), this.getZ(), 1, 0, 0, 0, 0);
		}
		if(this.inGroundTime == 1) {
			LightningBoltEntity entity = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, level);
			entity.setPos(this.getX(), this.getX(), this.getX());
			level.addFreshEntity(entity);
		}
		super.tick();
	}
}
