package com.gmail.creeperhostanimations.oretech.blocks.entity;

import com.gmail.creeperhostanimations.oretech.OreTech;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import nerdhub.cardinalenergy.api.IEnergyHandler;
import nerdhub.cardinalenergy.impl.EnergyStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Direction;

public class ElectricFurnaceEntity extends BlockEntity
        implements SidedInventory, PropertyDelegateHolder, IEnergyHandler {

    public int progress = 0;

    PropertyDelegate propertyDelegate = new PropertyDelegate(){
    
        @Override
        public int size() {
            return 4;
        }
    
        @Override
        public void set(int arg0, int arg1) {
            
        }
    
        @Override
        public int get(int arg0) {
            switch(arg0) {
                case 0:
                    return progress;
                case 1:
                    return 100;
                case 2:
                    return storage.getEnergyStored();
                case 3:
                    return storage.getCapacity();
                default:
                    return 0;
            }
        }
    };

    public EnergyStorage storage = new EnergyStorage(10000);

    DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    public ElectricFurnaceEntity() {
        super(OreTech.ELECTRIC_FURNACE_ENTITY);
        // TODO Auto-generated constructor stub
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putInt("progress", progress);
        Inventories.toTag(tag, inventory);
        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        Inventories.fromTag(tag, inventory);
        progress = tag.getInt("progress");
    }

    @Override
    public boolean canPlayerUseInv(PlayerEntity arg0) {
        return true;
    }

    @Override
    public int getInvSize() {
        return 2;
    }

    public ItemStack getOutputStack() {
        if (!inventory.get(0).isEmpty()) {
            SmeltingRecipe recipe = world.getRecipeManager().getFirstMatch(RecipeType.SMELTING, this, world).orElse(null);
            return recipe != null ? recipe.getOutput().copy() : ItemStack.EMPTY;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack getInvStack(int arg0) {
        return inventory.get(arg0);
    }

    @Override
    public boolean isInvEmpty() {
        return inventory.isEmpty();
    }

    @Override
    public ItemStack removeInvStack(int arg0) {
        return inventory.remove(arg0);
    }

    @Override
    public void setInvStack(int arg0, ItemStack arg1) {
        if(arg0==1) {
            inventory.set(arg0, arg1);
        }else {
            inventory.set(0, arg1);
            inventory.set(1, getOutputStack());
            inventory.set(0, ItemStack.EMPTY);
        }
    }

    @Override
    public ItemStack takeInvStack(int arg0, int arg1) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
    }

    @Override
    public boolean canExtractInvStack(int arg0, ItemStack arg1, Direction arg2) {
        return true;
    }

    @Override
    public boolean canInsertInvStack(int arg0, ItemStack arg1, Direction arg2) {
        return arg0==0;
    }

	@Override
	public int[] getInvAvailableSlots(Direction arg0) {
		return new int[]{0,1};
	}
    
}