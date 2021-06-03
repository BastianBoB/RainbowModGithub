package com.dreckigesname.firstmod.core.init;


import com.dreckigesname.firstmod.FirstMod;
import com.dreckigesname.firstmod.common.entities.RainbowSheepEntity;
import com.dreckigesname.firstmod.common.entities.projectiles.BulletEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypeInit {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES  = DeferredRegister.create(ForgeRegistries.ENTITIES, FirstMod.MOD_ID);
	
	public static final RegistryObject<EntityType<RainbowSheepEntity>> RAINBOW_SHEEP = ENTITY_TYPES.register("rainbow_sheep",
			() -> EntityType.Builder.of(RainbowSheepEntity::new, EntityClassification.CREATURE)
			.sized(1.0f, 1.0f)
			.build(new ResourceLocation(FirstMod.MOD_ID, "rainbow_sheep").toString()));
	
	public static final RegistryObject<EntityType<BulletEntity>> BULLET_ENTITY = ENTITY_TYPES.register("bullet",
			() -> EntityType.Builder.of(BulletEntity::new, EntityClassification.AMBIENT)
			.sized(0.1f, 0.1f)
			.build(new ResourceLocation(FirstMod.MOD_ID, "bullet").toString()));
}
