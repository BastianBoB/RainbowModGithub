package com.dreckigesname.firstmod.core.init;

import java.awt.event.KeyEvent;

import com.dreckigesname.firstmod.FirstMod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class KeyBindInit {
	public static KeyBinding rainbowToolKey;
	
	public static void register(final FMLClientSetupEvent event) {
		rainbowToolKey = create("rainbow_tool_key", KeyEvent.VK_X);
		
		ClientRegistry.registerKeyBinding(rainbowToolKey);
	}
	
	private static KeyBinding create(String name, int key) {
		return new KeyBinding("key." + FirstMod.MOD_ID + "." + name, key, "key.category." + FirstMod.MOD_ID);
	}
}
