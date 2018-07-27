package org.dimdev.notenoughvanilla.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReaderBase;
import net.minecraft.world.ServerTickList;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;
import org.dimdev.notenoughvanilla.NotEnoughVanilla;

import java.util.List;
import java.util.Random;

public class BlockLight extends BlockAir {
    public BlockLight(Builder builder) {
        super(builder);
    }

    @Override
    public void tick(IBlockState state, World world, BlockPos pos, Random random) {
        if (!world.isRemote) {
            List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class,
                    new AxisAlignedBB(pos.add(-2, -2, -2), pos.add(2, 2, 2)));

            if (players.isEmpty() || players.stream().noneMatch(
                    p -> p.getHeldItem(EnumHand.MAIN_HAND).getItem() == NotEnoughVanilla.LANTERN_ITEM ||
                         p.getHeldItem(EnumHand.OFF_HAND).getItem() == NotEnoughVanilla.LANTERN_ITEM)) {

                world.setBlockToAir(pos);
            } else {
                ((ServerTickList<Block>) world.getPendingBlockTickList()).func_205370_c(pos, this, tickRate(world), TickPriority.NORMAL);
            }
        }
    }

    @Override
    public int getLightValue(IBlockState p_getLightValue_1_) {
        return 12;
    }

    @Override
    public int tickRate(IWorldReaderBase world) {
        return 1;
    }

    @Override
    public void onBlockPlace(IBlockState state, World world, BlockPos pos, IBlockState p_onBlockPlace_4_) {
        if (!world.isRemote) {
            ((ServerTickList<Block>) world.getPendingBlockTickList()).func_205370_c(pos, this, tickRate(world), TickPriority.NORMAL);
        }
    }
}
