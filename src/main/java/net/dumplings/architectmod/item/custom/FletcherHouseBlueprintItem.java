package net.dumplings.architectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;

public class FletcherHouseBlueprintItem extends BlueprintItemBase {
    public FletcherHouseBlueprintItem(Properties pProperties) {
        super(pProperties);
        professionType = VillagerProfession.FLETCHER;
        structureSize = new BlockPos(11, 11, 16);
        entitySpawnOffset = new BlockPos(5, 2, 4);
        structureFileName = "fletcherr_home";
    }
}
