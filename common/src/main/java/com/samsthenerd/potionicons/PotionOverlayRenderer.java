package com.samsthenerd.potionicons;

import com.samsthenerd.inline.api.client.extrahooks.ItemOverlayRenderer;
import com.samsthenerd.inline.utils.Spritelike;
import com.samsthenerd.inline.utils.SpritelikeRenderers;
import com.samsthenerd.inline.utils.SpritelikeUtils;
import com.samsthenerd.potionicons.PotionIconsConfig.PIRenderMode;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
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
        boolean isShift = Screen.hasShiftDown();
        var config = PotionIconsModClient.getConfig();
        PIRenderMode mode = isShift ? config.shiftMode : config.normalMode;
        if(mode == PIRenderMode.NONE) return;
        // TODO: make this cycle?
        int i = 0;
        for(var effect : effects){
            var effectSprite = MinecraftClient.getInstance().getStatusEffectSpriteManager().getSprite(effect.getEffectType());
            Spritelike effectSpritelike = SpritelikeUtils.spritelikeFromSprite(effectSprite);
            if(mode == PIRenderMode.FULL){
                SpritelikeRenderers.getRenderer(effectSpritelike).drawSprite(effectSpritelike, drawContext, 0, 0,
                    100+i, 16, 16);
            } else if(mode == PIRenderMode.TOP_LEFT || (mode == PIRenderMode.TRY_BOTTOM_RIGHT && stack.getMaxCount() > 1)){
                SpritelikeRenderers.getRenderer(effectSpritelike).drawSprite(effectSpritelike, drawContext, 2*i, 2*i,
                    100+i, 9, 9);
            } else if(mode == PIRenderMode.TRY_BOTTOM_RIGHT){
                SpritelikeRenderers.getRenderer(effectSpritelike).drawSprite(effectSpritelike, drawContext, 7-2*i, 7-2*i,
                    100+i, 9, 9);
            }
            i++;
        }
    }

    @Override
    public boolean renderInFront(ItemStack stack){
        boolean isShift = Screen.hasShiftDown();
        var config = PotionIconsModClient.getConfig();
        return isShift ? config.shiftFront : config.normalFront;
    }

    @Override
    public boolean isActive(ItemStack stack) {
        return stack.get(DataComponentTypes.POTION_CONTENTS) != null;
    }
}
