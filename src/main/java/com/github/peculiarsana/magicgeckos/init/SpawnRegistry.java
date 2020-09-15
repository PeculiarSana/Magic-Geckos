package com.github.peculiarsana.magicgeckos.init;

import com.github.peculiarsana.magicgeckos.MagicGeckos;
import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//TODO: Set up spawning based on Type instead of direct Biome
@Mod.EventBusSubscriber(modid = MagicGeckos.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class SpawnRegistry {
    public static void addSpawn() {
        Set<Type> types = new HashSet<>();
        types.add(Type.HOT);
        types.add(Type.JUNGLE);
        types.add(Type.SAVANNA);

        for (Biome biomes : ForgeRegistries.BIOMES)
        {
            for (Type t : types)
            {
                if (BiomeDictionary.hasType(biomes, t))
                {
                    List<Biome.SpawnListEntry> spawnList = biomes.getSpawns(EntityClassification.CREATURE);
                    spawnList.add(new Biome.SpawnListEntry(ModEntityTypes.GECKO.get(), 1, 1, 2));
                }
            }
        }

    }
}
