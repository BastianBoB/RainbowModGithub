package com.dreckigesname.firstmod.common.entities;

import javax.annotation.Nullable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class GroundexplodeTNT extends TNTEntity {
	float explodeR;
	int fuse;

	public GroundexplodeTNT(EntityType<? extends TNTEntity> type, World worldIn) {
		super(EntityType.TNT, worldIn);
	}

	public GroundexplodeTNT(World worldIn, double x, double y, double z, @Nullable LivingEntity igniter) {
		super(worldIn, x, y, z, igniter);
		explodeR = 5;
		fuse = 200;
		setFuse(fuse);
	}

	@Override
	protected void explode() {
		this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), explodeR, Explosion.Mode.BREAK);
	}

	protected void startExplosion() {
		this.remove();
		if (!this.level.isClientSide) {
			this.explode();
		}
	}

	@Override
	public void tick() {
		if (!this.isNoGravity()) {
			this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
		}

		this.move(MoverType.SELF, this.getDeltaMovement());
		this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));

		if (this.onGround) {
			this.startExplosion();
		}

		--this.fuse;
		if (this.fuse <= 0) {
			this.startExplosion();
		} else {
			this.updateInWaterStateAndDoFluidPushing();
			if (this.level.isClientSide) {
				this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
			}
		}

	}
}