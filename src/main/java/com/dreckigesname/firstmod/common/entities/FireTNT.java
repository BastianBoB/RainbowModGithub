package com.dreckigesname.firstmod.common.entities;

import javax.annotation.Nullable;

import com.dreckigesname.firstmod.FirstMod;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class FireTNT extends TNTEntity{
	
	float explodeR;
	int fuse;
	
	public FireTNT(EntityType<? extends TNTEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public FireTNT(World worldIn, double x, double y, double z, @Nullable LivingEntity igniter) {
		super(worldIn, x, y, z, igniter);
		explodeR = 10;
		fuse = 100;
		setFuse(fuse);
	}
	
	@Override
	protected void explode() {
		this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), explodeR, Explosion.Mode.BREAK);
		
		int range = 20;
		for(int i = 0; i < 10; i++) {
			double r = random.nextDouble() * 2 * range - range;
			double a = random.nextDouble() * Math.PI * 2;
			int x = (int)(this.getX() + Math.cos(a) * r);
			int z = (int)(this.getZ() + Math.sin(a) * r);
			int y = 255;
			while(!this.level.getBlockState(new BlockPos(x, y, z)).canOcclude()) {
				y--;
			}
			y++;
			level.setBlock(new BlockPos(x, y, z), Blocks.LAVA.defaultBlockState(), 3);
		}
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
			this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, -0.5D, 0.7D));
		}

		--this.fuse;
		if (this.fuse <= 0) {
			this.startExplosion();
		} else {
			this.updateInWaterStateAndDoFluidPushing();
			if (this.level.isClientSide) {
				this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0, 0, 0);
			}
		}

	}
	

}
