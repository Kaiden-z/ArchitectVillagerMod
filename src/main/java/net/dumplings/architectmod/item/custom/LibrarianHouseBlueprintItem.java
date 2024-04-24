package net.dumplings.architectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;

public class LibrarianHouseBlueprintItem extends BlueprintItemBase {
    public LibrarianHouseBlueprintItem(Properties pProperties) {
        super(pProperties);
        professionType = VillagerProfession.LIBRARIAN;
        structureSize = new BlockPos(12, 15, 15);
        entitySpawnOffset = new BlockPos(1, 1, 1);
        structureFileName = "librarian_home";
    }
}
