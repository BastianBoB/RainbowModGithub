package com.dreckigesname.firstmod.client.entity.model;

import com.dreckigesname.firstmod.common.entities.projectiles.BulletEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class BulletModel<T extends BulletEntity> extends EntityModel<T>{

	private final ModelRenderer bb_main;

	public BulletModel() {
		texWidth = 16;
		texWidth = 16;

		bb_main = new ModelRenderer(this);
		bb_main.setPos(0.0F, 0.0F, 0.0F);
		bb_main.texOffs(0, 0).addBox(0.0F, -0.0F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}


	@Override
	public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

	}
}
