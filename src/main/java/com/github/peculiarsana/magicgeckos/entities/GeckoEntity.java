package com.github.peculiarsana.magicgeckos.entities;

import com.github.peculiarsana.magicgeckos.MagicGeckos;
import com.github.peculiarsana.magicgeckos.init.ItemInit;
import com.github.peculiarsana.magicgeckos.init.ModEntityTypes;
import com.github.peculiarsana.magicgeckos.util.ModSoundEvents;
import com.google.common.collect.Maps;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.animation.controller.EntityAnimationController;
import software.bernie.geckolib.entity.IAnimatedEntity;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

//TODO:
// Ability to cling onto walls
// Taming
// Drops
// Sound Effects
// More Rare variants
// Faster anim speed when running
// Head Looking
public class GeckoEntity extends TameableEntity implements IAnimatedEntity {

    private static final DataParameter<Byte> TYPE = EntityDataManager.createKey(GeckoEntity.class, DataSerializers.BYTE);
    public static final Map<Byte, ResourceLocation> BASE_TEX = Util.make(Maps.newHashMap(), (typeTex) -> {
        typeTex.put((byte)0, new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/normal.png"));
        typeTex.put((byte)1, new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/black_velvet.png"));
        typeTex.put((byte)2, new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/radar.png"));
        typeTex.put((byte)3, new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/super_snow.png"));
        typeTex.put((byte)4, new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/creamsicle.png"));
        typeTex.put((byte)100, new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/acrid.png"));
    });
    private static final DataParameter<Byte> EYE_COLOUR = EntityDataManager.createKey(GeckoEntity.class, DataSerializers.BYTE);
    public static final Map<Byte, String> EYE_COLOUR_HEX = Util.make(Maps.newHashMap(), (eyeCol) -> {
        eyeCol.put((byte)0, "#000000");//Black
        eyeCol.put((byte)1, "#8A8A8A");//Silver
        eyeCol.put((byte)2, "#9E0000");//Red
        eyeCol.put((byte)3, "#FFD3F7");//Pink
    });
    private static final DataParameter<Byte> PATTERN = EntityDataManager.createKey(GeckoEntity.class, DataSerializers.BYTE);
    public static final Map<Byte, ResourceLocation> PATTERN_TEX = Util.make(Maps.newHashMap(), (typeTex) -> {
        typeTex.put((byte)0, new ResourceLocation(""));
        typeTex.put((byte)1, new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/body/stripe.png"));
        typeTex.put((byte)2, new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/body/drops.png"));
        typeTex.put((byte)3, new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/body/jungle.png"));
        typeTex.put((byte)4, new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/body/reverse_stripe.png"));
    });
    private static final DataParameter<Byte> PATTERN_COLOUR = EntityDataManager.createKey(GeckoEntity.class, DataSerializers.BYTE);
    public static final Map<Byte, String> PATTERN_COLOUR_HEX = Util.make(Maps.newHashMap(), (patCol) -> {
        patCol.put((byte)0, "#000000");//Black
        patCol.put((byte)1, "#270000");//Maroon
        patCol.put((byte)2, "#FFFFFF");//White
        patCol.put((byte)3, "#FFD3F7");//Pink
    });
    //Food List
    private static final List<Item> FOOD_ITEMS = Util.make(new ArrayList<Item>(), (list) -> {
        list.add(ItemInit.WORM.get());
    });
    private static final Predicate<ItemEntity> TARGET_SELECTOR = (t) ->
            !t.cannotPickup() && t.isAlive() && FOOD_ITEMS.contains(t.getItem().getItem());

    private GeckoEntity.AvoidPlayerGoal<PlayerEntity> avoidPlayerGoal;

    EntityAnimationManager manager = new EntityAnimationManager();
    EntityAnimationController controller = new EntityAnimationController(
            this, "geckoController", 10, this::animationPredicate
    );

    public GeckoEntity(EntityType<? extends TameableEntity> type, World worldIn) {
        super(type, worldIn);
        manager.setAnimationSpeed(2.0D);
        registerAnimationControllers();
    }

    public ResourceLocation getGeckoTypeTex() {
        return BASE_TEX.getOrDefault(this.getGeckoType(), BASE_TEX.get(0));
    }

    //Controls what occurs when clicked with an egg
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        GeckoEntity entity = new GeckoEntity(ModEntityTypes.GECKO.get(), this.world);
        entity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(entity)),
                SpawnReason.BREEDING, (ILivingEntityData) null, (CompoundNBT) null);
        if (ageable instanceof GeckoEntity) {
            if (this.rand.nextBoolean()) {
                entity.setGeckoType(this.getGeckoType());
            } else {
                entity.setGeckoType(((GeckoEntity) ageable).getGeckoType());
            }
            entity.setGrowingAge(-20000);
        }
        return entity;
    }

    public byte getGeckoType() {
         return this.dataManager.get(TYPE);
    }

    public void setGeckoType(byte type) {
        this.dataManager.set(TYPE, type);
    }

    public String getEyeColour() {
        byte key = this.dataManager.get(EYE_COLOUR);
        return EYE_COLOUR_HEX.get(key);
    }

    public void setEyeColour(byte colour) {
        this.dataManager.set(EYE_COLOUR, colour);
    }

    public byte getPattern() {
        return this.dataManager.get(PATTERN);
    }

    public void setPattern(byte body) {
        this.dataManager.set(PATTERN, body);
    }

    public String getPatternColour(){
        return PATTERN_COLOUR_HEX.get(this.dataManager.get(PATTERN_COLOUR));
    }

    public void setPatternColour(byte colour) {
        this.dataManager.set(PATTERN_COLOUR, colour);
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putByte("Type", this.getGeckoType());
        compound.putByte("Eyes", this.dataManager.get(EYE_COLOUR));
        compound.putByte("Pattern", this.getPattern());
        compound.putByte("PatternColour", this.dataManager.get(PATTERN_COLOUR));
    }

    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setGeckoType(compound.getByte("Type"));
        this.setEyeColour(compound.getByte("Eyes"));
        this.setPattern(compound.getByte("Pattern"));
        this.setPatternColour(compound.getByte("PatternColour"));
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(
                this, PlayerEntity.class, 5.0F, 1D, 1.5D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new GeckoEntity.FindItemsGoal());
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(TYPE, (byte)1);
        this.dataManager.register(EYE_COLOUR, (byte)1);
        this.dataManager.register(PATTERN, (byte)1);
        this.dataManager.register(PATTERN_COLOUR, (byte)1);
    }

    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setGeckoType((byte)this.rand.nextInt(5));
        this.setEyeColour((byte)this.rand.nextInt(4));
        this.setPattern((byte)this.rand.nextInt(5));
        this.setPatternColour((byte)this.rand.nextInt(4));

        return spawnDataIn;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3.0d);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15d);
    }

    @Override
    public void livingTick() {
        ItemStack itemstack = this.getItemStackFromSlot(EquipmentSlotType.MAINHAND);
        if (!itemstack.isEmpty()) {
            Vec3d vec3d = (new Vec3d(((double)this.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D)).rotatePitch(-this.rotationPitch * ((float)Math.PI / 180F)).rotateYaw(-this.rotationYaw * ((float)Math.PI / 180F));
            this.world.addParticle(new ItemParticleData(ParticleTypes.ITEM, itemstack), this.getPosX() + this.getLookVec().x / 2.0D, this.getPosY(), this.getPosZ() + this.getLookVec().z / 2.0D, vec3d.x, vec3d.y + 0.05D, vec3d.z);
        }
        super.livingTick();
    }

    // Creates a goal to avoid the player and only executes if this instance isn't tamed
    protected void setupTamedAI() {
        if (this.avoidPlayerGoal == null) {
            this.avoidPlayerGoal = new GeckoEntity.AvoidPlayerGoal<>(this, PlayerEntity.class, 16.0F, 0.8D, 1.33D);
        }

        this.goalSelector.removeGoal(this.avoidPlayerGoal);
        if (!this.isTamed()) {
            this.goalSelector.addGoal(4, this.avoidPlayerGoal);
        }
    }

    static class AvoidPlayerGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
        private final GeckoEntity gecko;

        public AvoidPlayerGoal(GeckoEntity geckoIn, Class<T> entityClassToAvoidIn, float avoidDistanceIn, double farSpeedIn, double nearSpeedIn) {
            super(geckoIn, entityClassToAvoidIn, avoidDistanceIn, farSpeedIn, nearSpeedIn, EntityPredicates.CAN_AI_TARGET::test);
            this.gecko = geckoIn;
        }
        public boolean shouldExecute() {
            return !this.gecko.isTamed() && super.shouldExecute();
        }

        public boolean shouldContinueExecuting() {
            return !this.gecko.isTamed() && super.shouldContinueExecuting();
        }
    }

    //Goal to allow the Geckos to look for and navigate towards items in FOOD_ITEMS
    class FindItemsGoal extends Goal {
        /*public FindItemsGoal() {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }*/

        @Override
        public boolean shouldExecute() {
            if (GeckoEntity.this.rand.nextInt(17) != 0) { return false;}
            else {
                List<ItemEntity> list = GeckoEntity.this.world.getEntitiesWithinAABB(ItemEntity.class,
                        GeckoEntity.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D), GeckoEntity.TARGET_SELECTOR);
                return !list.isEmpty();
            }
        }

        @Override
        public void tick() {
            List<ItemEntity> list = GeckoEntity.this.world.getEntitiesWithinAABB(ItemEntity.class, GeckoEntity.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D), GeckoEntity.TARGET_SELECTOR);
            if (!list.isEmpty()) {
                GeckoEntity.this.tryMoveToEntityLiving(list.get(0), (double)2.0F);
                manager.setAnimationSpeed(2.5D);
                if (!GeckoEntity.this.tryMoveToEntityLiving(list.get(0), (double)2.0F) && GeckoEntity.this.getPosition().withinDistance(list.get(0).getPosition(), 1.0D))
                {
                    playSound(ModSoundEvents.GECKO_NOM.get(), 1.0F, 1.0F);
                    list.get(0).getItem().shrink(1);
                    list.clear();
                }
            }
        }

        public void startExecuting() {
            List<ItemEntity> list = GeckoEntity.this.world.getEntitiesWithinAABB(ItemEntity.class, GeckoEntity.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D), GeckoEntity.TARGET_SELECTOR);
            if (!list.isEmpty()) {
                GeckoEntity.this.tryMoveToEntityLiving(list.get(0), (double)2.0F);
                manager.setAnimationSpeed(2.5D);
                //controller.setAnimation(new AnimationBuilder().addAnimation("animation.magicgeckos.gecko_walk", true));
            }
        }
    }

    public boolean tryMoveToEntityLiving(Entity entityIn, double speedIn) {
        //p_75494_2_ is the distance to stop from target entity, relative to the block it's on
        Path path = this.getNavigator().getPathToEntity(entityIn, 0);
        return path != null && this.getNavigator().setPath(path, speedIn);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return sizeIn.height * 0.8f;
    }

    @Override
    public EntityAnimationManager getAnimationManager() {
        return manager;
    }

    private <E extends Entity> boolean animationPredicate(AnimationTestEvent<E> event) {
        if (event.isWalking())
        {
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