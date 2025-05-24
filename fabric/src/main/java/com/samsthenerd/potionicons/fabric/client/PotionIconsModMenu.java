package com.samsthenerd.potionicons.fabric.client;

import com.samsthenerd.inline.config.InlineConfigHandler;
import com.samsthenerd.potionicons.PotionIconsConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;

public class PotionIconsModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(PotionIconsConfig.class, parent).get();
    }
}
