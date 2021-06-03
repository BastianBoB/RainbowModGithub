package com.dreckigesname.firstmod.common.armor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class RainbowHelmet extends ArmorItem {

	public RainbowHelmet(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
		super(materialIn, slot, builderIn);
	}

	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity player) {
		super.onArmorTick(stack, world, player);
		
		if (player.hasEffect(Effects.NIGHT_VISION)) {
			int potionDuration = player.getEffect(Effects.NIGHT_VISION).getDuration();
			if (potionDuration < 200) {
				player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 300, 1));
			}
		} else {
			player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 300, 1));
		}
	}
}
