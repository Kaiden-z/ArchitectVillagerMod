package net.dumplings.architectmod.item;

import net.dumplings.architectmod.ArchitectVillagerMod;
import net.dumplings.architectmod.item.custom.ArmorerHouseBlueprintItem;
import net.dumplings.architectmod.item.custom.ButcherHouseBlueprintItem;
import net.dumplings.architectmod.item.custom.CartographerHouseBlueprintItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ArchitectVillagerMod.MODID);

    public static final RegistryObject<Item> ARMORER_HOUSE_BLUEPRINT = ITEMS.register("armorer_house_blueprint",
            () -> new ArmorerHouseBlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> BUTCHER_HOUSE_BLUEPRINT = ITEMS.register("butcher_house_blueprint",
            () -> new ButcherHouseBlueprintItem(new Item.Properties()));
    public static final RegistryObject<Item> CARTOGRAPHER_HOUSE_BLUEPRINT = ITEMS.register("cartographer_house_blueprint",
            () -> new CartographerHouseBlueprintItem(new Item.Properties()));
    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }


}
