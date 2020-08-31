package com.github.peculiarsana.magicgeckos;

import com.github.peculiarsana.magicgeckos.data.*;
import com.github.peculiarsana.magicgeckos.init.BlockInit;
import com.github.peculiarsana.magicgeckos.init.ItemInit;
import com.github.peculiarsana.magicgeckos.init.ModEntityTypes;
import com.github.peculiarsana.magicgeckos.items.ModSpawnEggItem;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MagicGeckos.MODID)
@Mod.EventBusSubscriber(modid = MagicGeckos.MODID, bus = Bus.MOD)
public class MagicGeckos {
    public static final String MODID = "magicgeckos";
    public static final Logger LOGGER = LogManager.getLogger();
    public static MagicGeckos instance;

    public MagicGeckos() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        instance = this;

        //Register Objects to the Deferred Register
        ItemInit.ITEMS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);



        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onRegisterBlocks(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(MagicGeckosItemGroup.instance);
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public void serverSetup(FMLServerStartingEvent event) {

    }

    public static class MagicGeckosItemGroup extends ItemGroup {
        public static final MagicGeckosItemGroup instance = new MagicGeckosItemGroup(ItemGroup.GROUPS.length, "magicgeckostab");
        private MagicGeckosItemGroup(int index, String label) {
            super(index, label);
        }
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.DEF_ITEM.get());
        }
    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        ModSpawnEggItem.InitSpawnEggs();
    }
}
