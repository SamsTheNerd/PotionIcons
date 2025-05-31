package com.samsthenerd.potionicons.forge;


import com.samsthenerd.inline.InlineClient;
import com.samsthenerd.inline.forge.InlineForgeClient;
import com.samsthenerd.potionicons.PotionIconsMod;
import com.samsthenerd.potionicons.PotionIconsModClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(PotionIconsMod.MOD_ID)
public final class PotionIconsModForge {
    public PotionIconsModForge() {
        // Run our common setup.
        PotionIconsMod.init();
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(PotionIconsModForgeClient::onClientSetup);
//        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modBus.register(PotionIconsModClient.class));
    }

}
