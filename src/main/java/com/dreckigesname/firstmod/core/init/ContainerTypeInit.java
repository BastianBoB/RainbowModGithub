package com.dreckigesname.firstmod.core.init;

import com.dreckigesname.firstmod.FirstMod;
import com.dreckigesname.firstmod.common.containers.RainbowAltarContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypeInit {
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, FirstMod.MOD_ID);
	
	public static final RegistryObject<ContainerType<RainbowAltarContainer>> RAINBOW_ALTAR_CONTAINER_TYPE = CONTAINER_TYPES.register("rainbow_altar",
			() -> IForgeContainerType.create(RainbowAltarContainer::new));
}
