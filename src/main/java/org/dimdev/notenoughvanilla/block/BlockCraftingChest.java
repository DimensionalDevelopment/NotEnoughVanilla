package org.dimdev.notenoughvanilla.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.dimdev.notenoughvanilla.tileentity.TileEntityCraftingChest;

public class BlockCraftingChest extends BlockContainer {
    public BlockCraftingChest(Builder builder) {
        super(builder);
    }

    @Override
    public boolean onRightClick(IBlockState state, World world, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        }
        TileEntityCraftingChest craftingChest = (TileEntityCraftingChest) world.getTileEntity(pos);
        player.displayGUIChest(craftingChest);

        player.func_195066_a(StatList.CRAFTING_TABLE_INTERACTION);
        player.func_195066_a(StatList.CHEST_OPENED);
        return true;
    }

    @Override
    public TileEntity getTileEntity(IBlockReader p_196283_1_) {
        return new TileEntityCraftingChest();
    }
}
