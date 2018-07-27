package org.dimdev.notenoughvanilla.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import org.dimdev.notenoughvanilla.NotEnoughVanilla;

public class BlockLantern extends Block {
    public BlockLantern(Builder builder) {
        super(builder);
    }

    @Override
    public ItemStack getItem(IBlockReader world, BlockPos pos, IBlockState state) {
        return new ItemStack(NotEnoughVanilla.LANTERN_ITEM);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(IBlockState p_isFullCube_1_) {
        return true;
    }
}
