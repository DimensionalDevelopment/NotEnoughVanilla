package org.dimdev.notenoughvanilla.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.dimdev.notenoughvanilla.NotEnoughVanilla;

public class BlockLantern extends Block {
    public BlockLantern() {
        super(Builder.create(Material.GLASS).lightValue(15));
    }

    @Override
    public IItemProvider getItemProvider(IBlockState p_getItemProvider_1_, World p_getItemProvider_2_, BlockPos p_getItemProvider_3_, int p_getItemProvider_4_) {
        return NotEnoughVanilla.LANTERN_ITEM;
    }
}
