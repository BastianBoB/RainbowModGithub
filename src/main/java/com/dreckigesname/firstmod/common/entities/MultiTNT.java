package com.dreckigesname.firstmod.common.entities;


import javax.annotation.Nullable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class MultiTNT extends TNTEntity {

	float explodeR;

	MultiTNT(EntityType<? extends TNTEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public MultiTNT(World worldIn, double x, double y, double z, float explodeR, int fuse, @Nullable LivingEntity igniter) {
		super(worldIn, x, y, z, igniter);
		this.explodeR = explodeR;
		setFuse(fuse);
	}

	@Override
	protected void explode() {
		this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 1, Explosion.Mode.BREAK);
		
		for (int y = 1; y < explodeR / 2; y += 8) {
			for (int i = 1; i < explodeR; i += 8) {
				double r = (i-y);
				double increment = Math.max(Math.PI * 3 / i, 0.05);
				for (double a = 0; a < Math.PI * 2; a += increment) {
					double x = r * Math.cos(a);
					double z = r * Math.sin(a);
					this.level.explode(this, this.getX() + x, this.getY(0.0625D) - y, this.getZ() + z, 12, Explosion.Mode.BREAK);
				}

			}

		}

	}

}
