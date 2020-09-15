
package com.github.peculiarsana.magicgeckos.client.renderer.entity.model;

import com.github.peculiarsana.magicgeckos.entities.GeckoEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;
import software.bernie.geckolib.animation.render.AnimatedModelRenderer;

public class GeckoModel extends AnimatedEntityModel<GeckoEntity> {
    public final AnimatedModelRenderer Body;
    public final AnimatedModelRenderer Torso;
    public final AnimatedModelRenderer Head;
    private final AnimatedModelRenderer Leg_FR1;
    private final AnimatedModelRenderer Leg_FR2;
    private final AnimatedModelRenderer Leg_FL1;
    private final AnimatedModelRenderer Leg_FL2;
    private final AnimatedModelRenderer Torso_Spines;
    private final AnimatedModelRenderer Body_Spines;
    private final AnimatedModelRenderer Tail1;
    private final AnimatedModelRenderer Tail2;
    private final AnimatedModelRenderer Tail3;
    private final AnimatedModelRenderer Tail2_Small;
    private final AnimatedModelRenderer Leg_BL1;
    private final AnimatedModelRenderer Leg_BL2;
    private final AnimatedModelRenderer Leg_BR1;
    private final AnimatedModelRenderer Leg_BR2;

    public GeckoModel()
    {
        textureWidth = 32;
        textureHeight = 16;
        Body = new AnimatedModelRenderer(this);
        Body.setRotationPoint(0.0F, 21.5F, -0.5F);
        Body.setTextureOffset(20, 0).addBox(-1.5F, -0.75F, 0.75F, 3.0F, 2.0F, 3.0F, -0.1F, false);
        Body.setModelRendererName("Body");
        this.registerModelRenderer(Body);

        Torso = new AnimatedModelRenderer(this);
        Torso.setRotationPoint(0.0F, -0.15F, 1.0F);
        Body.addChild(Torso);
        setRotationAngle(Torso, -0.0873F, 0.0F, 0.0F);
        Torso.setTextureOffset(0, 6).addBox(-1.5F, -0.6856F, -3.7254F, 3.0F, 2.0F, 4.0F, 0.0F, false);
        Torso.setModelRendererName("Torso");
        this.registerModelRenderer(Torso);

        Head = new AnimatedModelRenderer(this);
        Head.setRotationPoint(0.0F, -0.0549F, -3.4696F);
        Torso.addChild(Head);
        setRotationAngle(Head, 0.2618F, 0.0F, 0.0F);
        Head.setTextureOffset(6, 0).addBox(-1.5F, -1.1307F, -3.7557F, 3.0F, 2.0F, 4.0F, -0.1F, false);
        Head.setModelRendererName("Head");
        this.registerModelRenderer(Head);

        Leg_FR1 = new AnimatedModelRenderer(this);
        Leg_FR1.setRotationPoint(-1.3F, 0.6951F, -3.1196F);
        Torso.addChild(Leg_FR1);
        setRotationAngle(Leg_FR1, -2.4435F, 1.3963F, -3.1416F);
        Leg_FR1.setTextureOffset(0, 0).addBox(-0.5F, -0.5039F, -1.9079F, 1.0F, 1.0F, 2.0F, 0.0F, true);
        Leg_FR1.setModelRendererName("Leg_FR1");
        this.registerModelRenderer(Leg_FR1);

        Leg_FR2 = new AnimatedModelRenderer(this);
        Leg_FR2.setRotationPoint(-0.0302F, -0.0737F, -1.5767F);
        Leg_FR1.addChild(Leg_FR2);
        setRotationAngle(Leg_FR2, 0.7495F, -0.9893F, -0.0398F);
        Leg_FR2.setTextureOffset(16, 6).addBox(-0.6156F, -0.6066F, -1.9021F, 1.0F, 1.0F, 2.0F, 0.0F, true);
        Leg_FR2.setModelRendererName("Leg_FR2");
        this.registerModelRenderer(Leg_FR2);

        Leg_FL1 = new AnimatedModelRenderer(this);
        Leg_FL1.setRotationPoint(1.3F, 0.6951F, -3.1196F);
        Torso.addChild(Leg_FL1);
        setRotationAngle(Leg_FL1, -2.4435F, -1.3963F, 3.1416F);
        Leg_FL1.setTextureOffset(0, 0).addBox(-0.5F, -0.5039F, -1.9079F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        Leg_FL1.setModelRendererName("Leg_FL1");
        this.registerModelRenderer(Leg_FL1);

        Leg_FL2 = new AnimatedModelRenderer(this);
        Leg_FL2.setRotationPoint(0.0302F, -0.0737F, -1.5767F);
        Leg_FL1.addChild(Leg_FL2);
        setRotationAngle(Leg_FL2, 0.7495F, 0.9893F, 0.0398F);
        Leg_FL2.setTextureOffset(16, 6).addBox(-0.3844F, -0.6066F, -1.9021F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        Leg_FL2.setModelRendererName("Leg_FL2");
        this.registerModelRenderer(Leg_FL2);

        Torso_Spines = new AnimatedModelRenderer(this);
        Torso_Spines.setRotationPoint(0.0F, 0.0F, 0.0F);
        Torso.addChild(Torso_Spines);
        Torso_Spines.setTextureOffset(0, 0).addBox(0.75F, -1.6F, -2.75F, 0.0F, 1.0F, 1.0F, 0.0F, false);
        Torso_Spines.setTextureOffset(0, 0).addBox(0.75F, -1.6F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
        Torso_Spines.setTextureOffset(0, 0).addBox(-0.75F, -1.6F, -2.75F, 0.0F, 1.0F, 1.0F, 0.0F, false);
        Torso_Spines.setTextureOffset(0, 0).addBox(-0.75F, -1.6F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
        Torso_Spines.setModelRendererName("Torso_Spines");
        this.registerModelRenderer(Torso_Spines);

        Body_Spines = new AnimatedModelRenderer(this);
        Body_Spines.setRotationPoint(0.0F, 0.0F, 0.0F);
        Body.addChild(Body_Spines);
        Body_Spines.setTextureOffset(0, 0).addBox(0.75F, -1.6F, 1.75F, 0.0F, 1.0F, 1.0F, 0.0F, false);
        Body_Spines.setTextureOffset(0, 0).addBox(-0.75F, -1.6F, 1.75F, 0.0F, 1.0F, 1.0F, 0.0F, false);
        Body_Spines.setModelRendererName("Body_Spines");
        this.registerModelRenderer(Body_Spines);

        Tail1 = new AnimatedModelRenderer(this);
        Tail1.setRotationPoint(0.0F, -0.15F, 3.6F);
        Body.addChild(Tail1);
        setRotationAngle(Tail1, -0.3054F, 0.0F, 0.0F);
        Tail1.setTextureOffset(6, 12).addBox(-1.0F, -0.5699F, -0.4454F, 2.0F, 2.0F, 2.0F, -0.35F, false);
        Tail1.setModelRendererName("Tail1");
        this.registerModelRenderer(Tail1);

        Tail2 = new AnimatedModelRenderer(this);
        Tail2.setRotationPoint(0.0F, 0.0434F, 1.1538F);
        Tail1.addChild(Tail2);
        setRotationAngle(Tail2, 0.1745F, 0.0F, 0.0F);
        Tail2.setTextureOffset(14, 10).addBox(-1.0F, -0.6715F, -0.4699F, 2.0F, 2.0F, 4.0F, -0.25F, false);
        Tail2.setModelRendererName("Tail2");
        this.registerModelRenderer(Tail2);

        Tail3 = new AnimatedModelRenderer(this);
        Tail3.setRotationPoint(0.0F, 0.15F, 3.1F);
        Tail2.addChild(Tail3);
        setRotationAngle(Tail3, 0.1309F, 0.0F, 0.0F);
        Tail3.setTextureOffset(0, 13).addBox(-0.5F, -0.35F, 0.0238F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        Tail3.setModelRendererName("Tail3");
        this.registerModelRenderer(Tail3);

        Tail2_Small = new AnimatedModelRenderer(this);
        Tail2_Small.setRotationPoint(0.0F, 0.0F, 1.1F);
        Tail1.addChild(Tail2_Small);
        Tail2_Small.setTextureOffset(0, 0).addBox(-0.5F, -0.1F, -0.25F, 1.0F, 1.0F, 2.0F, -0.1F, false);
        Tail2_Small.setModelRendererName("Tail2_Small");
        this.registerModelRenderer(Tail2_Small);

        Leg_BL1 = new AnimatedModelRenderer(this);
        Leg_BL1.setRotationPoint(1.05F, 0.5F, 2.6F);
        Body.addChild(Leg_BL1);
        setRotationAngle(Leg_BL1, 2.7795F, -1.0155F, -1.9807F);
        Leg_BL1.setTextureOffset(0, 0).addBox(-0.5F, -0.5647F, -1.7415F, 1.0F, 1.0F, 2.0F, 0.0F, false);
        Leg_BL1.setModelRendererName("Leg_BL1");
        this.registerModelRenderer(Leg_BL1);

        Leg_BL2 = new AnimatedModelRenderer(this);
        Leg_BL2.setRotationPoint(0.0254F, 0.0274F, -1.5079F);
        Leg_BL1.addChild(Leg_BL2);
        setRotationAngle(Leg_BL2, 0.3496F, 0.9409F, -2.1782F);
        Leg_BL2.setTextureOffset(11, 6).addBox(-0.3199F, -0.5298F, -2.7057F, 1.0F, 1.0F, 3.0F, 0.0F, false);
        Leg_BL2.setModelRendererName("Leg_BL2");
        this.registerModelRenderer(Leg_BL2);

        Leg_BR1 = new AnimatedModelRenderer(this);
        Leg_BR1.setRotationPoint(-1.05F, 0.5F, 2.6F);
        Body.addChild(Leg_BR1);
        setRotationAngle(Leg_BR1, 2.7795F, 1.0155F, 1.9807F);
        Leg_BR1.setTextureOffset(0, 0).addBox(-0.5F, -0.5647F, -1.7415F, 1.0F, 1.0F, 2.0F, 0.0F, true);
        Leg_BR1.setModelRendererName("Leg_BR1");
        this.registerModelRenderer(Leg_BR1);

        Leg_BR2 = new AnimatedModelRenderer(this);
        Leg_BR2.setRotationPoint(-0.0254F, 0.0274F, -1.5079F);
        Leg_BR1.addChild(Leg_BR2);
        setRotationAngle(Leg_BR2, 0.3837F, -0.971F, 2.1264F);
        Leg_BR2.setTextureOffset(11, 6).addBox(-0.6801F, -0.5296F, -2.7059F, 1.0F, 1.0F, 3.0F, 0.0F, true);
        Leg_BR2.setModelRendererName("Leg_BR2");
        this.registerModelRenderer(Leg_BR2);

        this.rootBones.add(Body);
    }

    @Override
    public ResourceLocation getAnimationFileLocation()
    {
        return new ResourceLocation("magicgeckos", "animations/gecko_walk.json");
    }
}