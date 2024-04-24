package net.dumplings.architectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;

public class SheperdHouseBlueprintItem extends BlueprintItemBase {
    public SheperdHouseBlueprintItem(Properties pProperties) {
        super(pProperties);
        professionType = VillagerProfession.SHEPHERD;
        structureSize = new BlockPos(13, 11, 13);
        entitySpawnOffset = new BlockPos(5, 2, 8);
        structureFileName = "shepherd_home";
    }
}
