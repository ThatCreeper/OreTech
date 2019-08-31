package com.gmail.creeperhostanimations.oretech.blocks.entity;

import com.gmail.creeperhostanimations.oretech.EnergyUtil;
import com.gmail.creeperhostanimations.oretech.OreTech;

import io.github.cottonmc.cotton.gui.PropertyDelegateHolder;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinalenergy.api.IEnergyHandler;
import nerdhub.cardinalenergy.impl.EnergyStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class BatteryEntity extends BlockEntity implements IEnergyHandler, PropertyDelegateHolder, Tickable {

    public EnergyStorage storage = new EnergyStorage(10000);

    public PropertyDelegate propertyDelegate = new PropertyDelegate(){
    
        @Override
        public int size() {
            return 2;
        }
    
        @Override
        public void set(int arg0, int arg1) {
            switch(arg0) {
                case 0:
                    storage.setEnergyStored(arg1);
                    break;
                case 1:
                    storage.setCapacity(arg1);
                    break;
            }
        }
    
        @Override
        public int get(int arg0) {
            switch(arg0) {
                case 0:
                    return storage.getEnergyStored();
                case 1:
                    return storage.getCapacity();
            }
            return 0;
        }
    };

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
    public void tick() {
        EnergyUtil.sendEnergyToNeighbors(world, storage, pos, 500);
    }

    @Override
    public PropertyDelegate getPropertyDelegate() {
        return propertyDelegate;
	}

    @Override
    public boolean isEnergyProvider(Direction direction, ComponentType componentType) {
        return true;
    }

    @Override
    public boolean isEnergyReceiver(Direction direction, ComponentType componentType) {
        return true;
    }

}