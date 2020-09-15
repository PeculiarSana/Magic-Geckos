package com.github.peculiarsana.magicgeckos.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.animation.controller.EntityAnimationController;
import software.bernie.geckolib.entity.IAnimatedEntity;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;

import javax.annotation.Nonnull;

public class GeckoEggEntity extends AnimalEntity implements IAnimatedEntity {
    EntityAnimationManager manager = new EntityAnimationManager();
    EntityAnimationController controller = new EntityAnimationController(
            this, "geckoController", 10, this::animationPredicate
    );

    public GeckoEggEntity(EntityType<? extends AnimalEntity> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        registerAnimationControllers();
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2.0d);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0d);
    }

    @Override
    public EntityAnimationManager getAnimationManager() {
        return manager;
    }

    private <E extends Entity> boolean animationPredicate(AnimationTestEvent<E> event) {
        //controller.setAnimation(new AnimationBuilder().addAnimation("animation.magicgeckos.egg_wiggle", true));
        return true;
    }

    private void registerAnimationControllers() {
        manager.addAnimationController(controller);
    }

    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return null;
    }
}
