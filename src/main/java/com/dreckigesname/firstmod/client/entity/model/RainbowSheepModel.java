package com.dreckigesname.firstmod.client.entity.model;


import com.dreckigesname.firstmod.common.entities.RainbowSheepEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class RainbowSheepModel<T extends RainbowSheepEntity> extends EntityModel<T> {
	
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer legFrontLeft;
	private final ModelRenderer legFrontRight;
	private final ModelRenderer legBackLeft;
	private final ModelRenderer legBackRight;

	public RainbowSheepModel() {
		texWidth = 128;
		texHeight = 128;

		head = new ModelRenderer(this);
		head.setPos(0.0F, 24.0F, 0.0F);
		head.texOffs(0, 28).addBox(-4.0F, -19.0F, -15.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.texOffs(14, 0).addBox(2.0F, -22.0F, -14.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		head.texOffs(14, 8).addBox(-3.0F, -22.0F, -14.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.texOffs(0, 0).addBox(-7.0F, -15.0F, -9.0F, 14.0F, 9.0F, 19.0F, 0.0F, false);
		body.texOffs(32, 32).addBox(-6.0F, -18.0F, -3.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
		body.texOffs(5, 14).addBox(-1.0F, -19.0F, -4.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		body.texOffs(32, 36).addBox(4.0F, -18.0F, -3.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
		body.texOffs(13, 5).addBox(4.0F, -17.0F, 2.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		body.texOffs(38, 28).addBox(4.0F, -17.0F, 6.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		body.texOffs(8, 0).addBox(-1.0F, -20.0F, 6.0F, 2.0F, 5.0F, 1.0F, 0.0F, false);
		body.texOffs(0, 8).addBox(-1.0F, -21.0F, 2.0F, 2.0F, 6.0F, 1.0F, 0.0F, false);
		body.texOffs(32, 28).addBox(-6.0F, -18.0F, 6.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
		body.texOffs(13, 13).addBox(-6.0F, -19.0F, 2.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		legFrontLeft = new ModelRenderer(this);
		legFrontLeft.setPos(0.0F, 24.0F, 0.0F);
		legFrontLeft.texOffs(0, 28).addBox(3.0F, -6.0F, -6.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		legFrontRight = new ModelRenderer(this);
		legFrontRight.setPos(0.0F, 24.0F, 0.0F);
		legFrontRight.texOffs(24, 28).addBox(-5.0F, -6.0F, -6.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		legBackLeft = new ModelRenderer(this);
		legBackLeft.setPos(4.0F, 18.0F, 5.0F);
		legBackLeft.texOffs(6, 6).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		legBackRight = new ModelRenderer(this);
		legBackRight.setPos(-4.0F, 18.0F, 5.0F);
		legBackRight.texOffs(0, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
	}


	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		legFrontLeft.render(matrixStack, buffer, packedLight, packedOverlay);
		legFrontRight.render(matrixStack, buffer, packedLight, packedOverlay);
		legBackLeft.render(matrixStack, buffer, packedLight, packedOverlay);
		legBackRight.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		  this.head.xRot = headPitch * ((float)Math.PI / 180F);
	      this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
	      this.body.xRot = 0;
	      this.legBackRight.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	      this.legBackLeft.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	      this.legFrontRight.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	      this.legFrontLeft.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}

}
