package org.dimdev.notenoughvanilla.block;

import net.minecraft.block.BlockTorchWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockBurntOutTorchWall extends BlockTorchWall {
    public BlockBurntOutTorchWall(Builder builder) {
        super(builder);
    }

    @Override
    public boolean onRightClick(IBlockState state, World world, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return BlockBurntOutTorch.tryLightingTorch(Blocks.WALL_TORCH, state, world, pos, player, hand);
    }

    @Override
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random) {
        // NO-OP
    }
}
