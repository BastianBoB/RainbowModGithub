package com.dreckigesname.firstmod.common.entities;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ExplodestrengthTNT extends TNTEntity{

	float explodeR;

	public ExplodestrengthTNT(EntityType<? extends TNTEntity> type, World worldIn) {
		super(EntityType.TNT, worldIn);
	}
	
	public ExplodestrengthTNT(World worldIn, double x, double y, double z, float explodeR, int fuse, @Nullable LivingEntity igniter) {
		super(worldIn, x, y, z, igniter);
		this.explodeR = explodeR;
		setFuse(fuse);
	}

	@Override
	protected void explode() {
		this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), explodeR, Explosion.Mode.BREAK);
	}
	
}
