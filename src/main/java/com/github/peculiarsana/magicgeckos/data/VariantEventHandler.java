package com.github.peculiarsana.magicgeckos.data;

import com.github.peculiarsana.magicgeckos.MagicGeckos;
import com.github.peculiarsana.magicgeckos.entities.GeckoEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class VariantEventHandler {
        public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof GeckoEntity) {
                GeckoEntity entity = (GeckoEntity)event.getObject();
                EntityVariantProvider provider = new EntityVariantProvider();
                event.addCapability(new ResourceLocation(MagicGeckos.MODID, "variant"), provider);
                event.addListener(provider::invalidate);
            }
        }
}
