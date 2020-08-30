package com.github.peculiarsana.magicgeckos.data;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

/**
 * Default implementation of IEntityVariant
 */
public class DefaultEntityVariant implements IEntityVariant{

    private int variant;

    @Override
    public void setVariant(int variant) {
        this.variant = variant;
    }

    @Override
    public int getVariant() {
        return variant;
    }
}
