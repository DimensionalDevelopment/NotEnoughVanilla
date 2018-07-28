package org.dimdev.notenoughvanilla.mixin.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.dimdev.notenoughvanilla.NotEnoughVanilla;
import org.dimdev.notenoughvanilla.block.BlockBurntOutTorch;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(BlockTorch.class)
public abstract class MixinBlockTorch extends Block {
    public MixinBlockTorch(Builder builder) {
        super(builder);
    }

    @Override
    public boolean getTickRandomly(IBlockState state) {
        return true;
    }

    @Override
    public void tick(IBlockState state, World world, BlockPos pos, Random random) {
        Block unlitTorch;

        if (this == Blocks.TORCH) {
            unlitTorch = NotEnoughVanilla.BURNT_OUT_TORCH;
        } else if (this == Blocks.WALL_TORCH) {
            unlitTorch = NotEnoughVanilla.BURNT_OUT_WALL_TORCH;
        } else {
            unlitTorch = null;
        }

        if (unlitTorch != null && random.nextInt(4) != 0) {
            BlockBurntOutTorch.replaceTorch(unlitTorch, state, world, pos);
            world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (random.nextFloat() - random.nextFloat()) * 0.8F);
        }
    }
}
