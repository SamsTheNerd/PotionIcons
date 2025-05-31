package com.samsthenerd.potionicons;

import com.mojang.serialization.Codec;
import com.samsthenerd.inline.Inline;
import com.samsthenerd.inline.api.data.ModIconData;
import com.samsthenerd.inline.tooltips.CustomTooltipManager.CustomTooltipProvider;
import com.samsthenerd.inline.tooltips.data.SpriteTooltipData;
import com.samsthenerd.inline.tooltips.providers.ModDataTTProvider;
import com.samsthenerd.inline.utils.Spritelike;
import com.samsthenerd.inline.utils.SpritelikeUtils;
import com.samsthenerd.inline.xplat.IModMeta;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipData;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;

// this is probably not the most server safe tooltip
public class EffectDataTTProvider implements CustomTooltipProvider<RegistryEntry<StatusEffect>> {

    public static final EffectDataTTProvider INSTANCE = new EffectDataTTProvider();

    @Override
    public Identifier getId(){
        return PotionIconsMod.id("effectdata");
    }

    // IDs for effect descriptions from Cassian's stuff or others
    public static List<UnaryOperator<String>> EFFECT_DESCRIBERS = List.of(
        (id) -> id + ".description",
        (id) -> id + ".desc",
        (id) -> "description." + id
    );

    @Override
    @NotNull
    public List<Text> getTooltipText(RegistryEntry<StatusEffect> effectEntry){
        var effect = effectEntry.value();
        List<Text> effectInfo = new ArrayList<>();

        MutableText title = Text.translatable(effect.getTranslationKey()).setStyle(Style.EMPTY.withBold(true));
        // look for a description key
        String effectLangKey = null;
        for(UnaryOperator<String> descrr : EFFECT_DESCRIBERS){
            String effKey = descrr.apply(effect.getTranslationKey());
            if(I18n.hasTranslation(effKey)){
                effectLangKey = effKey;
                break;
            }
        }
        effectInfo.add(title);
        if(effectLangKey != null){
            MutableText description = Text.translatable(effectLangKey).setStyle(Style.EMPTY.withColor(Formatting.GRAY));
            effectInfo.add(description);
        }
        return effectInfo;
    }

    @Override
    @NotNull
    public Optional<TooltipData> getTooltipData(RegistryEntry<StatusEffect> effectEntry){
        var effect = effectEntry.value();
        var effectSprite = MinecraftClient.getInstance().getStatusEffectSpriteManager().getSprite(effectEntry.value());
        Spritelike effectSpritelike = SpritelikeUtils.spritelikeFromSprite(effectSprite);
        return Optional.of(new SpriteTooltipData(effectSpritelike, (w, h) -> 32));
    }

    @Override
    @NotNull
    public Codec<RegistryEntry<StatusEffect>> getCodec(){
        return Registries.STATUS_EFFECT.createEntryCodec();
    }
}
