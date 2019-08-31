package com.gmail.creeperhostanimations.oretech.blocks.controller;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;

public class PlayerInv implements SidedInventory {

    @Override
    public boolean canPlayerUseInv(PlayerEntity arg0) {
        return true;
    }

    @Override
    public int getInvSize() {
        return 1;
    }

    @Override
    public ItemStack getInvStack(int arg0) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isInvEmpty() {
        return true;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public ItemStack removeInvStack(int arg0) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setInvStack(int arg0, ItemStack arg1) {

    }

    @Override
    public ItemStack takeInvStack(int arg0, int arg1) {
        return ItemStack.EMPTY;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean canExtractInvStack(int arg0, ItemStack arg1, Direction arg2) {
        return false;
    }

    @Override
    public boolean canInsertInvStack(int arg0, ItemStack arg1, Direction arg2) {
        return false;
    }

    @Override
	public int[] getInvAvailableSlots(Direction arg0) {
		return new int[]{0};
	}
    
}