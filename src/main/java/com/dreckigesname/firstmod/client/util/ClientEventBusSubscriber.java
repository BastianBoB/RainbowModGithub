package com.dreckigesname.firstmod.client.util;

import com.dreckigesname.firstmod.FirstMod;
import com.dreckigesname.firstmod.client.render.entity.BulletRenderer;
import com.dreckigesname.firstmod.client.render.entity.RainbowSheepRenderer;
import com.dreckigesname.firstmod.client.render.tileentity.RainbowAltarTileEntityRenderer;
import com.dreckigesname.firstmod.client.screen.RainbowAltarScreen;
import com.dreckigesname.firstmod.common.entities.RainbowSheepEntity;
import com.dreckigesname.firstmod.common.entities.projectiles.BulletEntity;
import com.dreckigesname.firstmod.core.init.BlockInit;
import com.dreckigesname.firstmod.core.init.ContainerTypeInit;
import com.dreckigesname.firstmod.core.init.EntityTypeInit;
import com.dreckigesname.firstmod.core.init.ItemInit;
import com.dreckigesname.firstmod.core.init.KeyBindInit;
import com.dreckigesname.firstmod.core.init.TileEntityTypeInit;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.item.ArmorStandEntity;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {


	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.RAINBOW_SHEEP.get(), RainbowSheepRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.BULLET_ENTITY.get(), BulletRenderer::new);


		ItemModelsProperties.register(ItemInit.RAINBOW_BOW.get(), new ResourceLocation("pull"),
				(p_239429_0_, p_239429_1_, p_239429_2_) -> {
					if (p_239429_2_ == null) {
						return 0.0F;
					} else {
						return p_239429_2_.getUseItem() != p_239429_0_ ? 0.0F
								: (float) (p_239429_0_.getUseDuration() - p_239429_2_.getUseItemRemainingTicks()) / 20.0F;
					}
				});
		ItemModelsProperties.register(ItemInit.RAINBOW_BOW.get(), new ResourceLocation("pulling"),
				(p_239428_0_, p_239428_1_, p_239428_2_) -> {
					return p_239428_2_ != null && p_239428_2_.isUsingItem()
							&& p_239428_2_.getUseItem() == p_239428_0_ ? 1.0F : 0.0F;
				});

		RenderTypeLookup.setRenderLayer(BlockInit.RAINBOW_BLOCK_HOLOGRAM.get(), RenderType.translucent());
		RenderTypeLookup.setRenderLayer(BlockInit.RAINBOW_ALTAR.get(), RenderType.cutout());

		ScreenManager.register(ContainerTypeInit.RAINBOW_ALTAR_CONTAINER_TYPE.get(), RainbowAltarScreen::new);
		ClientRegistry.bindTileEntityRenderer(TileEntityTypeInit.RAINBOW_ALTAR_TILE_ENTITY_TYPE.get(),
				RainbowAltarTileEntityRenderer::new);

		KeyBindInit.register(event);
	}


	@SubscribeEvent
	public static void setAttributes(EntityAttributeCreationEvent event) {
		//event.put(EntityTypeInit.BULLET_ENTITY.get(), BulletEntity.createAttributes().build());
		event.put(EntityTypeInit.RAINBOW_SHEEP.get(), RainbowSheepEntity.createAttributes().build());
	}
}
