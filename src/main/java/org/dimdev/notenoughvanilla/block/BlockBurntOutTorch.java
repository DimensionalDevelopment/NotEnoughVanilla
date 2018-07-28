package org.dimdev.notenoughvanilla.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IProperty;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockBurntOutTorch extends BlockTorch {
    public BlockBurntOutTorch(Builder builder) {
        super(builder);
    }

    @Override
    public boolean onRightClick(IBlockState state, World world, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return tryLightingTorch(Blocks.TORCH, state, world, pos, player, hand);
    }

    public static boolean tryLightingTorch(Block litTorch, IBlockState unlitTorchState, World world, BlockPos pos, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (stack.getItem() == Items.FLINT_AND_STEEL) {
            replaceTorch(litTorch, unlitTorchState, world, pos);
            stack.damageItem(1, player);
            world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
            return true;
        }
        return false;
    }

    public static void replaceTorch(Block newTorch, IBlockState oldTorchState, World world, BlockPos pos) {
        IBlockState newState = newTorch.getDefaultState();
        for (IProperty property : oldTorchState.getProperties()) {
            newState.withProperty(property, oldTorchState.getValue(property));
        }
        world.setBlockState(pos, newState, 11);
    }

    @Override
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random) {
        // NO-OP
    }
}
