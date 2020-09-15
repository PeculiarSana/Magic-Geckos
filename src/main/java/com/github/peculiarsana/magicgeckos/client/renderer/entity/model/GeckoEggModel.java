package com.github.peculiarsana.magicgeckos.client.renderer.entity.model;

import com.github.peculiarsana.magicgeckos.MagicGeckos;
import com.github.peculiarsana.magicgeckos.entities.GeckoEggEntity;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class GeckoEggModel extends AnimatedEntityModel<GeckoEggEntity> {

    private final AnimatedModelRenderer root;

    public GeckoEggModel()
    {
        textureWidth = 16;
        textureHeight = 16;
        root = new AnimatedModelRenderer(this);
        root.setRotationPoint(-0.25F, 24.0F, 1.0F);
        root.setTextureOffset(0, 0).addBox(-2.25F, -5.0F, -4.75F, 5.0F, 5.0F, 7.0F, 0.0F, false);
        root.setTextureOffset(0, 0).addBox(-1.75F, -4.5F, 2.25F, 4.0F, 4.0F, 2.0F, 0.0F, false);
        root.setTextureOffset(0, 0).addBox(-1.75F, -4.5F, -5.75F, 4.0F, 4.0F, 1.0F, 0.0F, false);
        root.setTextureOffset(0, 0).addBox(-1.25F, -4.0F, -6.5F, 3.0F, 3.0F, 1.0F, -0.25F, false);
        root.setTextureOffset(0, 0).addBox(-1.25F, -4.0F, 4.25F, 3.0F, 3.0F, 1.0F, 0.0F, false);
        root.setModelRendererName("root");
        this.registerModelRenderer(root);

        this.rootBones.add(root);
    }

    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation(MagicGeckos.MODID, "animations/egg_wiggle.json");
    }
}