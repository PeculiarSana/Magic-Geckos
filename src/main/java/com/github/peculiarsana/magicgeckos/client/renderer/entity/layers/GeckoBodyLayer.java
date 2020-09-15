package com.github.peculiarsana.magicgeckos.client.renderer.entity.layers;

import com.github.peculiarsana.magicgeckos.client.renderer.entity.model.GeckoModel;
import com.github.peculiarsana.magicgeckos.entities.GeckoEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.awt.*;

@OnlyIn(Dist.CLIENT)
public class GeckoBodyLayer extends LayerRenderer<GeckoEntity, GeckoModel> {
    private final GeckoModel model = new GeckoModel();

    public GeckoBodyLayer(IEntityRenderer<GeckoEntity, GeckoModel> renderer) {
        super(renderer);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, GeckoEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entitylivingbaseIn.getGeckoType() != 100 && entitylivingbaseIn.getPattern() != 0) {
            Color colour = Color.decode(entitylivingbaseIn.getPatternColour());
            float red = (float)colour.getRed() / 255;
            float green = (float)colour.getGreen() / 255;
            float blue = (float)colour.getBlue() / 255;
            ResourceLocation GECKO_PATTERN = entitylivingbaseIn.PATTERN_TEX.get(entitylivingbaseIn.getPattern());
            this.model.Torso.copyModelAngles(this.getEntityModel().Torso);
            this.model.Body.copyModelAngles(this.getEntityModel().Body);
            renderCopyCutoutModelAnimated(this.getEntityModel(), this.model, GECKO_PATTERN, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, red, green, blue);
        }
    }

    protected static <T extends LivingEntity> void renderCopyCutoutModelAnimated(EntityModel<T> modelParentIn, EntityModel<T> modelIn, ResourceLocation textureLocationIn, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks, float red, float green, float blue) {
        if (!entityIn.isInvisible()) {
            modelParentIn.copyModelAttributesTo(modelIn);
            modelIn.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            renderCutoutModel(modelIn, textureLocationIn, matrixStackIn, bufferIn, packedLightIn, entityIn, red, green, blue);
        }
    }
}
