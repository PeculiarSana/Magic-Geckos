package com.github.peculiarsana.magicgeckos.client.entity.renderer;

import com.github.peculiarsana.magicgeckos.MagicGeckos;
import com.github.peculiarsana.magicgeckos.entities.GeckoEntity;
import com.github.peculiarsana.magicgeckos.client.entity.renderer.model.GeckoModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class GeckoRenderer extends MobRenderer<GeckoEntity, GeckoModel> {

    protected static ResourceLocation TEXTURE = null;

    public GeckoRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GeckoModel(), 0.2f);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(GeckoEntity entity) {
            switch(entity.getGeckoType()) {
                case 0: TEXTURE = new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/leopard.png"); break;
                case 1: TEXTURE = new ResourceLocation(MagicGeckos.MODID + ":textures/entity/gecko/velvet.png"); break;
            }
        return TEXTURE;
    }

    @Override
    protected void applyRotations(GeckoEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
    }
}
