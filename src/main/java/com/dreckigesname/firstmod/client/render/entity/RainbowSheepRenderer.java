package com.dreckigesname.firstmod.client.render.entity;

import com.dreckigesname.firstmod.FirstMod;
import com.dreckigesname.firstmod.client.entity.model.RainbowSheepModel;
import com.dreckigesname.firstmod.common.entities.RainbowSheepEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RainbowSheepRenderer extends MobRenderer<RainbowSheepEntity, RainbowSheepModel<RainbowSheepEntity>> {

	
	protected static final ResourceLocation TEXTURE = new ResourceLocation(FirstMod.MOD_ID, "textures/entity/rainbow_sheep.png");
	
	public RainbowSheepRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new RainbowSheepModel<>(), 0.7f);
	}

	@Override
	public ResourceLocation getTextureLocation(RainbowSheepEntity entity) {
		return TEXTURE;
	}

}
