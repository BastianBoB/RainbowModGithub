package com.dreckigesname.firstmod.common.blocks;


import com.dreckigesname.firstmod.common.tiles.RainbowAltarTileEntity;
import com.dreckigesname.firstmod.core.init.TileEntityTypeInit;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class RainbowAltar extends BaseHorizontalBlock{
	
	public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 6, 16);
	
	public RainbowAltar() {
		super(AbstractBlock.Properties.of(Material.GLASS)
				.strength(10f)
				.sound(SoundType.METAL)
				.noOcclusion()
				.harvestTool(ToolType.PICKAXE)
				.requiresCorrectToolForDrops());
		runCalculation(SHAPE);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypeInit.RAINBOW_ALTAR_TILE_ENTITY_TYPE.get().create();
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES.get(state.getValue(HORIZONTAL_FACING));
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult hit) {
		
		
		if(!worldIn.isClientSide) {
			TileEntity te = worldIn.getBlockEntity(pos);
			if(te instanceof RainbowAltarTileEntity) {
				if(player.getMainHandItem().getItem() == Items.NETHER_STAR) {
					 ((RainbowAltarTileEntity) te).tryActiviation(player);
				} else {
					NetworkHooks.openGui((ServerPlayerEntity) player, (RainbowAltarTileEntity) te, pos);
				}
			}
		}
		return super.use(state, worldIn, pos, player, handIn, hit);
	}
	
	
}
