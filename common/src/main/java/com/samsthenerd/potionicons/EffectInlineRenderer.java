package com.samsthenerd.potionicons;

import com.samsthenerd.inline.api.client.GlowHandling;
import com.samsthenerd.inline.api.client.InlineRenderer;
import com.samsthenerd.inline.api.data.ItemInlineData;
import com.samsthenerd.inline.utils.Spritelike;
import com.samsthenerd.inline.utils.SpritelikeRenderers;
import com.samsthenerd.inline.utils.SpritelikeUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.text.Style;
import net.minecraft.util.Identifier;

public class EffectInlineRenderer implements InlineRenderer<EffectInlineData> {

    public static EffectInlineRenderer INSTANCE = new EffectInlineRenderer();

    @Override
    public Identifier getId() {
        return PotionIconsMod.id("effect");
    }

    @Override
    public int render(EffectInlineData data, DrawContext context, int index, Style style, int codepoint, TextRenderingContext trContext) {
        var effect = new StatusEffectInstance(data.getEffect());
        var effectSprite = MinecraftClient.getInstance().getStatusEffectSpriteManager().getSprite(effect.getEffectType());
        Spritelike effectSpritelike = SpritelikeUtils.spritelikeFromSprite(effectSprite);
        SpritelikeRenderers.getRenderer(effectSpritelike).drawSpriteWithLight(effectSpritelike, context, 0, 0,
            1, 8, 8, trContext.light(), 0xFF_FFFFFF);
        return 8;
    }

    @Override
    public int charWidth(EffectInlineData data, Style style, int codepoint) {
        return 8;
    }

    @Override
    public GlowHandling getGlowPreference(EffectInlineData forData){
        return new GlowHandling.Full(forData.getEffect().getIdAsString().replace(':', '-'));
    }
}
