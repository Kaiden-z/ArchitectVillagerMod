package net.dumplings.architectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;

public class LibrarianHouseBlueprintItem extends BlueprintItemBase {
    public LibrarianHouseBlueprintItem(Properties pProperties) {
        super(pProperties);
        professionType = VillagerProfession.LIBRARIAN;
        structureSize = new BlockPos(5, 5, 5);
        structureFileName = "testing";
    }
}
