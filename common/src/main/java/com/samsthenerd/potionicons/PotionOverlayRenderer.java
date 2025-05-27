package com.samsthenerd.potionicons;

import com.samsthenerd.inline.api.client.extrahooks.ItemOverlayRenderer;
import com.samsthenerd.inline.utils.SpriteUVLens;
import com.samsthenerd.inline.utils.Spritelike;
import com.samsthenerd.inline.utils.SpritelikeRenderers;
import com.samsthenerd.inline.utils.SpritelikeUtils;
import com.samsthenerd.potionicons.PotionIconsConfig.PIRenderMode;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

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
        List<StatusEffectInstance> effectsList = new ArrayList<>();
        for(var effect : effects) {
            effectsList.add(effect);
        }
        double cycleSpeed = isShift ? config.cycleSpeedShift : config.cycleSpeedNormal;
        long cycleSpeedMS = Math.round(cycleSpeed * 1000);
        long startIdx = 0;
        if(cycleSpeedMS != 0){
            startIdx = (SpriteUVLens.getSysTime() / cycleSpeedMS) % effectsList.size();
        }
        for(int j = 0; j < startIdx; j++){
            effectsList.add(effectsList.removeFirst());
        }
        for(var effect : effectsList) {
            var effectSprite = MinecraftClient.getInstance().getStatusEffectSpriteManager().getSprite(effect.getEffectType());
            Spritelike effectSpritelike = SpritelikeUtils.spritelikeFromSprite(effectSprite);
            if(mode == PIRenderMode.FULL){
                SpritelikeRenderers.getRenderer(effectSpritelike).drawSprite(effectSpritelike, drawContext, -1, -1,
                    effectsList.size() - i, 18, 18);
            } else if(mode == PIRenderMode.TOP_LEFT || (mode == PIRenderMode.TRY_BOTTOM_RIGHT && stack.getMaxCount() > 1)){
                SpritelikeRenderers.getRenderer(effectSpritelike).drawSprite(effectSpritelike, drawContext, 2*i, 2*i,
                    effectsList.size() - i, 9, 9);
            } else if(mode == PIRenderMode.TRY_BOTTOM_RIGHT){
                SpritelikeRenderers.getRenderer(effectSpritelike).drawSprite(effectSpritelike, drawContext, 7-2*i, 7-2*i,
                    effectsList.size() - i, 9, 9);
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
