package com.gmail.creeperhostanimations.oretech.blocks;

import com.gmail.creeperhostanimations.oretech.blocks.entity.CrankEntity;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateFactory.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Crank extends Block implements BlockEntityProvider {

    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty CRANK_ROTATED = BooleanProperty.of("crank_rotated");

    public Crank() {
        super(FabricBlockSettings.of(Material.WOOD).strength(1, 2).build());
        setDefaultState(stateFactory.getDefaultState().with(FACING, Direction.NORTH).with(CRANK_ROTATED, false));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView arg0) {
		return new CrankEntity();
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
        stateFactory.add(CRANK_ROTATED);
    }
    
    @Override
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return this.getDefaultState().with(FACING, itemPlacementContext_1.getPlayerFacing().getOpposite());
    }

    @Override
    public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1,
            Hand hand_1, BlockHitResult blockHitResult_1) {
        world_1.setBlockState(blockPos_1, world_1.getBlockState(blockPos_1).with(CRANK_ROTATED, !world_1.getBlockState(blockPos_1).get(CRANK_ROTATED)));
        if(world_1.isClient) {
            playerEntity_1.playSound(SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 10, 1);
            return true;
        }

        BlockEntity be = world_1.getBlockEntity(blockPos_1);
		if (be!=null && be instanceof CrankEntity) {
			((CrankEntity)be).storage.receiveEnergy(50);
		}
        return true;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
    
}