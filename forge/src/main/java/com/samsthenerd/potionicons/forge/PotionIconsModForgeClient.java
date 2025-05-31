package com.samsthenerd.potionicons.forge;

import com.samsthenerd.inline.Inline;
import com.samsthenerd.potionicons.PotionIconsConfig;
import com.samsthenerd.potionicons.PotionIconsMod;
import com.samsthenerd.potionicons.PotionIconsModClient;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;

//@Mod.EventBusSubscriber(modid = PotionIconsMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class PotionIconsModForgeClient {

//    @SubscribeEvent()
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            Inline.LOGGER.info("potion client init");
            PotionIconsModClient.init();
            ModLoadingContext.get().getActiveContainer()
                .registerExtensionPoint(ConfigScreenFactory.class,
                    () -> new ConfigScreenFactory((mc, parent) -> AutoConfig.getConfigScreen(PotionIconsConfig.class, parent).get()));
        });
    }
}
