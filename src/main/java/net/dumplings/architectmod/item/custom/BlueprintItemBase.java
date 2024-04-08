package net.dumplings.architectmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

abstract class BlueprintItemBase extends Item {

    protected VillagerProfession professionType; // Type of villager that this blueprint should spawn
    protected BlockPos structureSize; // Size of structure in terms of a 3d Vector (length, height, width)
    protected String structureFileName; // Name of the nbt file that holes the structure
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
                    structureGen(validSiteCorner, pContext, structureFileName);
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

    /**
     * Grabbing and storing the block position and palette from the nbt file
     *
     * @param structureName the nbt file name of the given structure
     * @param level The bottom left corner of the where the structure is to be built
     * @return a list of buildingBlocks
     */
    public static ArrayList<buildingBlocks> getBuildingBlocks(String structureName, LevelAccessor level) {
        ResourceManager resourceManager;
        if (!level.isClientSide())
            resourceManager = Minecraft.getInstance().getResourceManager();
        else
            resourceManager = Objects.requireNonNull(level.getServer()).getResourceManager();

        ArrayList<buildingBlocks> blocks = new ArrayList<>();

        CompoundTag nbt = getBuildingNbt(structureName, resourceManager);
        assert nbt != null;
        ListTag blocksNbt = nbt.getList("blocks", 10);
        ArrayList<BlockState> palette = getBuildingPalette(nbt);

        for(int i = 0; i < blocksNbt.size(); i++) {
            CompoundTag blockNbt = blocksNbt.getCompound(i);
            ListTag blockPosNbt = blockNbt.getList("pos", 3);
            blocks.add(new buildingBlocks(
                new BlockPos(
                    blockPosNbt.getInt(0),
                    blockPosNbt.getInt(1),
                    blockPosNbt.getInt(2)
                ),
                    palette.get(blockNbt.getInt("state"))
            ));
        }
        return blocks;
    }

    /**
     * Assessing the nbt file from resources folder
     *
     * @param structureName the nbt file name of the given structure
     * @param resManager ResourceManager
     * @return all structure info in CompoundTag
     */
    public static CompoundTag getBuildingNbt(String structureName, ResourceManager resManager) {
        try {
            ResourceLocation rl = new ResourceLocation(
                    "architectvillagermod",
                    "structures/" + structureName + ".nbt"
            );
            Optional<Resource> rs = resManager.getResource(rl);
            return NbtIo.readCompressed(rs.get().open(), NbtAccounter.unlimitedHeap());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Parse through all the available block palettes given a structure
     *
     * @param nbt CompoundTags; various info of a given structure
     * @param resManager ResourceManager
     * @return list of various block palettes
     */
    public static ArrayList<BlockState> getBuildingPalette(CompoundTag nbt) {
        ArrayList<BlockState> palette = new ArrayList<>();
        // load in palette (list of unique block states)
        ListTag paletteNbt = nbt.getList("palette", 10);
        for (int i = 0; i < paletteNbt.size(); i++)
            palette.add(NbtUtils.readBlockState(BuiltInRegistries.BLOCK.asLookup(), paletteNbt.getCompound(i)));
        return palette;
    }
    
    /**
     * Generate the structure
     *
     * @param sitePos the placed location of the block
     * @param pContext UseOnContext
     * @param structureName nbt file name of the structure
     * @return list of various block palettes
     */
    public static void structureGen(BlockPos sitePos, UseOnContext pContext, String structureName) {
        ArrayList<buildingBlocks> blocksList = getBuildingBlocks(structureName, pContext.getLevel());
        for (buildingBlocks block : blocksList) {
            BlockPos relativeToWorld = new BlockPos (
                    sitePos.getX() + block.blockPosition().getX(),
                    sitePos.getY() +  block.blockPosition().getY() + 1,
                    sitePos.getZ() +  block.blockPosition().getZ()
            );
            pContext.getLevel().setBlockAndUpdate(relativeToWorld, block.blockState());
        }
    }
}
