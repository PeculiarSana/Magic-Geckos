package com.github.peculiarsana.magicgeckos.util;

import com.github.peculiarsana.magicgeckos.MagicGeckos;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, MagicGeckos.MODID);

    public static final RegistryObject<SoundEvent> GECKO_NOM = SOUND_EVENTS.register("gecko_nom", () ->
            new SoundEvent(new ResourceLocation(MagicGeckos.MODID, "gecko_nom"))
    );
}
