package com.dreckigesname.firstmod.common.containers;

import java.util.Objects;

import com.dreckigesname.firstmod.common.tiles.RainbowAltarTileEntity;
import com.dreckigesname.firstmod.core.init.BlockInit;
import com.dreckigesname.firstmod.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

public class RainbowAltarContainer extends Container {

	public final RainbowAltarTileEntity te;
	private final IWorldPosCallable canInteractWithCollable;
	
	public RainbowAltarContainer(final int windowId, final PlayerInventory playerInv, final RainbowAltarTileEntity te) {
		super(ContainerTypeInit.RAINBOW_ALTAR_CONTAINER_TYPE.get(), windowId);
		this.te = te;
		this.canInteractWithCollable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());
		
		//Tile Entity
		this.addSlot(new Slot((IInventory) te, 0, 80, 35));
		
		//Main Player Inventory
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 9; col++) {
				this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
			}
		}
		
		//Player Hotbar
		for(int col = 0; col < 9; col++) {
			this.addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
		}
	}
	
	public RainbowAltarContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
		this(windowId, playerInv, getTileEntity(playerInv, data));
	}
	
	private static RainbowAltarTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
		Objects.requireNonNull(playerInv, "Player Inventory cannot be null");
		Objects.requireNonNull(data, "Packet Buffer cannot be null");
		final TileEntity te = playerInv.player.level.getBlockEntity(data.readBlockPos());
		if(te instanceof RainbowAltarTileEntity) {
			return (RainbowAltarTileEntity) te;
		}
		
		throw new IllegalStateException("Tile Entity is not Correct");
	}

	@Override
	public boolean stillValid(PlayerEntity playerIn) {
		return stillValid(canInteractWithCollable, playerIn, BlockInit.RAINBOW_ALTAR.get());
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if(slot != null && slot.hasItem()) {
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();
			if(index < RainbowAltarTileEntity.slots && !this.moveItemStackTo(stack1, RainbowAltarTileEntity.slots, this.slots.size(), true)) {
				return ItemStack.EMPTY;
			}
			if(!this.moveItemStackTo(stack1, 0, RainbowAltarTileEntity.slots, false)) {
				return ItemStack.EMPTY;
			}
			if(stack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			}
			else {
				slot.setChanged();
			}
		}
		return stack;
	}
}
