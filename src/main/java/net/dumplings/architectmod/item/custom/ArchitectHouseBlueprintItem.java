package net.dumplings.architectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;

public class ArchitectHouseBlueprintItem extends BlueprintItemBase {
    public ArchitectHouseBlueprintItem(Properties pProperties) {
        super(pProperties);
        // idk what profession what to put here
        professionType = VillagerProfession.ARMORER;
        structureSize = new BlockPos(5, 5, 5);
        entitySpawnOffset = new BlockPos(3, 3, 4);
        structureFileName = "architect_home";
    }
}
