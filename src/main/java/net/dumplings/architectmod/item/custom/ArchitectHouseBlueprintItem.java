package net.dumplings.architectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;

public class ArchitectHouseBlueprintItem extends BlueprintItemBase {
    public ArchitectHouseBlueprintItem(Properties pProperties) {
        super(pProperties);
        // idk what profession what to put here
        professionType = VillagerProfession.ARMORER;
        structureSize = new BlockPos(9, 10, 12);
        entitySpawnOffset = new BlockPos(5, 2, 8);
        structureFileName = "architect_home";
    }
}
