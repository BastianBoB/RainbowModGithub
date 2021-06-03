package com.dreckigesname.firstmod.common.tiles;

import java.util.List;

import com.dreckigesname.firstmod.core.init.BlockInit;
import com.dreckigesname.firstmod.core.init.TileEntityTypeInit;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class RainbowQuarryTileEntity extends TileEntity implements ITickableTileEntity {

	private int modifierRadius = 1;
	private int diameter = 1;
	private int mineSpeed = 2;
	private float fortuneLevel;
	private boolean hasSilktouchModifier = false;
	private boolean hasOreModifier = false;
	private int mineX = 0;
	private int mineY = -1;
	private int mineZ = 0;
	private int mineTickCount = 0;
	private boolean isMining = true;

	private ItemStack toolStack = Items.DIAMOND_PICKAXE.getDefaultInstance();

	public RainbowQuarryTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	public RainbowQuarryTileEntity() {
		this(TileEntityTypeInit.RAINBOW_QUARRY_TILE_ENTITY_TYPE.get());
	}

	public void calculateModifiers() {
		int oldDiameter = diameter;
		mineSpeed = 4;
		diameter = 3;
		fortuneLevel = 0;
		hasSilktouchModifier = false;
		hasOreModifier = false;
		for (int x = -modifierRadius; x < modifierRadius + 1; x++) {
			for (int z = -modifierRadius; z < modifierRadius + 1; z++) {
				BlockPos pos = new BlockPos(this.getBlockPos().getX() + x, this.getBlockPos().getY(), this.getBlockPos().getZ() + z);
				Block block = this.getLevel().getBlockState(pos).getBlock();
				if (block == BlockInit.SPEED_MODIFIER.get()) {
					mineSpeed += 8;
				}
				if (block == BlockInit.SILKTOUCH_MODIFIER.get()) {
					hasSilktouchModifier = true;
				}
				if (block == BlockInit.ORE_MODIFIER.get()) {
					hasOreModifier = true;
				}
				if (block == BlockInit.RANGE_MODIFIER.get()) {
					diameter += 4;
				}
				if (block == BlockInit.LUCK_MODIFIER.get()) {
					fortuneLevel += 0.5;
				}
			}
		}

		if (diameter != oldDiameter) {
			mineX = 0;
			mineY = -1;
			mineZ = 0;
			mineTickCount = 0;
			isMining = true;
		}

		toolStack = Items.DIAMOND_PICKAXE.getDefaultInstance();
		toolStack.enchant(Enchantments.BLOCK_FORTUNE, (int) fortuneLevel);
		if (hasSilktouchModifier) {
			toolStack.enchant(Enchantments.SILK_TOUCH, 1);
		}

		System.out.println(fortuneLevel);
	}

	@Override
	public void tick() {
		if (!level.isClientSide) {
			if (isMining) {
				for (int i = 0; i < Math.max(mineSpeed / 100, 1); i++) {
					if (mineTickCount % Math.max(100 / mineSpeed, 1) == 0) {
						BlockPos pos = getBlockPos();
						BlockPos blockPos = new BlockPos(pos.getX() + mineX - diameter / 2, pos.getY() +  mineY, pos.getZ() + mineZ - diameter / 2);
						Block block = this.getLevel().getBlockState(blockPos).getBlock();
						ServerWorld world = (ServerWorld) this.getLevel();
						world.sendParticles(ParticleTypes.DRAGON_BREATH, blockPos.getX() + 0.5D, blockPos.getY() + 1.5D, blockPos.getZ() + 0.5D, 1, 0.0D, 0.0D, 0.0D, 0.0D);

						if (!(block == Blocks.BEDROCK || (hasOreModifier && !Tags.Blocks.ORES.contains(block)))) {
							insertItems(blockPos);
							world.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);
						}

						mineX++;
						if (mineX >= diameter) {
							mineX = 0;
							mineZ++;
							if (mineZ >= diameter) {
								mineZ = 0;
								mineY--;
								if (blockPos.getY() + mineY < 1) {
									isMining = false;
								}
							}
						}
					}
					mineTickCount++;
				}
			}
		}
	}
	
	private void insertItems(BlockPos blockPos) {
		World world = this.getLevel();
		List<ItemStack> items = Block.getDrops(world.getBlockState(blockPos), (ServerWorld) world, blockPos, world.getBlockEntity(getBlockPos()), null, toolStack);
		TileEntity chest = world.getBlockEntity(getBlockPos().above());
		if (chest != null) {
			LazyOptional<?> handler = chest.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
			handler.resolve().ifPresent(ItemStackHandler -> {
				if (ItemStackHandler instanceof InvWrapper) {
					items.forEach(stack -> {
						for (int j = 0; j < ((InvWrapper) ItemStackHandler).getSlots(); ++j) {
							stack = ((InvWrapper) ItemStackHandler).insertItem(j, stack, false);
						}
						ItemEntity itemEntity = new ItemEntity(world, this.getBlockPos().getX() + 0.5D, this.getBlockPos().getY() + 2, this.getBlockPos().getZ() + 0.5D, stack);
						itemEntity.setDeltaMovement(0.0D, 0.0D, 0.0D);
						world.addFreshEntity(itemEntity);
					});
				}
			});
		} else {
			items.forEach(stack -> {
				ItemEntity itemEntity = new ItemEntity(world, this.getBlockPos().getX() + 0.5D, this.getBlockPos().getY() + 2, this.getBlockPos().getZ() + 0.5D, stack);
				itemEntity.setDeltaMovement(0.0D, 0.0D, 0.0D);
				world.addFreshEntity(itemEntity);
			});
		}
	}
}
