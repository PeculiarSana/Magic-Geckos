package com.github.peculiarsana.magicgeckos.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityVariantProvider implements ICapabilitySerializable<CompoundNBT> {

    private final DefaultEntityVariant variant = new DefaultEntityVariant();
    private final LazyOptional<IEntityVariant> variantOptional = LazyOptional.of(() -> variant);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return variantOptional.cast();
    }

    @Override
    public CompoundNBT serializeNBT() {
        if (CapabilityEntityVariant.ENTITY_VARIANT_CAPABILITY == null) {
            return new CompoundNBT();
        } else {
            return (CompoundNBT) CapabilityEntityVariant.ENTITY_VARIANT_CAPABILITY.writeNBT(variant, null);
        }
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (CapabilityEntityVariant.ENTITY_VARIANT_CAPABILITY != null) {
            CapabilityEntityVariant.ENTITY_VARIANT_CAPABILITY.readNBT(variant, null, nbt);
        }
    }

    public void invalidate() {
        variantOptional.invalidate();
    }
}
