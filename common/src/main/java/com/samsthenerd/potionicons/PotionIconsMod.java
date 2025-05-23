package com.samsthenerd.potionicons;

import com.samsthenerd.inline.api.InlineAPI;
import com.samsthenerd.inline.api.data.ModIconData.ModIconDataType;
import com.samsthenerd.inline.tooltips.CustomTooltipManager;
import com.samsthenerd.potionicons.EffectInlineData.EffectDataType;
import net.minecraft.text.MutableText;
import net.minecraft.util.Identifier;

public final class PotionIconsMod {
    public static final String MOD_ID = "potionicons";

    public static Identifier id(String path){
        return Identifier.of(MOD_ID, path);
    }

    public static void init() {
        InlineAPI.INSTANCE.addDataType(EffectDataType.INSTANCE);
        CustomTooltipManager.registerProvider(EffectDataTTProvider.INSTANCE);
    }
}
