package com.dreckigesname.firstmod.common.items;

import com.dreckigesname.firstmod.FirstMod;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class RainbowApple extends Item{

	@SuppressWarnings("deprecation")
	public RainbowApple() {
		super(new Item.Properties()
		.tab(FirstMod.RAINBOW_GROUP)
		.food(new Food.Builder()
				.nutrition(10)
				.saturationMod(20)
				.fast()
				.effect(new EffectInstance(Effects.ABSORPTION, 3000, 4), 1f)
				.effect(new EffectInstance(Effects.REGENERATION, 500, 2), 1f)
				.effect(new EffectInstance(Effects.DAMAGE_RESISTANCE, 6000, 1), 1f)
				.effect(new EffectInstance(Effects.FIRE_RESISTANCE, 6000, 0), 1f)
				.effect(new EffectInstance(Effects.JUMP, 6000, 1), 1f)
				.effect(new EffectInstance(Effects.MOVEMENT_SPEED, 6000, 1), 1f)
				.alwaysEat()
				.build())
		);
	}

}
