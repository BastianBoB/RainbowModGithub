package com.dreckigesname.firstmod.common.blocks;

import javax.annotation.Nonnull;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;

import java.util.Random;

public class FaceBlock extends FallingBlock {

	@Override
	protected void createBlockStateDefinition(@Nonnull StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

	public FaceBlock() {
		super(AbstractBlock.Properties.of(Material.WOOL).strength(15f, 20f).harvestTool(ToolType.AXE)
				.requiresCorrectToolForDrops().harvestLevel(0).sound(SoundType.SLIME_BLOCK).jumpFactor(2.5f));

		registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
	}
}
