package com.gmail.creeperhostanimations.oretech.blocks.entity;

import com.gmail.creeperhostanimations.oretech.OreTech;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import nerdhub.cardinalenergy.api.IEnergyHandler;
import nerdhub.cardinalenergy.impl.EnergyStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Direction;

public class ElectricFurnaceEntity extends BlockEntity
        implements SidedInventory, PropertyDelegateHolder, IEnergyHandler, Tickable {

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

    EnergyStorage storage = new EnergyStorage(10000);

    DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    public ElectricFurnaceEntity() {
        super(OreTech.ELECTRIC_FURNACE_ENTITY);
        // TODO Auto-generated constructor stub
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putInt("progress", progress);
    }

    @Override
    public void fromTag(CompoundTag compoundTag_1) {
        
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
        return null;
    }

    @Override
    public boolean isInvEmpty() {
        return false;
    }

    @Override
    public ItemStack removeInvStack(int arg0) {
        return null;
    }

    @Override
    public void setInvStack(int arg0, ItemStack arg1) {

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
        return null;
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
		return null;
	}

    @Override
    public void tick() {
        if()
    }
    
}