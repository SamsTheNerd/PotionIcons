package com.samsthenerd.potionicons.neoforge;

import com.samsthenerd.potionicons.PotionIconsConfig;
import com.samsthenerd.potionicons.PotionIconsMod;
import com.samsthenerd.potionicons.PotionIconsModClient;
import me.shedaniel.autoconfig.AutoConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.javafmlmod.FMLModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value=PotionIconsMod.MOD_ID, dist=Dist.CLIENT)
public class PotionIconsModNeoForgeClient {

    public PotionIconsModNeoForgeClient(FMLModContainer container, IEventBus modBus, Dist dist) {
        PotionIconsModClient.init();
        ModLoadingContext.get().getActiveContainer().registerExtensionPoint(IConfigScreenFactory.class, (mc, parent) -> AutoConfig.getConfigScreen(PotionIconsConfig.class, parent).get());
    }
}
