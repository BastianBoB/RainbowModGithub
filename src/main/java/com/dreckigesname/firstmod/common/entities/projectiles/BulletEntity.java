package com.dreckigesname.firstmod.common.entities.projectiles;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class BulletEntity extends Entity {

	public BulletEntity(EntityType<?> p_i48580_1_, World p_i48580_2_) {
		super(p_i48580_1_, p_i48580_2_);
	}

	@Override
	protected void defineSynchedData() {

	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {

	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {

	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	public void tick() {
		RayTraceResult raytraceresult = ProjectileHelper.getHitResult(this, this::canHitEntity);
		if (raytraceresult.getType() != RayTraceResult.Type.MISS) {
			this.onHit(raytraceresult);
		}
		Vector3d mov = this.getDeltaMovement();
		double d2 = this.getX() + mov.x;
		double d0 = this.getY() + mov.y;
		double d1 = this.getZ() + mov.z;
		this.setPos(d2, d0, d1);

		this.setDeltaMovement(mov.x, mov.y - (double)this.getGravity(), mov.z);
		super.tick();
	}

	protected boolean canHitEntity(Entity p_230298_1_) {
		return true;
	}

	protected float getGravity() {
		return 0.0F;
	}

	public void shoot(double x, double y, double z, double speed, double acc){
		x += random.nextDouble() * 2 * acc - acc;
		y += random.nextDouble() * 2 * acc - acc;
		z += random.nextDouble() * 2 * acc - acc;
		this.setDeltaMovement(x * speed, y * speed, z * speed);
	}

	protected void onHit(RayTraceResult ray) {
		RayTraceResult.Type raytraceresult$type = ray.getType();
		if (raytraceresult$type == RayTraceResult.Type.ENTITY) {
			System.out.println("ENTITY HIT");
			this.onHitEntity((EntityRayTraceResult)ray);
		} else if (raytraceresult$type == RayTraceResult.Type.BLOCK) {
			//System.out.println("BLOCK HIT");
			this.onHitBlock((BlockRayTraceResult)ray);
		}
	}

	protected void onHitEntity(EntityRayTraceResult ray) {
		ray.getEntity().hurt(DamageSource.GENERIC, 10.0f);
	}

	protected void onHitBlock(BlockRayTraceResult p_230299_1_) {
		this.remove();
	}
}
