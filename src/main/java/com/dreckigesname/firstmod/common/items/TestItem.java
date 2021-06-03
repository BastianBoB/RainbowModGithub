package com.dreckigesname.firstmod.common.items;

import java.util.List;

import org.lwjgl.glfw.GLFW;

import com.dreckigesname.firstmod.common.entities.GroundexplodeTNT;
import com.dreckigesname.firstmod.common.entities.MultiTNT;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TestItem extends Item {

	public TestItem(Properties properties) {
		super(properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
			tooltip.add(new StringTextComponent("Right click to make party"));
		} else {
			tooltip.add(new TranslationTextComponent("tooltip.firstmod.test_item.hold_shift"));
		}
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		double speed = 2;
		GroundexplodeTNT entity1 = new GroundexplodeTNT(worldIn, playerIn.getX(), playerIn.getY(), playerIn.getZ(), null);
		entity1.setDeltaMovement(playerIn.getLookAngle().x * speed, playerIn.getLookAngle().y * speed, playerIn.getLookAngle().z * speed);
		worldIn.addFreshEntity(entity1);

		
		playerIn.getCooldowns().addCooldown(this, 1);
		return ActionResult.success(playerIn.getItemInHand(handIn));
	}	
}
