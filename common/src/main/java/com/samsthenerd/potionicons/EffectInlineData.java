package com.samsthenerd.potionicons;

import com.mojang.serialization.Codec;
import com.samsthenerd.inline.Inline;
import com.samsthenerd.inline.api.InlineAPI;
import com.samsthenerd.inline.api.InlineData;
import com.samsthenerd.inline.tooltips.CustomTooltipManager;
import com.samsthenerd.inline.tooltips.providers.ModDataTTProvider;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EffectInlineData implements InlineData<EffectInlineData> {

    private final RegistryEntry<StatusEffect> effect;

    public EffectInlineData(RegistryEntry<StatusEffect> effect){
        this.effect = effect;
    }

    public RegistryEntry<StatusEffect> getEffect(){
        return this.effect;
    }

    @Override
    public InlineDataType<EffectInlineData> getType() {
        return EffectDataType.INSTANCE;
    }

    @Override
    public Identifier getRendererId() {
        return PotionIconsMod.id("effect");
    }

    @Override
    public Style getExtraStyle() {
        HoverEvent he = new HoverEvent(
            HoverEvent.Action.SHOW_ITEM,
            new HoverEvent.ItemStackContent(CustomTooltipManager.getForTooltip(EffectDataTTProvider.INSTANCE, effect))
        );
        return Style.EMPTY.withHoverEvent(he);
    }

    public Text addIconToPotionTooltip(Text t){
        if(!PotionIconsModClient.getConfig().showInTooltip) return t;
        MutableText effText = t.copy();
        Text iconText = this.asText(false).copy().styled(sty -> InlineAPI.INSTANCE.withSizeModifier(sty, 1.25));
        return Text.empty().append(iconText.copy()).append(" ").append(effText);
    }

    public static class EffectDataType implements InlineDataType<EffectInlineData> {
        public static EffectDataType INSTANCE = new EffectDataType();

        @Override
        public Identifier getId(){
            return PotionIconsMod.id( "effect");
        }

        @Override
        public Codec<EffectInlineData> getCodec(){
            return StatusEffect.ENTRY_CODEC.xmap(
                EffectInlineData::new, EffectInlineData::getEffect
            );
        }
    }
}
