package com.samsthenerd.potionicons.neoforge;

import com.samsthenerd.inline.InlineClient;
import com.samsthenerd.inline.config.InlineConfigHandler;
import com.samsthenerd.potionicons.PotionIconsConfig;
import com.samsthenerd.potionicons.PotionIconsModClient;
import me.shedaniel.autoconfig.AutoConfig;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;

import com.samsthenerd.potionicons.PotionIconsMod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(PotionIconsMod.MOD_ID)
public final class PotionIconsModNeoForge {
    public PotionIconsModNeoForge(IEventBus modBus, ModContainer container) {
        // Run our common setup.
        PotionIconsMod.init();
    }

}
