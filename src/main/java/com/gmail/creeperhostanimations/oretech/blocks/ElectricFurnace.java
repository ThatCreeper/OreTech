package com.gmail.creeperhostanimations.oretech.blocks;

import java.util.Set;

import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.component.BlockComponentProvider;
import nerdhub.cardinal.components.api.component.Component;
import nerdhub.cardinalenergy.DefaultTypes;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class ElectricFurnace extends Block implements BlockEntityProvider, BlockComponentProvider {

    public ElectricFurnace() {
        super(FabricBlockSettings.of(Material.STONE).strength(2, 1).build());
        // TODO Auto-generated constructor stub
    }

    @Override
    public <T extends Component> boolean hasComponent(BlockView blockView, BlockPos pos, ComponentType<T> type,
            Direction side) {
        return type==DefaultTypes.CARDINAL_ENERGY;
    }

    @Override
    public <T extends Component> T getComponent(BlockView blockView, BlockPos pos, ComponentType<T> type,
            Direction side) {
        if(type==DefaultTypes.CARDINAL_ENERGY) {
            return (T) ((ElectricFurnaceEntity) blockView.getBlockEntity(pos)).storage;
        }
        return null;
    }

    @Override
    public Set<ComponentType<?>> getComponentTypes(BlockView blockView, BlockPos pos, Direction side) {
        return Collections.singleton(DefaultTypes.CARDINAL_ENERGY);
    }

	@Override
	public BlockEntity createBlockEntity(BlockView arg0) {
		return null;
	}
    
}