package com.gmail.creeperhostanimations.oretech.blocks.entity;

import com.gmail.creeperhostanimations.oretech.EnergyUtil;
import com.gmail.creeperhostanimations.oretech.OreTech;

import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinalenergy.api.IEnergyHandler;
import nerdhub.cardinalenergy.impl.EnergyStorage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class CrankEntity extends BlockEntity implements IEnergyHandler, Tickable {

    public EnergyStorage storage = new EnergyStorage(10000);

    public CrankEntity() {
        super(OreTechBlockEntities.CRANK_ENTITY);
        // TODO Auto-generated constructor stub
	}

    @Override
    public void tick() {
        EnergyUtil.sendEnergyToNeighbors(world, storage, pos, 50);
    }
    
    @Override
    public boolean isEnergyReceiver(Direction direction, ComponentType componentType) {
        return false;
    }

    @Override
    public boolean isEnergyProvider(Direction direction, ComponentType componentType) {
        return true;
    }
}