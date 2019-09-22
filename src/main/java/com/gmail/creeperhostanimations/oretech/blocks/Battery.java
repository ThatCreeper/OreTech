package com.gmail.creeperhostanimations.oretech.blocks;

import java.util.Collections;
import java.util.Set;

import com.gmail.creeperhostanimations.oretech.blocks.controller.OreTechControllers;
import com.gmail.creeperhostanimations.oretech.blocks.entity.BatteryEntity;

import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.component.BlockComponentProvider;
import nerdhub.cardinal.components.api.component.Component;
import nerdhub.cardinalenergy.DefaultTypes;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Battery extends Block implements BlockEntityProvider, BlockComponentProvider {

    public Battery() {
        super(FabricBlockSettings.of(Material.STONE).strength(1, 2).build());
        // TODO Auto-generated constructor stub
    }

	@Override
	public BlockEntity createBlockEntity(BlockView arg0) {
		return new BatteryEntity();
	}
    
    @Override
	public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
		if (world.isClient) return true;
		
		BlockEntity be = world.getBlockEntity(pos);
		if (be!=null && be instanceof BatteryEntity) {
			ContainerProviderRegistry.INSTANCE.openContainer(OreTechControllers.BATTERY_CONTROLLER, player, (buf)->{
				buf.writeBlockPos(pos);
			});
		}
		
		return true;
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
            return (T) ((BatteryEntity) blockView.getBlockEntity(pos)).storage;
        }
        return null;
    }

    @Override
    public Set<ComponentType<?>> getComponentTypes(BlockView blockView, BlockPos pos, Direction side) {
        return Collections.singleton(DefaultTypes.CARDINAL_ENERGY);
    }

}