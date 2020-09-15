package com.github.peculiarsana.magicgeckos.client.renderer.entity;

import com.github.peculiarsana.magicgeckos.MagicGeckos;
import com.github.peculiarsana.magicgeckos.client.renderer.entity.model.GeckoEggModel;
import com.github.peculiarsana.magicgeckos.entities.GeckoEggEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class GeckoEggRenderer extends MobRenderer<GeckoEggEntity, GeckoEggModel> {

    public GeckoEggRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GeckoEggModel(), 0.1f);
    }

    protected void preRenderCallback(GeckoEggEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.scale(0.35F, 0.35F, 0.35F);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(GeckoEggEntity entity) {
        return new ResourceLocation(MagicGeckos.MODID, "textures/entity/gecko_egg.png");
    }

    @Override
    protected void applyRotations(GeckoEggEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }
}