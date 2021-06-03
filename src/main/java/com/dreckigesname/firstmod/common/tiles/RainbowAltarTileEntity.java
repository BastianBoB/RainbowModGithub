package com.dreckigesname.firstmod.common.tiles;

import com.dreckigesname.firstmod.FirstMod;
import com.dreckigesname.firstmod.common.containers.RainbowAltarContainer;
import com.dreckigesname.firstmod.core.init.BlockInit;
import com.dreckigesname.firstmod.core.init.ItemInit;
import com.dreckigesname.firstmod.core.init.TileEntityTypeInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class RainbowAltarTileEntity extends LockableLootTileEntity implements ITickableTileEntity {

	public static int slots = 1;
	private static int[] BlockPosX = new int[] { -1, -1, -1, +0, +0, +0, +1, +1, +1, -2, -2, +2, +2, -3, -3, +3, +3, -3, -3, +3, +3};
	private static int[] BlockPosY = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, +0, +0, +0, +0, +1, +1, +1, +1, +2, +2, +2, +2};
	private static int[] BlockPosZ = new int[] { -1, +0, +1, -1, +0, +1, -1, +0, +1, -2, +2, -2, +2, -3, +3, -3, +3, -3, +3, -3, +3};

	private static Block RH = BlockInit.RAINBOW_BLOCK_HOLOGRAM.get();
	private static Block RB = BlockInit.RAINBOW_BLOCK.get();

	private static int totalBlocks = 21;
	private static Block[] correctBlock = new Block[] { RB, RB, RB, RB, RB, RB, RB, RB, RB, RB, RB, RB, RB, RB, RB, RB, RB, RB, RB, RB, RB};
	private static Block[] replaceBlock = new Block[] { RH, RH, RH, RH, RH, RH, RH, RH, RH, RH, RH, RH, RH, RH, RH, RH, RH, RH, RH, RH, RH};

	private static int totalItems = 5;
	private static Item[] inputItems  = new Item[] { ItemInit.RAINBOW_SWORD.get().getItem(), ItemInit.RAINBOW_PICKAXE.get().getItem(), ItemInit.RAINBOW_AXE.get().getItem(), ItemInit.RAINBOW_SHOVEL.get().getItem(), ItemInit.RAINBOW_HOE.get().getItem() };
	private static Item[] outputItems = new Item[] { ItemInit.CHARGED_RAINBOW_SWORD.get().getItem(), ItemInit.CHARGED_RAINBOW_PICKAXE.get().getItem(), ItemInit.CHARGED_RAINBOW_AXE.get().getItem(), ItemInit.CHARGED_RAINBOW_SHOVEL.get().getItem(), ItemInit.CHARGED_RAINBOW_HOE.get().getItem() };

	protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

	private boolean ritualRunning = false;
	private int animationTickCount = 0;

	public RainbowAltarTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	public RainbowAltarTileEntity() {
		this(TileEntityTypeInit.RAINBOW_ALTAR_TILE_ENTITY_TYPE.get());
	}

	@Override
	public int getContainerSize() {
		return slots;
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	public ItemStack getItem() {
		return this.items.get(0);
	}

	@Override
	protected void setItems(NonNullList<ItemStack> itemsIn) {
		this.items = itemsIn;
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + FirstMod.MOD_ID + "rainbow_altar");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new RainbowAltarContainer(id, player, this);
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);
		ItemStackHelper.saveAllItems(compound, items);
		return compound;
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		super.load(state, nbt);
		this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(nbt, this.items);
	}


	public void tryActiviation(PlayerEntity player) {
		if (!ritualRunning) {
			int x = this.getBlockPos().getX();
			int y = this.getBlockPos().getY();
			int z = this.getBlockPos().getZ();
			World worldIn = this.getLevel();
			
	    	for (int i = 0; i < totalBlocks; i++) {
				setBlock(x + BlockPosX[i], y + BlockPosY[i], z + BlockPosZ[i], replaceBlock[i], correctBlock[i], worldIn);
			}

	    	if(isStructureCorrect()){
				startRitual(player);
			}
		}
	}

	public void startRitual(PlayerEntity player) {
		ritualRunning = true;
		animationTickCount = 0;
		player.inventory.getSelected().shrink(1);
	}

	public void stopRitual() {
		ritualRunning = false;
		animationTickCount = 0;
	}

	public void completeRitual() {
		stopRitual();

		int x = this.getBlockPos().getX();
		int y = this.getBlockPos().getY();
		int z = this.getBlockPos().getZ();
		
		for(int i = 0; i < totalItems; i++) {
			if (this.getItem().getItem() == inputItems[i].getItem()) {
				ItemEntity item = new ItemEntity(this.level, x, y + 1, z, new ItemStack(outputItems[i]));
				this.level.addFreshEntity(item);
				items = NonNullList.withSize(slots, ItemStack.EMPTY);
				break;
			}
		}
	}

	public boolean isStructureCorrect() {
		int x = this.getBlockPos().getX();
		int y = this.getBlockPos().getY();
		int z = this.getBlockPos().getZ();
		World worldIn = this.getLevel();
		for (int i = 0; i < totalBlocks; i++) {
			if (!isBlock(x + BlockPosX[i], y + BlockPosY[i], z + BlockPosZ[i], correctBlock[i], worldIn)) {
				return false;
			}
		}
		return true;
	}

	public boolean isItemCorrect() {
		for (int i = 0; i < totalItems; i++) {
			if (this.getItem().getItem() == inputItems[i].getItem()) {
				return true;
			}
		}
		return false;
	}

	private void setBlock(int x, int y, int z, Block replaceBlock, Block correctBlock, World worldIn) {
		BlockPos pos = new BlockPos(x, y, z);
		Block targetBlock = worldIn.getBlockState(pos).getBlock();
		if (targetBlock != Blocks.BEDROCK && targetBlock != replaceBlock && targetBlock != correctBlock) {
			worldIn.destroyBlock(pos, true);
			worldIn.setBlock(pos, replaceBlock.defaultBlockState(), 3);
		}
	}

	private boolean isBlock(int x, int y, int z, Block block, World worldIn) {
		BlockPos pos = new BlockPos(x, y, z);
		return worldIn.getBlockState(pos) == block.defaultBlockState();
	}

	@Override
	public void tick() {
		if (!this.level.isClientSide) {
			if (ritualRunning) {
				if (!isItemCorrect() || !isStructureCorrect()) {
					stopRitual();
				}
				
				ServerWorld serverWorld = (ServerWorld) this.getLevel();
				double x = (double) this.getBlockPos().getX() + 0.5D;
				double y = (double) this.getBlockPos().getY() + 0;
				double z = (double) this.getBlockPos().getZ() + 0.5D;
				
				if(animationTickCount > 0 && animationTickCount < 20) {
					serverWorld.sendParticles(ParticleTypes.DRAGON_BREATH, x - 3, y + 4, z - 3, 4, 0.0D, 0.0D, 0.0D, 0.02);
					serverWorld.sendParticles(ParticleTypes.DRAGON_BREATH, x - 3, y + 4, z + 3, 4, 0.0D, 0.0D, 0.0D, 0.02);
					serverWorld.sendParticles(ParticleTypes.DRAGON_BREATH, x + 3, y + 4, z - 3, 4, 0.0D, 0.0D, 0.0D, 0.02);
					serverWorld.sendParticles(ParticleTypes.DRAGON_BREATH, x + 3, y + 4, z + 3, 4, 0.0D, 0.0D, 0.0D, 0.02);
				}

				if (animationTickCount > 20 && animationTickCount < 29) {
					int pAmount = 1;
					for(double r = 0; r < (animationTickCount - 20)*4; r += 1) {
						serverWorld.sendParticles(ParticleTypes.DRAGON_BREATH, x - 3 + r/10.0D, y + 4 + r/20.0D, z - 3 + r/10.0D, pAmount, 0.0D, 0.0D, 0.0D, 0.0);
						serverWorld.sendParticles(ParticleTypes.DRAGON_BREATH, x - 3 + r/10.0D, y + 4 + r/20.0D, z + 3 - r/10.0D, pAmount, 0.0D, 0.0D, 0.0D, 0.0);
						serverWorld.sendParticles(ParticleTypes.DRAGON_BREATH, x + 3 - r/10.0D, y + 4 + r/20.0D, z - 3 + r/10.0D, pAmount, 0.0D, 0.0D, 0.0D, 0.0);
						serverWorld.sendParticles(ParticleTypes.DRAGON_BREATH, x + 3 - r/10.0D, y + 4 + r/20.0D, z + 3 - r/10.0D, pAmount, 0.0D, 0.0D, 0.0D, 0.0);
					}
				}
				
				if(animationTickCount == 29) {
					serverWorld.sendParticles(ParticleTypes.SMOKE, x, y + 5.6, z, 1000, 0.0D, 0.0D, 0.0D, 0.2);
				}
				
				
				if(animationTickCount > 40 && animationTickCount < 170) {
					serverWorld.sendParticles(ParticleTypes.DRAGON_BREATH, x, y + 5.6, z, 10, 0.0D, 0.0D, 0.0D, 0.02);
					
					for(int i = 0; i < 2; i++) {
						serverWorld.sendParticles(ParticleTypes.LAVA, x, y + 5.6, z, 1, 0.0D, 0.0D, 0.0D, 0.0);
					}
				}
				

				if (animationTickCount > 180) {
					LightningBoltEntity entity = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, this.getLevel());
					entity.setPos(x - 4, y + 3, z - 4);
					this.getLevel().addFreshEntity(entity);
					entity = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, this.getLevel());
					entity.setPos(x - 4, y + 3, z + 4);
					this.getLevel().addFreshEntity(entity);
					entity = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, this.getLevel());
					entity.setPos(x + 4, y + 3, z - 4);
					this.getLevel().addFreshEntity(entity);
					entity = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, this.getLevel());
					entity.setPos(x + 4, y + 3, z + 2);
					this.getLevel().addFreshEntity(entity);
					
					completeRitual();
				}

				animationTickCount++;
			}
		}
	}
}
