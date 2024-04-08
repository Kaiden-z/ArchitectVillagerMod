package net.dumplings.architectmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class buildingBlocks {
    private final BlockPos bPosition; // Position of one block relative to world
    private final BlockState bState; // Block state for this block

    public buildingBlocks (BlockPos bp, BlockState bs) {
        this.bPosition = bp;
        this.bState = bs;
    }

    public BlockPos blockPosition () {
        return this.bPosition;
    }
    public BlockState blockState () {
        return this.bState;
    }
}
