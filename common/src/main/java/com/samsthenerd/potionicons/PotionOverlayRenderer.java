package com.samsthenerd.potionicons;

import com.samsthenerd.inline.api.client.extrahooks.ItemOverlayRenderer;
import com.samsthenerd.inline.utils.Spritelike;
import com.samsthenerd.inline.utils.SpritelikeRenderers;
import com.samsthenerd.inline.utils.SpritelikeUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;

// TODO: add config
public class PotionOverlayRenderer implements ItemOverlayRenderer {

    public static PotionOverlayRenderer INSTANCE = new PotionOverlayRenderer();

    @Override
    public void render(ItemStack stack, DrawContext drawContext) {
        var potComp = stack.get(DataComponentTypes.POTION_CONTENTS);
        if(potComp == null) return;
        var effects = potComp.getEffects();
        // TODO: make this cycle?
        int i = 0;
        for(var effect : effects){
            var effectSprite = MinecraftClient.getInstance().getStatusEffectSpriteManager().getSprite(effect.getEffectType());
            Spritelike effectSpritelike = SpritelikeUtils.spritelikeFromSprite(effectSprite);
            boolean tl = stack.getMaxCount() > 1;
            SpritelikeRenderers.getRenderer(effectSpritelike).drawSprite(effectSpritelike, drawContext, tl ? 2*i : 7-2*i, tl ? 2*i : 7-2*i,
                100+i, 9, 9);
            i++;
        }
    }

    @Override
    public boolean isActive(ItemStack stack) {
        return stack.get(DataComponentTypes.POTION_CONTENTS) != null;
    }
}
