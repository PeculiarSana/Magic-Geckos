package com.github.peculiarsana.magicgeckos.client.renderer.entity;

import com.github.peculiarsana.magicgeckos.client.renderer.entity.layers.GeckoBodyLayer;
import com.github.peculiarsana.magicgeckos.client.renderer.entity.layers.GeckoEyeLayer;
import com.github.peculiarsana.magicgeckos.client.renderer.entity.model.GeckoModel;
import com.github.peculiarsana.magicgeckos.entities.GeckoEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CatCollarLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class GeckoRenderer extends MobRenderer<GeckoEntity, GeckoModel> {

    public GeckoRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GeckoModel(), 0.2f);
        //this.addLayer(new GeckoEyeLayer(this));
        this.addLayer(new GeckoBodyLayer(this));
    }

    protected void preRenderCallback(GeckoEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.scale(0.7F, 0.7F, 0.7F);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(GeckoEntity entity) {
        return entity.getGeckoTypeTex();
    }

    @Override
    protected void applyRotations(GeckoEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }
}