package com.dreckigesname.firstmod.common.items;

import java.util.List;

import org.lwjgl.glfw.GLFW;

import com.dreckigesname.firstmod.FirstMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SpecialItem extends Item {

	public SpecialItem(Properties properties) {
		super(properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
			tooltip.add(new StringTextComponent("Heftigster Tooltip Ever :O"));
		} else {
			tooltip.add(new TranslationTextComponent("tooltip.firstmod.special_item.hold_shift"));
		}
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.addEffect(new EffectInstance(Effects.REGENERATION, 40, 2));

		for (int i = 0; i < 50; i++) {
			double xDiff = random.nextDouble() * 40 - 20;
			double zDiff = random.nextDouble() * 40 - 20;
			ZombieEntity entity = new ZombieEntity(worldIn);
			entity.setPos(playerIn.getX() + xDiff, playerIn.getY() + 3, playerIn.getZ() + zDiff);
			worldIn.addFreshEntity(entity);
		}

		playerIn.getCooldowns().addCooldown(this, 100);
		return ActionResult.success(playerIn.getItemInHand(handIn));
	}

}
