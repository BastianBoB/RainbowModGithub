package com.dreckigesname.firstmod.core.init;

import com.dreckigesname.firstmod.FirstMod;
import com.dreckigesname.firstmod.common.armor.ModArmorMaterial;
import com.dreckigesname.firstmod.common.armor.RainbowBoots;
import com.dreckigesname.firstmod.common.armor.RainbowChestplate;
import com.dreckigesname.firstmod.common.armor.RainbowHelmet;
import com.dreckigesname.firstmod.common.armor.RainbowLeggings;
import com.dreckigesname.firstmod.common.items.*;
import com.dreckigesname.firstmod.common.tools.ModItemTier;
import com.dreckigesname.firstmod.common.tools.ChargedRainbowSword;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FirstMod.MOD_ID);

	//Items
	public static final RegistryObject<Item> TEST_ITEM = ITEMS.register("test_item",
			() -> new TestItem(new Item.Properties().tab(FirstMod.RAINBOW_GROUP).stacksTo(1)));

	public static final RegistryObject<SpecialItem> SPECIAL_ITEM = ITEMS.register("special_item",
			() -> new SpecialItem(new Item.Properties().tab(FirstMod.RAINBOW_GROUP).stacksTo(1)));
	
	public static final RegistryObject<TeleportItem> TELEPORT_ITEM = ITEMS.register("teleport_item",
			() -> new TeleportItem(new Item.Properties().tab(FirstMod.RAINBOW_GROUP).stacksTo(1)));
	
	public static final RegistryObject<Item> RAINBOW_CRYSTAL = ITEMS.register("rainbow_crystal",
			() -> new Item(new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<Item> TOUGH_RAINBOW_CRYSTAL = ITEMS.register("tough_rainbow_crystal",
			() -> new Item(new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<Item> CHARGED_RAINBOW_CRYSTAL = ITEMS.register("charged_rainbow_crystal",
			() -> new Item(new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<Item> RAINBOW_WRENCH = ITEMS.register("rainbow_wrench",
			() -> new Item(new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<Item> RAINBOW_ARROW = ITEMS.register("rainbow_arrow",
			() -> new Item(new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<Item> FIRE_ARROW = ITEMS.register("fire_arrow",
			() -> new Item(new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<Item> TORCH_ARROW = ITEMS.register("torch_arrow",
			() -> new Item(new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<Item> TNT_ARROW = ITEMS.register("tnt_arrow",
			() -> new Item(new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<Item> ICE_ARROW = ITEMS.register("ice_arrow",
			() -> new Item(new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<Item> NATURE_ARROW = ITEMS.register("nature_arrow",
			() -> new Item(new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<Item> LIGHTNING_ARROW = ITEMS.register("lightning_arrow",
			() -> new Item(new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<RainbowBow> RAINBOW_BOW = ITEMS.register("rainbow_bow",
			() -> new RainbowBow(new Item.Properties().tab(FirstMod.RAINBOW_GROUP).durability(600)));
	
	public static final RegistryObject<BridgeEgg> BRIDGE_EGG = ITEMS.register("bridge_egg",
			() -> new BridgeEgg(new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<RainbowPistol> RAINBOW_PISTOL = ITEMS.register("rainbow_pistol",
			() -> new RainbowPistol(new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));

	public static final RegistryObject<GrenadeLauncher> GRENADE_LAUNCHER = ITEMS.register("grenade_launcher",
			() -> new GrenadeLauncher(new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	
	
	
	//Food
	public static final RegistryObject<RainbowApple> RAINBOW_APPLE = ITEMS.register("rainbow_apple",
			() -> new RainbowApple());
	
	//Armor
	public static final RegistryObject<ArmorItem> RAINBOW_HELMET = ITEMS.register("rainbow_helmet",
			() -> new RainbowHelmet(ModArmorMaterial.RAINBOW, EquipmentSlotType.HEAD, new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<ArmorItem> RAINBOW_CHESTPLATE = ITEMS.register("rainbow_chestplate",
			() -> new RainbowChestplate(ModArmorMaterial.RAINBOW, EquipmentSlotType.CHEST, new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<ArmorItem> RAINBOW_LEGGINGS = ITEMS.register("rainbow_leggings",
			() -> new RainbowLeggings(ModArmorMaterial.RAINBOW, EquipmentSlotType.LEGS, new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<ArmorItem> RAINBOW_BOOTS = ITEMS.register("rainbow_boots",
			() -> new RainbowBoots(ModArmorMaterial.RAINBOW, EquipmentSlotType.FEET, new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	
	//Tools
	public static final RegistryObject<SwordItem> RAINBOW_SWORD = ITEMS.register("rainbow_sword",
			() -> new SwordItem(ModItemTier.RAINBOW, 2, -2f, new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<PickaxeItem> RAINBOW_PICKAXE = ITEMS.register("rainbow_pickaxe",
			() -> new PickaxeItem(ModItemTier.RAINBOW, -1, -2.6f, new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<AxeItem> RAINBOW_AXE = ITEMS.register("rainbow_axe",
			() -> new AxeItem(ModItemTier.RAINBOW, 4, -1.5f, new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<ShovelItem> RAINBOW_SHOVEL = ITEMS.register("rainbow_shovel",
			() -> new ShovelItem(ModItemTier.RAINBOW, -1, -2.8f, new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<HoeItem> RAINBOW_HOE = ITEMS.register("rainbow_hoe",
			() -> new HoeItem(ModItemTier.RAINBOW, -5, -3f, new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<SwordItem> CHARGED_RAINBOW_SWORD = ITEMS.register("charged_rainbow_sword",
			() -> new ChargedRainbowSword(ModItemTier.RAINBOW, 4, -2f, new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<PickaxeItem> CHARGED_RAINBOW_PICKAXE = ITEMS.register("charged_rainbow_pickaxe",
			() -> new PickaxeItem(ModItemTier.RAINBOW, 1, -2.6f, new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<AxeItem> CHARGED_RAINBOW_AXE = ITEMS.register("charged_rainbow_axe",
			() -> new AxeItem(ModItemTier.RAINBOW, 6, -1.5f, new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<ShovelItem> CHARGED_RAINBOW_SHOVEL = ITEMS.register("charged_rainbow_shovel",
			() -> new ShovelItem(ModItemTier.RAINBOW, 1, -2.8f, new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<HoeItem> CHARGED_RAINBOW_HOE = ITEMS.register("charged_rainbow_hoe",
			() -> new HoeItem(ModItemTier.RAINBOW, -3, -3f, new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));

	
	// BLOCK ITEMS
	public static final RegistryObject<BlockItem> RAINBOW_BLOCK = ITEMS.register("rainbow_block",
			() -> new BlockItem(BlockInit.RAINBOW_BLOCK.get(), new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));

	public static final RegistryObject<BlockItem> BREAD_BLOCK = ITEMS.register("bread_block",
			() -> new BlockItem(BlockInit.BREAD_BLOCK.get(), new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));

	public static final RegistryObject<BlockItem> FACE_BLOCK = ITEMS.register("face_block",
			() -> new BlockItem(BlockInit.FACE_BLOCK.get(), new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<BlockItem> RAINBOW_ORE = ITEMS.register("rainbow_ore",
			() -> new BlockItem(BlockInit.RAINBOW_ORE.get(), new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<BlockItem> RAINBOW_BLOCK_HOLOGRAM = ITEMS.register("rainbow_block_hologram",
			() -> new BlockItem(BlockInit.RAINBOW_BLOCK_HOLOGRAM.get(), new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<BlockItem> RAINBOW_ALTAR = ITEMS.register("rainbow_altar",
			() -> new BlockItem(BlockInit.RAINBOW_ALTAR.get(), new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<BlockItem> RAINBOW_QUARRY = ITEMS.register("rainbow_quarry",
			() -> new BlockItem(BlockInit.RAINBOW_QUARRY.get(), new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<BlockItem> SPEED_MODIFIER = ITEMS.register("speed_modifier",
			() -> new BlockItem(BlockInit.SPEED_MODIFIER.get(), new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<BlockItem> LUCK_MODIFIER = ITEMS.register("luck_modifier",
			() -> new BlockItem(BlockInit.LUCK_MODIFIER.get(), new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<BlockItem> SILKTOUCH_MODIFIER = ITEMS.register("silktouch_modifier",
			() -> new BlockItem(BlockInit.SILKTOUCH_MODIFIER.get(), new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<BlockItem> ORE_MODIFIER = ITEMS.register("ore_modifier",
			() -> new BlockItem(BlockInit.ORE_MODIFIER.get(), new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
	public static final RegistryObject<BlockItem> RANGE_MODIFIER = ITEMS.register("range_modifier",
			() -> new BlockItem(BlockInit.RANGE_MODIFIER.get(), new Item.Properties().tab(FirstMod.RAINBOW_GROUP)));
	
}