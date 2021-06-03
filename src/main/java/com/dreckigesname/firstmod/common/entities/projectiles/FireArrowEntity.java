package com.dreckigesname.firstmod.common.entities.projectiles;

import com.dreckigesname.firstmod.core.init.ParticleInit;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class FireArrowEntity extends ArrowEntity {
	
	public FireArrowEntity(World worldIn, LivingEntity shooter) {
		super(worldIn, shooter);
	}

	@Override
	protected void doPostHurtEffects(LivingEntity living) {
		super.doPostHurtEffects(living);
		living.setSecondsOnFire(10);
	}

	@Override
	protected void onHitBlock(BlockRayTraceResult ray) {
		super.onHitBlock(ray);
		BlockPos pos = ray.getBlockPos().offset(0, 1, 0);
		level.setBlock(pos, Blocks.FIRE.defaultBlockState(), 3);
		this.remove();
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
