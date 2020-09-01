package com.github.peculiarsana.magicgeckos.init;

import com.github.peculiarsana.magicgeckos.MagicGeckos;
import com.github.peculiarsana.magicgeckos.MagicGeckos.MagicGeckosItemGroup;
import com.github.peculiarsana.magicgeckos.items.GeckoFoodItem;
import com.github.peculiarsana.magicgeckos.items.ModSpawnEggItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MagicGeckos.MODID);

    //Default Item
    public static final RegistryObject<Item> DEF_ITEM = ITEMS.register("def_item",
            () -> new Item(new Item.Properties().group(MagicGeckosItemGroup.instance)));

    //Test Egg
    public static final RegistryObject<ModSpawnEggItem> TEST_EGG = ITEMS.register("test_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.GECKO, 0xF5CD67, 0x474130,
                    new Item.Properties().group(MagicGeckosItemGroup.instance)
                    .maxStackSize(16)
            ));

    //Worm
    public static final RegistryObject<GeckoFoodItem> WORM = ITEMS.register("worm",
            () -> new GeckoFoodItem(new Item.Properties().group(MagicGeckosItemGroup.instance)));
}
