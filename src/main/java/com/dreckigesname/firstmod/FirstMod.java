package com.dreckigesname.firstmod;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dreckigesname.firstmod.core.init.BlockInit;
import com.dreckigesname.firstmod.core.init.ContainerTypeInit;
import com.dreckigesname.firstmod.core.init.EntityTypeInit;
import com.dreckigesname.firstmod.core.init.ItemInit;
import com.dreckigesname.firstmod.core.init.ParticleInit;
import com.dreckigesname.firstmod.core.init.TileEntityTypeInit;
import com.dreckigesname.firstmod.world.OreGeneration;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FirstMod.MOD_ID)
public class FirstMod {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "firstmod";
	public static final ItemGroup RAINBOW_GROUP = new RainbowGroup();

	public static double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
	}

	public FirstMod() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		ItemInit.ITEMS.register(bus);
		BlockInit.BLOCKS.register(bus);
		EntityTypeInit.ENTITY_TYPES.register(bus);
		TileEntityTypeInit.TILE_ENTITY_TYPE.register(bus);
		ContainerTypeInit.CONTAINER_TYPES.register(bus);
		ParticleInit.PARTICLES.register(bus);

		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);
		MinecraftForge.EVENT_BUS.register(this);
	}



	public static class RainbowGroup extends ItemGroup {
		public RainbowGroup() {
			super("rainbow_mod_tab");
		}

		@Override
		public ItemStack makeIcon() {
			return ItemInit.RAINBOW_CRYSTAL.get().getDefaultInstance();
		}
		
		@Override
		public void fillItemList(NonNullList<ItemStack> items) {
			super.fillItemList(items);
		}

	}
}
