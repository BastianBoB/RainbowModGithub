package com.dreckigesname.firstmod.common.entities.projectiles;


import com.dreckigesname.firstmod.common.entities.ExplodestrengthTNT;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class TntArrowEntity extends ArrowEntity{
	boolean touchedGround = false;
	
	public TntArrowEntity(World worldIn, LivingEntity shooter) {
		super(worldIn, shooter);
	}

	@Override
	public void tick() {
		if (!this.inGround) {
			ServerWorld serverWorld = (ServerWorld) this.level;
			serverWorld.sendParticles(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 1, 0, 0, 0, 0);
		} else if(touchedGround == false) {
			TNTEntity entity = new ExplodestrengthTNT(level, this.getX(), this.getY(), this.getZ(), 3, 0, null);
			level.addFreshEntity(entity);
			touchedGround = true;
		}
		super.tick();
	}
}
