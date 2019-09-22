package com.gmail.creeperhostanimations.oretech.blocks.entity;

import com.gmail.creeperhostanimations.oretech.OreTech;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinalenergy.api.IEnergyHandler;
import nerdhub.cardinalenergy.impl.EnergyStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
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
        implements SidedInventory, PropertyDelegateHolder, IEnergyHandler, Tickable {

    public float progress = 0;

    public float timeAmount = 0;

    PropertyDelegate propertyDelegate = new PropertyDelegate(){
    
        @Override
        public int size() {
            return 4;
        }
    
        @Override
        public void set(int arg0, int arg1) {
            switch(arg0) {
                case 0:
                    progress = arg1;
                    break;
                case 1: 
                    break;
                case 2:
                    storage.setEnergyStored(arg1);
                    break;
                case 3:
                    storage.setCapacity(arg1);
                    break;
            }
        }
    
        @Override
        public int get(int arg0) {
            switch(arg0) {
                case 0:
                    return Math.round(progress);
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
        super(OreTechBlockEntities.ELECTRIC_FURNACE_ENTITY);
        // TODO Auto-generated constructor stub
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putFloat("progress", progress);
        tag.putFloat("timeAmount", timeAmount);
        Inventories.toTag(tag, inventory);
        storage.toTag(tag);
        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        progress = tag.getFloat("progress");
        timeAmount = tag.getFloat("timeAmount");
        Inventories.fromTag(tag, inventory);
        storage.fromTag(tag);
    }

    @Override
    public boolean canPlayerUseInv(PlayerEntity arg0) {
        return true;
    }

    @Override
    public int getInvSize() {
        return 2;
    }

    public ItemStack craft() {
        ItemStack slot0 = inventory.get(0);
        ItemStack slot1 = inventory.get(1);
        if (!slot0.isEmpty()) {
            SmeltingRecipe recipe = world.getRecipeManager().getFirstMatch(RecipeType.SMELTING, this, world).orElse(null);
            if((recipe.getOutput().isItemEqual(slot1)||slot1.isEmpty())&&recipe!=null){
                inventory.set(0, ItemStack.EMPTY);
                int count = slot1.equals(ItemStack.EMPTY) ? 1 : slot1.getCount()+slot0.getCount();
                ItemStack toReturn = recipe.getOutput().copy();
                toReturn.setCount(count);
                return toReturn;
            }
        }

        return slot1;
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
        if(!world.isClient()) regenTimes();
        return inventory.remove(arg0);
    }

    @Override
    public void setInvStack(int arg0, ItemStack arg1) {
        ItemStack old = inventory.get(arg0);
        inventory.set(arg0, arg1);
        if(!world.isClient()) regenTimes();
    }

    @Override
    public ItemStack takeInvStack(int arg0, int arg1) {
        ItemStack result = Inventories.splitStack(inventory, arg0, arg1);
        if (!result.isEmpty()) {
            markDirty();
        }
        if(!world.isClient()) regenTimes();
        return result;

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

    public void regenTimes(){
        SmeltingRecipe recipe = world.getRecipeManager().getFirstMatch(RecipeType.SMELTING, this, world).orElse(null);
        if(recipe==null){
            timeAmount = 0;
            return;
        }
        timeAmount=100.f/recipe.getCookTime();
        progress=0;
    }

    @Override
    public void tick() {
        if(timeAmount==0.f) return;
        if(progress==100.f){
            inventory.set(1,craft());
            if(!world.isClient()) { progress=0;
                regenTimes();
            }
        }
        progress += timeAmount;
    }

    @Override
    public boolean isEnergyReceiver(Direction direction, ComponentType componentType) {
        return true;
    }

    @Override
    public boolean isEnergyProvider(Direction direction, ComponentType componentType) {
        return false;
    }
    
}