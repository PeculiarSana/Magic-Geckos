package com.github.peculiarsana.magicgeckos.init;

import com.github.peculiarsana.magicgeckos.MagicGeckos;
import com.github.peculiarsana.magicgeckos.MagicGeckos.MagicGeckosItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.audio.Sound;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(MagicGeckos.MODID)
@Mod.EventBusSubscriber(modid = MagicGeckos.MODID, bus = Bus.MOD)
public class BlockInit {
    public static final Block test_block = null;

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event){
        event.getRegistry().register(new Block(Block.Properties
                .create(Material.ROCK)
                .hardnessAndResistance(1.0f, 1.0f))
                .setRegistryName("test_block.json")
        );
    }

    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event){
        event.getRegistry().register(new BlockItem(test_block, new Item.Properties().group(MagicGeckosItemGroup.instance))
                .setRegistryName("test_block.json")
        );
    }
}
