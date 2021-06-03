package com.dreckigesname.firstmod.common.entities.projectiles;

import com.dreckigesname.firstmod.core.init.ParticleInit;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class IceArrowEntity extends ArrowEntity {
	boolean touchedGround = false;

	public IceArrowEntity(World worldIn, LivingEntity shooter) {
		super(worldIn, shooter);
	}

	@Override
	protected void doPostHurtEffects(LivingEntity living) {
		super.doPostHurtEffects(living);

		living.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 200, 0));
	}

	@Override
	public void tick() {

		if (!this.inGround) {
			ServerWorld serverWorld = (ServerWorld) this.level;
			serverWorld.sendParticles(ParticleInit.BLUE_PARTICLE.get(), this.getX(), this.getY(), this.getZ(),1, 0, 0, 0, 0);
			serverWorld.sendParticles(ParticleInit.WHITE_PARTICLE.get(), this.getX(), this.getY(),this.getZ(), 1, 0, 0, 0, 0);
		} else if(touchedGround == false) {
			level.setBlock(new BlockPos(this.getLookAngle()), Blocks.SOUL_FIRE.defaultBlockState(), 3);
		}
		super.tick();
	}

}
