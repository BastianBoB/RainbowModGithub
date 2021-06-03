package com.dreckigesname.firstmod.common.entities.projectiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.EggEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BridgeEggEntity extends EggEntity {

	ArrayList<BlockPos> lastPoses = new ArrayList<BlockPos>();
	boolean dead = false;
	HashMap<BlockPos, Integer> posTickMap = new HashMap<BlockPos, Integer>();

	public BridgeEggEntity(World worldIn, LivingEntity throwerIn) {
		super(worldIn, throwerIn);
	}

	@Override
	public void tick() {
		super.tick();
		lastPoses.add(this.blockPosition());
		if (lastPoses.size() > 5) {
			lastPoses.remove(0);
			for (int x = -1; x < 2; x++) {
				for (int z = -1; z < 2; z++) {
					BlockPos pos = lastPoses.get(0).offset(x, -2, z);
					if (this.level.getBlockState(pos).getBlock() == Blocks.AIR.getBlock()) {
						this.level.setBlock(pos, Blocks.SANDSTONE.defaultBlockState(), 3);
						posTickMap.put(pos, 0);
					}
				}
			}
		}

		Iterator<Entry<BlockPos, Integer>> iterator = this.posTickMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<BlockPos, Integer> entry = iterator.next();
			entry.setValue(entry.getValue() + 1);
			if (entry.getValue() >= 200) {
				if (this.level.getBlockState(entry.getKey()).getBlock() == Blocks.SANDSTONE.getBlock()) {
					this.level.setBlock(entry.getKey(), Blocks.AIR.defaultBlockState(), 3);
				}
				iterator.remove();
			}
		}

		if (dead) {
			this.setDeltaMovement(0, 0, 0);
			if (posTickMap.size() < 1) {
				this.remove();
			}
		}
	}

	@Override
	protected void onHit(RayTraceResult result) {
		dead = true;
	}

}
