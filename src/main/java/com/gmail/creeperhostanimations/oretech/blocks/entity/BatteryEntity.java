package com.gmail.creeperhostanimations.oretech.blocks.entity;

import com.gmail.creeperhostanimations.oretech.OreTech;

import nerdhub.cardinalenergy.api.IEnergyHandler;
import nerdhub.cardinalenergy.impl.EnergyStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.math.Direction;

public class BatteryEntity extends BlockEntity implements IEnergyHandler, SidedInventory {

    public EnergyStorage storage = new EnergyStorage(10000);
    
    public DefaultedList<ItemStack> slots = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public BatteryEntity() {
        super(OreTech.BATTERY_ENTITY);
        // TODO Auto-generated constructor stub
	}
    
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        //Write energy to nbt
        this.storage.toTag(tag);
        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        // Read energy from nbt
        this.storage.fromTag(tag);
    }

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
        return slots.get(arg0);
    }

    @Override
    public boolean isInvEmpty() {
        return slots.isEmpty();
    }

    @Override
    public ItemStack removeInvStack(int arg0) {
        return slots.remove(arg0);
    }

    @Override
    public void setInvStack(int arg0, ItemStack arg1) {
        slots.set(arg0, arg1);
    }

    @Override
    public ItemStack takeInvStack(int arg0, int arg1) {
        ItemStack result = Inventories.splitStack(slots, arg0, arg1);
        if (!result.isEmpty()) {
            markDirty();
        }
        return result;
    }

    @Override
    public void clear() {
        slots.clear();
    }

    @Override
    public boolean canExtractInvStack(int arg0, ItemStack arg1, Direction arg2) {
        return true;
    }

    @Override
    public boolean canInsertInvStack(int arg0, ItemStack arg1, Direction arg2) {
        return true;
    }

    @Override
	public int[] getInvAvailableSlots(Direction arg0) {
		return new int[]{0};
	}

}