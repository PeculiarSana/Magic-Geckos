package com.github.peculiarsana.magicgeckos.init;

import com.github.peculiarsana.magicgeckos.MagicGeckos;
import com.github.peculiarsana.magicgeckos.MagicGeckos.MagicGeckosItemGroup;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = MagicGeckos.MODID, bus = Bus.MOD)
@ObjectHolder(MagicGeckos.MODID)
public class ItemInit {
    public static final Item test_egg = null;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new Item(new Item.Properties().group(MagicGeckosItemGroup.instance)).setRegistryName("test_egg"));
    }
}
