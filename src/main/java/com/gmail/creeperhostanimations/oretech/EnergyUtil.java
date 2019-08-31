package com.gmail.creeperhostanimations.oretech;

import nerdhub.cardinal.components.api.component.BlockComponentProvider;
import nerdhub.cardinalenergy.DefaultTypes;
import nerdhub.cardinalenergy.api.IEnergyHandler;
import nerdhub.cardinalenergy.impl.EnergyStorage;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class EnergyUtil {
    public static void sendEnergyToNeighbors(World world, EnergyStorage storage, BlockPos pos, int sendAmount){
        if(!world.isClient && storage.getEnergyStored() >= sendAmount) {
            for (Direction direction : Direction.values()) {
                BlockPos offsetPos = pos.offset(direction);
                if(world.getBlockState(offsetPos).getBlock() instanceof BlockComponentProvider){
                    BlockComponentProvider componentProvider = (BlockComponentProvider) world.getBlockState(offsetPos).getBlock();
                    if(world.getBlockEntity(offsetPos) instanceof IEnergyHandler && ((IEnergyHandler) world.getBlockEntity(offsetPos)).isEnergyReceiver(null, DefaultTypes.CARDINAL_ENERGY) && componentProvider.hasComponent(world, offsetPos, DefaultTypes.CARDINAL_ENERGY, null)) {
                        int energySent = storage.sendEnergy(world, offsetPos, sendAmount);
        
                        if(energySent > 0) {
                            world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
                            world.updateListeners(offsetPos, world.getBlockState(offsetPos), world.getBlockState(offsetPos), 3);
                        }
                    }
                }
    
            }
        }
    }
}