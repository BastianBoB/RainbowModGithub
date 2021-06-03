package com.dreckigesname.firstmod.client.render.entity;

import com.dreckigesname.firstmod.FirstMod;
import com.dreckigesname.firstmod.client.entity.model.BulletModel;
import com.dreckigesname.firstmod.common.entities.projectiles.BulletEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class BulletRenderer<T extends BulletEntity> extends EntityRenderer<T> {
	
	protected static final ResourceLocation TEXTURE = new ResourceLocation(FirstMod.MOD_ID, "textures/entity/bullet.png");
	protected final BulletModel<?> modelBullet = new BulletModel<>();
	
	public BulletRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn);
	}
	

	@Override
	public ResourceLocation getTextureLocation(T entity) {
		return TEXTURE;
	}
	
	@Override
	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
	      IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.modelBullet.renderType(this.getTextureLocation(entityIn)));
	      this.modelBullet.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

}
