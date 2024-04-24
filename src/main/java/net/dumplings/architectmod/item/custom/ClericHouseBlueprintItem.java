package net.dumplings.architectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;

public class ClericHouseBlueprintItem extends BlueprintItemBase {
    public ClericHouseBlueprintItem(Properties pProperties) {
        super(pProperties);
        professionType = VillagerProfession.CLERIC;
        structureSize = new BlockPos(10, 19, 11);
        entitySpawnOffset = new BlockPos(1, 1, 1);
        structureFileName = "cleric_home";
    }
}
