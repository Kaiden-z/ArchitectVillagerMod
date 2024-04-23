package net.dumplings.architectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;

public class ArmorerHouseBlueprintItem extends BlueprintItemBase {
    public ArmorerHouseBlueprintItem(Properties pProperties) {
        super(pProperties);
        professionType = VillagerProfession.ARMORER;
        structureSize = new BlockPos(5, 5, 5);
        entitySpawnOffset = new BlockPos(3, 3, 4);
        structureFileName = "testing";
    }
}
