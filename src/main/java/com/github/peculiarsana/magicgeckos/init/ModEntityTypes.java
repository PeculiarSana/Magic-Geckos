package com.github.peculiarsana.magicgeckos.init;

import com.github.peculiarsana.magicgeckos.MagicGeckos;
import com.github.peculiarsana.magicgeckos.entities.GeckoEggEntity;
import com.github.peculiarsana.magicgeckos.entities.GeckoEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, MagicGeckos.MODID);

    public static final RegistryObject<EntityType<GeckoEntity>> GECKO = ENTITY_TYPES.register("gecko",
            () -> EntityType.Builder.<GeckoEntity>create(GeckoEntity::new, EntityClassification.CREATURE)
                    .size(0.5f, 0.2f)
                    .build(new ResourceLocation(MagicGeckos.MODID, "gecko").toString())
    );

    public static final RegistryObject<EntityType<GeckoEggEntity>> GECKO_EGG = ENTITY_TYPES.register("gecko_egg",
            () -> EntityType.Builder.<GeckoEggEntity>create(GeckoEggEntity::new, EntityClassification.AMBIENT)
                    .size(0.3f, 0.1f)
                    .build(new ResourceLocation(MagicGeckos.MODID, "gecko_egg").toString())
    );
}
