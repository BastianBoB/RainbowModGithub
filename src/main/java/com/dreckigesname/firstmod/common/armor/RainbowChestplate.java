package com.dreckigesname.firstmod.common.armor;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class RainbowChestplate extends ArmorItem {

	public RainbowChestplate(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
		super(materialIn, slot, builderIn);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		super.onArmorTick(stack, world, player);

		if (player.hasEffect(Effects.REGENERATION)) {
			int potionDuration = player.getEffect(Effects.REGENERATION).getDuration();
			if (potionDuration < 200) {
				player.addEffect(new EffectInstance(Effects.REGENERATION, 300, 1));
			}
		} else {
			player.addEffect(new EffectInstance(Effects.REGENERATION, 300, 1));
		}

		if (!player.isOnGround()) {
			if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_SPACE)) {
				double sideSpeed = 1D;
				double ySpeed = 0.2D;
				double maxYSpeed = 1.2D;

				double mX = player.getLookAngle().x * sideSpeed;
				double mZ = player.getLookAngle().z * sideSpeed;
				double mY = Math.min(player.getDeltaMovement().y + ySpeed, maxYSpeed);
				player.setDeltaMovement(mX, mY, mZ);
			}
		}
	}
}
