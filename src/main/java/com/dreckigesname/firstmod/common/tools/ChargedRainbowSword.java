package com.dreckigesname.firstmod.common.tools;


import com.dreckigesname.firstmod.core.init.KeyBindInit;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ChargedRainbowSword extends SwordItem {

	public ChargedRainbowSword(IItemTier itemTier, int attackDamage, float attackSpeed, Properties properties) {
		super(itemTier, attackDamage, attackSpeed, properties);
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if (KeyBindInit.rainbowToolKey.isDown()) {
			System.out.println("SWORD RIGHTCLICK");
			double px = playerIn.getX();
			double py = playerIn.getY() + 1;
			double pz = playerIn.getZ();

			for (int i = 0; i < 8; i++) {
				double a = Math.PI * 2 / 8 * i;
				double x = px + Math.cos(a) * 10;
				double z = pz + Math.sin(a) * 10;
				LightningBoltEntity entity = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, worldIn);
				entity.setPos(x, py, z);
				worldIn.addFreshEntity(entity);
			}

			playerIn.getCooldowns().addCooldown(this, 80);
		}
		return ActionResult.success(playerIn.getItemInHand(handIn));
	}
}
