package com.dreckigesname.firstmod.core.init;

import com.dreckigesname.firstmod.FirstMod;
import com.dreckigesname.firstmod.common.tiles.RainbowAltarTileEntity;
import com.dreckigesname.firstmod.common.tiles.RainbowQuarryTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypeInit {
	
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, FirstMod.MOD_ID);
	
	public static final RegistryObject<TileEntityType<RainbowAltarTileEntity>> RAINBOW_ALTAR_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE.register("rainbow_altar_tile_entity",
			() -> TileEntityType.Builder.of(RainbowAltarTileEntity::new, BlockInit.RAINBOW_ALTAR.get()).build(null));
	
	public static final RegistryObject<TileEntityType<RainbowQuarryTileEntity>> RAINBOW_QUARRY_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE.register("rainbow_quarry_tile_entity",
			() -> TileEntityType.Builder.of(RainbowQuarryTileEntity::new, BlockInit.RAINBOW_QUARRY.get()).build(null));
}
