package net.dumplings.architectmod.item;

import net.dumplings.architectmod.ArchitectVillagerMod;
import net.dumplings.architectmod.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ArchitectVillagerMod.MODID);

    public static final RegistryObject<Item> ARMORER_HOUSE_BLUEPRINT = ITEMS.register("armorer_house_blueprint",
            () -> new ArmorerHouseBlueprintItem(new Item.Properties()
                    .stacksTo(1)));
    public static final RegistryObject<Item> BUTCHER_HOUSE_BLUEPRINT = ITEMS.register("butcher_house_blueprint",
            () -> new ButcherHouseBlueprintItem(new Item.Properties()
                    .stacksTo(1)));
    public static final RegistryObject<Item> CARTOGRAPHER_HOUSE_BLUEPRINT = ITEMS.register("cartographer_house_blueprint",
            () -> new CartographerHouseBlueprintItem(new Item.Properties()
                    .stacksTo(1)));

    public static final RegistryObject<Item> CLERIC_HOUSE_BLUEPRINT = ITEMS.register("cleric_house_blueprint",
            () -> new ClericHouseBlueprintItem(new Item.Properties()
                    .stacksTo(1)));

    public static final RegistryObject<Item> FARMER_HOUSE_BLUEPRINT = ITEMS.register("farmer_house_blueprint",
            () -> new FarmerHouseBlueprintItem(new Item.Properties()
                    .stacksTo(1)));

    public static final RegistryObject<Item> FISHERMAN_HOUSE_BLUEPRINT = ITEMS.register("fisherman_house_blueprint",
            () -> new FishermanHouseBlueprintItem(new Item.Properties()
                    .stacksTo(1)));

    public static final RegistryObject<Item> FLETCHER_HOUSE_BLUEPRINT = ITEMS.register("fletcher_house_blueprint",
            () -> new FletcherHouseBlueprintItem(new Item.Properties()
                    .stacksTo(1)));

    public static final RegistryObject<Item> LEATHERWORKER_HOUSE_BLUEPRINT = ITEMS.register("leatherworker_house_blueprint",
            () -> new LeatherworkerHouseBlueprintItem(new Item.Properties()
                    .stacksTo(1)));

    public static final RegistryObject<Item> LIBRARIAN_HOUSE_BLUEPRINT = ITEMS.register("librarian_house_blueprint",
            () -> new LibrarianHouseBlueprintItem(new Item.Properties()
                    .stacksTo(1)));

    public static final RegistryObject<Item> STONE_MASON_HOUSE_BLUEPRINT = ITEMS.register("stone_mason_house_blueprint",
            () -> new StoneMasonHouseBlueprintItem(new Item.Properties()
                    .stacksTo(1)));

    public static final RegistryObject<Item> SHEPERD_HOUSE_BLUEPRINT = ITEMS.register("sheperd_house_blueprint",
            () -> new SheperdHouseBlueprintItem(new Item.Properties()
                    .stacksTo(1)));

    public static final RegistryObject<Item> TOOLSMITH_HOUSE_BLUEPRINT = ITEMS.register("toolsmith_house_blueprint",
            () -> new ToolsmithHouseBlueprintItem(new Item.Properties()
                    .stacksTo(1)));

    public static final RegistryObject<Item> WEAPONSMITH_HOUSE_BLUEPRINT = ITEMS.register("weaponsmith_house_blueprint",
            () -> new WeaponsmithHouseBlueprintItem(new Item.Properties()
                    .stacksTo(1)));

    public static final RegistryObject<Item> BLUEPRINT_PAPER = ITEMS.register("blueprint_paper",
            () -> new Item(new Item.Properties()));
    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }


}
