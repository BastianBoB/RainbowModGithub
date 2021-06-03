package com.dreckigesname.firstmod.common.items;

import java.util.List;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TeleportItem extends Item {

	int typeState = 0;

	public TeleportItem(Properties properties) {
		super(properties);
	}
	
	private BlockPos blockPosFeet(float dist, PlayerEntity playerIn) {	
		return new BlockPos(playerIn.getX() + playerIn.getLookAngle().x * dist, playerIn.getY() + playerIn.getLookAngle().y * dist, playerIn.getZ() + playerIn.getLookAngle().z * dist);
	}
	
	private BlockPos blockPosHead(float dist, PlayerEntity playerIn) {	
		return new BlockPos(playerIn.getX() + playerIn.getLookAngle().x * dist, playerIn.getY() + playerIn.getLookAngle().y * dist+1, playerIn.getZ() + playerIn.getLookAngle().z * dist);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
			tooltip.add(new StringTextComponent("Sneak + Rightclick to change Mode"));
			switch(typeState) {
				case 0:
					tooltip.add(new StringTextComponent("1. Smooth Mode"));
					break;
				
				case 1:
					tooltip.add(new StringTextComponent("2. Normal Mode"));
					break;
					
				case 2:
					tooltip.add(new StringTextComponent("3. Advanced Normal Mode"));
					break;
				
				case 3:
					tooltip.add(new StringTextComponent("4. Wall Mode"));
					break;
			}
		} else {
			tooltip.add(new TranslationTextComponent("tooltip.firstmod.teleport_item.hold_shift"));
		}
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) {
			if (!worldIn.isClientSide) {
				typeState += 1;
				if (typeState > 3) {
					typeState = 0;
				}
			}
		} else if (!playerIn.getCooldowns().isOnCooldown(this)) {
			switch (typeState) {
			case 0:
				float mult = 2.5f;
				playerIn.setDeltaMovement(playerIn.getLookAngle().x * mult, playerIn.getLookAngle().y * mult, playerIn.getLookAngle().z * mult);
				playerIn.getCooldowns().addCooldown(this, 20);
				break;
				
			case 1:
				int maxDist = 18;
				int i = 1;
				while(!worldIn.getBlockState(blockPosFeet(i, playerIn)).canOcclude() && !worldIn.getBlockState(blockPosHead(i, playerIn)).canOcclude() && i < maxDist) {
					i++;
				}
				i-=1;
		        playerIn.setPos(playerIn.getX() + playerIn.getLookAngle().x * i, playerIn.getY() + playerIn.getLookAngle().y * i, playerIn.getZ() + playerIn.getLookAngle().z * i);
				playerIn.getCooldowns().addCooldown(this, 10);
				break;
				
			case 2:
				int maxDist2 = 20;
				int i2 = maxDist2;
				while(worldIn.getBlockState(blockPosFeet(i2, playerIn)).canOcclude() || worldIn.getBlockState(blockPosHead(i2, playerIn)).canOcclude() && i2 > 0) {
					i2--;
				}
		        playerIn.setPos(playerIn.getX() + playerIn.getLookAngle().x * i2, playerIn.getY() + playerIn.getLookAngle().y * i2, playerIn.getZ() + playerIn.getLookAngle().z * i2);
				playerIn.getCooldowns().addCooldown(this, 20);
				break;

			case 3:
				if(worldIn.getBlockState(blockPosFeet(1, playerIn)).canOcclude() && worldIn.getBlockState(blockPosHead(1, playerIn)).canOcclude()) {
					int maxDist3 = 20;
					int i3 = 1;
					while(worldIn.getBlockState(blockPosFeet(i3, playerIn)).canOcclude() && worldIn.getBlockState(blockPosHead(i3, playerIn)).canOcclude() && i3 < maxDist3) {
						i3++;
					}
					if(i3 < maxDist3) {
						playerIn.setPos(playerIn.getX() + playerIn.getLookAngle().x * i3, playerIn.getY() + playerIn.getLookAngle().y * i3, playerIn.getZ() + playerIn.getLookAngle().z * i3);
					}
				}
				playerIn.getCooldowns().addCooldown(this, 10);
				break;
			}
			return ActionResult.success(playerIn.getItemInHand(handIn));
		}
		return ActionResult.fail(playerIn.getItemInHand(handIn));
	}
}
