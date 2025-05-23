package com.samsthenerd.potionicons;

import com.samsthenerd.inline.Inline;
import com.samsthenerd.inline.api.client.InlineClientAPI;
import com.samsthenerd.inline.api.client.extrahooks.ItemOverlayRenderer;
import com.samsthenerd.inline.api.data.ItemInlineData;
import com.samsthenerd.inline.api.matching.InlineMatch.DataMatch;
import com.samsthenerd.inline.api.matching.MatcherInfo;
import com.samsthenerd.inline.api.matching.RegexMatcher;
import com.samsthenerd.inline.api.matching.RegexMatcher.Standard;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.HoverEvent.ItemStackContent;
import net.minecraft.text.Style;
import net.minecraft.util.Identifier;

public class PotionIconsModClient {
    public static void init(){
        ItemOverlayRenderer.addRenderer(PotionOverlayRenderer.INSTANCE);

        InlineClientAPI.INSTANCE.addRenderer(EffectInlineRenderer.INSTANCE);

        Identifier effectMatcherID = PotionIconsMod.id( "effect");
        InlineClientAPI.INSTANCE.addMatcher(new RegexMatcher.Standard("effect", Standard.IDENTIFIER_REGEX_INSENSITIVE, effectMatcherID,
            (String effectId) ->{
                Identifier effActualId = Identifier.of(effectId.toLowerCase());
                var optEff = Registries.STATUS_EFFECT.getEntry(effActualId);
                if(optEff.isEmpty()) return null;
                var effect = optEff.get();
                var effData = new EffectInlineData(effect);
                return new DataMatch(effData, effData.getExtraStyle());
            }, MatcherInfo.fromId(effectMatcherID)));
    }
}
