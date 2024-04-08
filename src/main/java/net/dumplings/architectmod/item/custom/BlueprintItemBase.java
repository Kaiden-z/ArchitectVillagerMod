package net.dumplings.architectmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

abstract class BlueprintItemBase extends Item {

    protected VillagerProfession professionType; // Type of villager that this blueprint should spawn
    protected BlockPos structureSize; // Size of structure in terms of a 3d Vector (length, height, width)
    private BlockPos validSiteCorner = null; // BlockPos of structure corner when site considered valid at the time of checking
    private Direction validDirection = null; // Direction of player when site was considered valid at the time of checking
    public BlueprintItemBase(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            assert player != null;
            Direction playerDirection = player.getDirection();

            if (ConstructionSiteIsClear(pContext, positionClicked, playerDirection)) {

                if (validSiteCorner == null) {
                    validSiteCorner = positionClicked;
                    validDirection = playerDirection;
                    player.sendSystemMessage(Component.literal("Construction site is clear!").withStyle(ChatFormatting.BLUE)
                            .append(Component.literal(" Interact with the block again to confirm.").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.WHITE)));
                    return InteractionResult.SUCCESS;

                } else if (positionClicked.equals(validSiteCorner)) {
                    player.sendSystemMessage(Component.literal("Building structure...").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GREEN));

                    validSiteCorner = null;
                    validDirection = null;
                    return InteractionResult.SUCCESS;

                } else {
                    validSiteCorner = null;
                    validDirection = null;
                    player.sendSystemMessage(Component.literal("Clicked a different block. Construction cancelled.").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
                    return InteractionResult.SUCCESS;
                }

            } else {
                validSiteCorner = null;
                validDirection = null;
                player.sendSystemMessage(Component.literal("Your construction site is obstructed.").withStyle(ChatFormatting.RED));
                player.sendSystemMessage(Component.literal("You need to clear a " +
                        structureSize.getX() + "x" + structureSize.getY() + "x" + structureSize.getZ() + " area to build here")
                        .withStyle(ChatFormatting.ITALIC));
                return InteractionResult.FAIL;
            }
        }

        return InteractionResult.SUCCESS;
    }


    /**
     * Check if the cube volume of where a structure is going to be built is clear of any blocks
     *
     * @param pContext
     * @param buildLocation The bottom left corner of the where the structure is to be built
     * @param playerDirection Player's current facing cardinal direction
     * @return True if there are no blocks within the construction zone, False otherwise
     */
    private boolean ConstructionSiteIsClear(UseOnContext pContext, BlockPos buildLocation, Direction playerDirection) {
        buildLocation = buildLocation.offset(0, 1, 0);

        /*
         * Need to check blocks towards right and away relative to player facing
         * Facing east (positive X): X++, Z++
         * Facing south (positive Z): X--, Z++
         * Facing west (negative X): X--, Z--
         * Facing north (negative Z): X++, Z--
         */
        int xOrientation = (playerDirection == Direction.NORTH || playerDirection == Direction.EAST) ? 1 : -1;
        int zOrientation = (playerDirection == Direction.EAST || playerDirection == Direction.SOUTH) ? 1 : -1;

        int length = structureSize.getX();
        int width = structureSize.getZ();
        int height = structureSize.getY();
        int structureVolume = length * width * height;

        for (int i = 0; i < structureVolume; i++) {
            int x = (i % length * xOrientation) + buildLocation.getX();
            int y = ((i / length) % width) + buildLocation.getY();
            int z = (i / (length * width) * zOrientation) + buildLocation.getZ();
            BlockPos curPos = new BlockPos(x, y, z);
            if (!pContext.getLevel().getBlockState(curPos).is(Blocks.AIR)) {
                return false;
            }
        }

        return true;
    }
}
