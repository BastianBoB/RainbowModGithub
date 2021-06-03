package com.dreckigesname.firstmod.common.entities.projectiles;


import com.dreckigesname.firstmod.core.init.ParticleInit;

import net.minecraft.block.trees.OakTree;
import net.minecraft.block.trees.Tree;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class NatureArrowEntity extends ArrowEntity{
	public NatureArrowEntity(World worldIn, LivingEntity shooter) {
		super(worldIn, shooter);
	}

	@Override
	protected void doPostHurtEffects(LivingEntity living) {
		super.doPostHurtEffects(living);

		living.setSecondsOnFire(10);
		living.addEffect(new EffectInstance(Effects.GLOWING, 200, 0));
	}

	@Override
	protected void onHitBlock(BlockRayTraceResult ray) {
		System.out.println("NATURE ARROW HIT BLOCK");
		BlockPos pos = ray.getBlockPos().offset(0, 1, 0);
		ServerWorld serverWorld = (ServerWorld) this.level;
		Tree tree = new OakTree();
		//tree.growTree(serverWorld, serverWorld.getChunkSource().getGenerator(), pos, serverWorld.getBlockState(pos), random);

	}

	@Override
	public void tick() {
		if (!this.inGround) {
			ServerWorld serverWorld = (ServerWorld) this.level;
			serverWorld.sendParticles(ParticleInit.GREEN_PARTILE.get(), this.getX(), this.getY(), this.getZ(), 1, 0, 0, 0, 0);
		}
		if(this.inGroundTime == 1) {
		}
		super.tick();
	}
}
