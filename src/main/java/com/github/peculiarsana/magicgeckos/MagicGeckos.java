package com.github.peculiarsana.magicgeckos;

import com.github.peculiarsana.magicgeckos.init.ItemInit;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MagicGeckos.MODID)
public class MagicGeckos {
    public static final String MODID = "magicgeckos";
    public static final Logger LOGGER = LogManager.getLogger();
    public static MagicGeckos instance;

    public MagicGeckos() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);
        instance = this;

        LOGGER.debug("This is a test to see if Geckos have the ability to speak.");

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    public static class MagicGeckosItemGroup extends ItemGroup {
        public static final MagicGeckosItemGroup instance = new MagicGeckosItemGroup(ItemGroup.GROUPS.length, "magicgeckostab");
        private MagicGeckosItemGroup(int index, String label) {
            super(index, label);
        }
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.test_egg);
        }
    }
}
