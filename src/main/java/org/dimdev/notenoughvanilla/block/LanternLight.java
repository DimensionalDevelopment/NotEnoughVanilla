package org.dimdev.notenoughvanilla.block;

import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.EnumLightType;
import net.minecraft.world.World;
import org.dimdev.notenoughvanilla.NotEnoughVanilla;

import java.util.List;
import java.util.Random;

public class LanternLight extends BlockAir {
    public LanternLight() {
        super(Builder.create(Material.AIR));
    }

    @Override
    public void tick(IBlockState state, World world, BlockPos pos, Random random) {
        System.out.println("HERP");

        if(!world.isRemote) {
            List<EntityPlayer> players = world.getEntitiesWithinAABB(EntityPlayer.class,
                    new AxisAlignedBB(pos.add(-1,-1,-1), pos.add(1,1,1)));

            if(players.isEmpty()) world.isAirBlock(pos);
            else if(players.stream().noneMatch(p -> p.inventory.hasItemStack(new ItemStack(NotEnoughVanilla.LANTERN_ITEM)))) {
                world.setBlockToAir(pos);
            }
        }

        world.getPendingBlockTickList().add(pos, this, tickRate(world));
    }

    @Override
    public int getLightValue(IBlockState p_getLightValue_1_) {
        return 15;
    }


    @Override
    public void onBlockPlace(IBlockState state, World world, BlockPos pos, IBlockState p_onBlockPlace_4_) {

        if (!world.isRemote) {
            System.out.println("Derp");
            world.getPendingBlockTickList().add(pos, this, tickRate(world));
        }
    }
}
