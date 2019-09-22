package com.gmail.creeperhostanimations.oretech.blocks;

import java.util.Collections;
import java.util.Set;

import com.gmail.creeperhostanimations.oretech.blocks.controller.OreTechControllers;
import com.gmail.creeperhostanimations.oretech.blocks.entity.ElectricFurnaceEntity;

import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.component.BlockComponentProvider;
import nerdhub.cardinal.components.api.component.Component;
import nerdhub.cardinalenergy.DefaultTypes;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory.Builder;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ElectricFurnace extends Block implements BlockEntityProvider, BlockComponentProvider {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;

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
		return new ElectricFurnaceEntity();
    }

    @Override
    public BlockState rotate(BlockState blockState_1, BlockRotation blockRotation_1) {
        return blockState_1.with(FACING, blockRotation_1.rotate(blockState_1.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState blockState_1, BlockMirror blockMirror_1) {
        return blockState_1.with(FACING, blockMirror_1.apply(blockState_1.get(FACING)));
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> stateFactory) {
        stateFactory.add(FACING);
    }
    
    @Override
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return this.getDefaultState().with(FACING, itemPlacementContext_1.getPlayerFacing().getOpposite());
    }
    
    @Override
	public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
        if (world.isClient) return true;
        
		BlockEntity be = world.getBlockEntity(pos);
		if (be!=null && be instanceof ElectricFurnaceEntity) {
			ContainerProviderRegistry.INSTANCE.openContainer(OreTechControllers.ELECTRIC_FURNACE_CONTROLLER, player, (buf)->{
				buf.writeBlockPos(pos);
			});
		}
		
		return true;
    }
    
}