package com.dreckigesname.firstmod.core.init;

import com.dreckigesname.firstmod.FirstMod;
import com.dreckigesname.firstmod.common.blocks.FaceBlock;
import com.dreckigesname.firstmod.common.blocks.RainbowAltar;
import com.dreckigesname.firstmod.common.blocks.RainbowQuarry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			FirstMod.MOD_ID);

	public static final RegistryObject<Block> RAINBOW_BLOCK = BLOCKS.register("rainbow_block",
			() -> new Block(AbstractBlock.Properties.of(Material.METAL).strength(10f, 20f).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().harvestLevel(2).sound(SoundType.METAL)));

	public static final RegistryObject<Block> BREAD_BLOCK = BLOCKS.register("bread_block",
			() -> new Block(AbstractBlock.Properties.of(Material.CLAY).strength(0.8f, 2f).sound(SoundType.BAMBOO).friction(1f)));

	public static final RegistryObject<Block> FACE_BLOCK = BLOCKS.register("face_block",
			() -> new FaceBlock());
	
	public static final RegistryObject<Block> RAINBOW_ORE = BLOCKS.register("rainbow_ore",
			() -> new Block(AbstractBlock.Properties.copy(Blocks.IRON_ORE)));
	
	public static final RegistryObject<Block> RAINBOW_BLOCK_HOLOGRAM = BLOCKS.register("rainbow_block_hologram",
			() -> new Block(AbstractBlock.Properties.of(Material.METAL).instabreak().noCollission().sound(SoundType.STONE)));
	
	public static final RegistryObject<Block> RAINBOW_ALTAR = BLOCKS.register("rainbow_altar",
			() -> new RainbowAltar());
	
	public static final RegistryObject<Block> RAINBOW_QUARRY = BLOCKS.register("rainbow_quarry",
			() -> new RainbowQuarry());
	
	public static final RegistryObject<Block> SPEED_MODIFIER = BLOCKS.register("speed_modifier",
			() -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
	
	public static final RegistryObject<Block> LUCK_MODIFIER = BLOCKS.register("luck_modifier",
			() -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
	
	public static final RegistryObject<Block> SILKTOUCH_MODIFIER = BLOCKS.register("silktouch_modifier",
			() -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
	
	public static final RegistryObject<Block> ORE_MODIFIER = BLOCKS.register("ore_modifier",
			() -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
	
	public static final RegistryObject<Block> RANGE_MODIFIER = BLOCKS.register("range_modifier",
			() -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
	

}
