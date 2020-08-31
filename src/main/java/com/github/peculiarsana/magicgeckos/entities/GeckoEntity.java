package com.github.peculiarsana.magicgeckos.entities;

import com.github.peculiarsana.magicgeckos.MagicGeckos;
import com.github.peculiarsana.magicgeckos.init.ModEntityTypes;
import com.google.common.collect.Maps;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.animation.controller.EntityAnimationController;
import software.bernie.geckolib.entity.IAnimatedEntity;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;

import javax.annotation.Nullable;
import java.util.Map;


public class GeckoEntity extends TameableEntity implements IAnimatedEntity {

    private static final DataParameter<Integer> GECKO_TYPE = EntityDataManager.createKey(GeckoEntity.class, DataSerializers.VARINT);
    public static final Map<Integer, ResourceLocation> TEXTURE_BY_ID = Util.make(Maps.newHashMap(), (typeTex) -> {
        typeTex.put(0, new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/normal.png"));
        typeTex.put(1, new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/black_velvet.png"));
        typeTex.put(2, new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/radar.png"));
        typeTex.put(3, new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/super_snow.png"));
        typeTex.put(4, new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/creamsicle.png"));
        typeTex.put(100, new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/acrid.png"));
    });
    EntityAnimationManager manager = new EntityAnimationManager();
    EntityAnimationController controller = new EntityAnimationController(
            this, "geckoController", 20, this::animationPredicate
    );

    public GeckoEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
        registerAnimationControllers();
    }

    public ResourceLocation getGeckoTypeTex() {
        return TEXTURE_BY_ID.getOrDefault(this.getGeckoType(), TEXTURE_BY_ID.get(0));
    }

    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        GeckoEntity entity = new GeckoEntity(ModEntityTypes.GECKO.get(), this.world);
        entity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(entity)),
                SpawnReason.BREEDING, (ILivingEntityData) null, (CompoundNBT) null);
        entity.setGrowingAge(-10000);
        if (ageable instanceof GeckoEntity) {
            if (this.rand.nextBoolean()) {
                entity.setGeckoType(this.getGeckoType());
            } else {
                entity.setGeckoType(((GeckoEntity) ageable).getGeckoType());
            }
        }
        return entity;
    }

    public int getGeckoType() {
         return this.dataManager.get(GECKO_TYPE);
    }

    public void setGeckoType(int type) {
        dataManager.set(GECKO_TYPE, type);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("GeckoType", this.getGeckoType());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setGeckoType(compound.getInt("GeckoType"));
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(
                this, PlayerEntity.class, 5.0F, 1D, 1.5D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(GECKO_TYPE, 1);
    }

    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setGeckoType(this.rand.nextInt(5));
        return spawnDataIn;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0d);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15d);
    }

    @Override
    public EntityAnimationManager getAnimationManager() {
        return manager;
    }

    private <E extends Entity> boolean animationPredicate(AnimationTestEvent<E> event) {
        manager.setAnimationSpeed(2.0D);
        if (event.isWalking()) {
            controller.setAnimation(new AnimationBuilder()
                    .addAnimation("animation.magicgeckos.gecko_walk", true));
            return true;
        }
        return event.isWalking();
    }

    private void registerAnimationControllers() {
        manager.addAnimationController(controller);
    }
}