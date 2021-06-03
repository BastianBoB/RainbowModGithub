package com.dreckigesname.firstmod.core.event;


import com.dreckigesname.firstmod.FirstMod;
import com.dreckigesname.firstmod.core.init.ItemInit;
import com.dreckigesname.firstmod.core.init.KeyBindInit;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.BlockToolInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = FirstMod.MOD_ID, bus = Bus.FORGE)
public class EventHandler {

	@SubscribeEvent
	public static void onUseTool(final BlockToolInteractEvent event) {
		BlockPos blockPos = event.getPos();
		Block targetBlock = event.getWorld().getBlockState(blockPos).getBlock();

		if (KeyBindInit.rainbowToolKey.isDown()) {

			if (event.getHeldItemStack().getItem().equals(ItemInit.CHARGED_RAINBOW_HOE.get())) {
				if (targetBlock == Blocks.GRASS_BLOCK || targetBlock == Blocks.DIRT) {
					int radius = 3;
					for (int x = -radius + 1; x < radius; x++) {
						for (int z = -radius + 1; z < radius; z++) {
							Block block = event.getWorld().getBlockState(blockPos.offset(x, 0, z)).getBlock();
							if (block == Blocks.GRASS_BLOCK || block == Blocks.DIRT) {
								event.getWorld().setBlock(blockPos.offset(x, 0, z), Blocks.FARMLAND.defaultBlockState(), 3);
							}
						}
					}
				}

			}
		}
	}
	

	@SubscribeEvent
	public static void onBreakBlock(final BlockEvent.BreakEvent event) {
		BlockPos blockPos = event.getPos();

		if (KeyBindInit.rainbowToolKey.isDown()) {

			if (event.getPlayer().getMainHandItem().getItem().equals(ItemInit.CHARGED_RAINBOW_AXE.get())) {
				Block block = event.getWorld().getBlockState(blockPos).getBlock();
				if (BlockTags.LOGS.contains(block)) {
					for (int x = -1; x < 2; x++) {
						for (int z = -1; z < 2; z++) {
							for (int y = 0; y < 255 - blockPos.getY(); y++) {
								block = event.getWorld().getBlockState(blockPos.offset(x, y, z)).getBlock();
								if (BlockTags.LOGS.contains(block)) {
									event.getWorld().destroyBlock(blockPos.offset(x, y, z), true, event.getPlayer());
								} else {
									break;
								}
							}
						}
					}
				}
			}

			if (event.getPlayer().getMainHandItem().getItem().equals(ItemInit.CHARGED_RAINBOW_PICKAXE.get())) {
				Block block = event.getWorld().getBlockState(blockPos).getBlock();
				if (Tags.Blocks.ORES.contains(block)) {
					for (int x = -4; x < 5; x++) {
						for (int y = -4; y < 5; y++) {
							for (int z = -4; z < 5; z++) {
								Block targetBlock = event.getWorld().getBlockState(blockPos.offset(x, y, z)).getBlock();
								if (block == targetBlock) {
									event.getWorld().destroyBlock(blockPos.offset(x, y, z), true, event.getPlayer());
								}
							}
						}
					}
				}
			}

			if (event.getPlayer().getMainHandItem().getItem().equals(ItemInit.CHARGED_RAINBOW_SHOVEL.get())) {
				double lx = event.getPlayer().getLookAngle().x;
				double ly = event.getPlayer().getLookAngle().y;
				double lz = event.getPlayer().getLookAngle().z;
				int r = 1;
				if (Math.abs(lx) > Math.abs(ly) && Math.abs(lx) > Math.abs(lz)) {
					for (int z = -r; z < r + 1; z++) {
						for (int y = -r; y < r + 1; y++) {
							event.getWorld().destroyBlock(blockPos.offset(0, y, z), true, event.getPlayer());
						}
					}
				}
				if (Math.abs(lz) > Math.abs(lx) && Math.abs(lz) > Math.abs(ly)) {
					for (int x = -r; x < r + 1; x++) {
						for (int y = -r; y < r + 1; y++) {
							event.getWorld().destroyBlock(blockPos.offset(x, y, 0), true, event.getPlayer());
						}
					}
				}
				if (Math.abs(ly) > Math.abs(lx) && Math.abs(ly) > Math.abs(lz)) {
					for (int x = -r; x < r + 1; x++) {
						for (int z = -r; z < r + 1; z++) {
							event.getWorld().destroyBlock(blockPos.offset(x, 0, z), true, event.getPlayer());
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void fallEvent(LivingFallEvent event) {
		if (!(event.getEntity() instanceof PlayerEntity)) {
			return;
		}
		PlayerEntity player = (PlayerEntity) event.getEntity();
		if (player.getItemBySlot(EquipmentSlotType.CHEST).getItem() == ItemInit.RAINBOW_CHESTPLATE.get().getItem()) {
			event.setDistance(0F);
		}
	}
	
}
