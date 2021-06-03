package com.dreckigesname.firstmod.common.items;

import java.util.function.Predicate;

import com.dreckigesname.firstmod.common.entities.projectiles.FireArrowEntity;
import com.dreckigesname.firstmod.common.entities.projectiles.IceArrowEntity;
import com.dreckigesname.firstmod.common.entities.projectiles.LightningArrowEntity;
import com.dreckigesname.firstmod.common.entities.projectiles.NatureArrowEntity;
import com.dreckigesname.firstmod.common.entities.projectiles.RainbowArrowEntity;
import com.dreckigesname.firstmod.common.entities.projectiles.TntArrowEntity;
import com.dreckigesname.firstmod.common.entities.projectiles.TorchArrowEntity;
import com.dreckigesname.firstmod.core.init.ItemInit;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class RainbowBow extends BowItem {
	
	float damageMultiplier = 3;
	int knockbackAdd = 1;
	
	Item arrowItems[] = {ItemInit.FIRE_ARROW.get(), ItemInit.ICE_ARROW.get(), ItemInit.LIGHTNING_ARROW.get(), ItemInit.NATURE_ARROW.get(), ItemInit.RAINBOW_ARROW.get(), ItemInit.TNT_ARROW.get(), ItemInit.TORCH_ARROW.get()};
	
	public RainbowBow(Properties builder) {
		super(builder);
	}

	@Override
	public void releaseUsing(ItemStack bowStack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		System.out.println("BOW FIRED");

		if (entityLiving instanceof PlayerEntity) {
			
			PlayerEntity playerentity = (PlayerEntity) entityLiving;
			boolean hasInfinity = playerentity.isCreative() || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, bowStack) > 0;
			ItemStack ammoStack = playerentity.getProjectile(bowStack);
			System.out.println(ammoStack);

			int timeDrawn = this.getUseDuration(bowStack) - timeLeft;
			timeDrawn = ForgeEventFactory.onArrowLoose(bowStack, worldIn, playerentity, timeDrawn, !ammoStack.isEmpty() || hasInfinity);
			if (timeDrawn < 0) return;

			if (!ammoStack.isEmpty() || hasInfinity) {
				boolean isTippedArrow = ammoStack.getItem() == Items.SPECTRAL_ARROW || ammoStack.getItem() == Items.TIPPED_ARROW;

				if (ammoStack.isEmpty()) {
					ammoStack = new ItemStack(ItemInit.RAINBOW_ARROW.get());
				}

				float velocity = getArrowVelocity(timeDrawn);
				if (!((double) velocity < 0.1D)) {
					if (!worldIn.isClientSide) {
						for (int i = 0; i < 4; i += 1) {
							double r = (i + 0.1) * 3;
							for (double a = 0; a < Math.PI * 2; a += Math.PI / r * 1.2) {
								float pitch = (float) (Math.cos(a) * r);
								float yaw = (float) (Math.sin(a) * r);
								AbstractArrowEntity arrowEntity = createArrow(worldIn, ammoStack, playerentity);
								arrowEntity.shootFromRotation(playerentity, playerentity.xRot + pitch, playerentity.yRot + yaw, 0.0F, velocity * 3.0F, 1.0F);

								if (velocity >= 1.0F)
									arrowEntity.setCritArrow(true);

								double damage = getArrowDamage(bowStack, arrowEntity) * damageMultiplier;
								arrowEntity.setBaseDamage(damage);

								int knockback = getArrowKnockback(bowStack, arrowEntity) + knockbackAdd;
								arrowEntity.setKnockback(knockback);

								// apply flame enchant
								if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, bowStack) > 0) {
									arrowEntity.setSecondsOnFire(100);
								}

								// set if arrow can be picked up from ground
								arrowEntity.pickup = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;

								// actually make the arrow entity exist in the world
								worldIn.addFreshEntity(arrowEntity);
							}
						}
					}

					// reduce bow durability
					bowStack.hurtAndBreak(1, playerentity, (p_220009_1_) -> {
						p_220009_1_.broadcastBreakEvent(playerentity.getUsedItemHand());
					});
					
					// sound
					worldIn.playSound((PlayerEntity) null, playerentity.getX(), playerentity.getY(),
							playerentity.getZ(), SoundEvents.ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F,
							1.0F / (random.nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);

					// use an arrow
					boolean shouldConsumeArrow = !hasInfinity || isTippedArrow;
					if (shouldConsumeArrow) {
						ammoStack.shrink(1);
						if (ammoStack.isEmpty()) {
							playerentity.inventory.removeItem(ammoStack);
						}
					}

					playerentity.awardStat(Stats.ITEM_USED.get(this));
				}
			}
		}
	}

	protected AbstractArrowEntity createArrow(World worldIn, ItemStack ammoStack, PlayerEntity playerentity) {
		for(int i = 0; i < arrowItems.length; i++) {
			if(ammoStack.getItem() == arrowItems[i]) {
				switch(i) {
					case 0:
						return new FireArrowEntity(worldIn, playerentity);
					case 1:
						return new IceArrowEntity(worldIn, playerentity);
					case 2:
						return new LightningArrowEntity(worldIn, playerentity);
					case 3:
						return new NatureArrowEntity(worldIn, playerentity);
					case 4:
						return new RainbowArrowEntity(worldIn, playerentity);
					case 5:
						return new TntArrowEntity(worldIn, playerentity);
					case 6:
						return new TorchArrowEntity(worldIn, playerentity);
				}
			}
		}
		return new RainbowArrowEntity(worldIn, playerentity);
	}

	protected double getArrowDamage(ItemStack bowStack, AbstractArrowEntity arrowEntity) {
		int powerLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bowStack);

		if (powerLevel > 0)
			return arrowEntity.getBaseDamage() + (double) powerLevel * 0.5D + 0.5D;
		else
			return arrowEntity.getBaseDamage();
	}

	protected int getArrowKnockback(ItemStack bowStack, AbstractArrowEntity arrowEntity) {
		return EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, bowStack);
	}

	public Predicate<ItemStack> getAllSupportedProjectiles() {
		return (ammoStack) -> {
			for(int i = 0; i < arrowItems.length; i++) {
				if(ammoStack.getItem() == arrowItems[i]) {
					return ammoStack.getItem() == arrowItems[i];
				}
			}
			return ammoStack.getItem() == Items.ARROW;
		};
	}

	public static float getArrowVelocity(int charge) {
		float f = (float) charge / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		if (f > 1.0F) {
			f = 1.0F;
		}
		return f * 1.3f;
	}
}
