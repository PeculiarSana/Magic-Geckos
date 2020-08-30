package com.github.peculiarsana.magicgeckos.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class CapabilityEntityVariant {
    @CapabilityInject(IEntityVariant.class)
    public static Capability<IEntityVariant> ENTITY_VARIANT_CAPABILITY = null;

    public static void register() {
        CapabilityManager.INSTANCE.register(IEntityVariant.class, new Storage(), DefaultEntityVariant::new);
    }

    public static class Storage implements Capability.IStorage<IEntityVariant> {

        @Nullable
        @Override
        public INBT writeNBT(Capability<IEntityVariant> capability, IEntityVariant instance, Direction side) {
            CompoundNBT tag = new CompoundNBT();
            tag.putInt("variant", instance.getVariant());
            return tag;
        }

        @Override
        public void readNBT(Capability<IEntityVariant> capability, IEntityVariant instance, Direction side, INBT nbt) {
            int variant = ((CompoundNBT) nbt).getInt("variant");
            instance.setVariant(variant);
        }
    }
}