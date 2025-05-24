package com.samsthenerd.potionicons;

import com.samsthenerd.inline.api.InlineAPI;
import com.samsthenerd.inline.api.client.InlineClientAPI;
import com.samsthenerd.inline.api.client.extrahooks.ItemOverlayRenderer;
import com.samsthenerd.inline.api.data.ItemInlineData;
import com.samsthenerd.inline.api.matching.InlineMatch.DataMatch;
import com.samsthenerd.inline.api.matching.MatcherInfo;
import com.samsthenerd.inline.api.matching.RegexMatcher;
import com.samsthenerd.inline.api.matching.RegexMatcher.Standard;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.registry.Registries;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.HoverEvent.ItemStackContent;
import net.minecraft.text.Style;
import net.minecraft.util.Identifier;

public class PotionIconsModClient {
    public static void init(){

        PotionIconsMod.isClient = true;

        AutoConfig.register(PotionIconsConfig.class, Toml4jConfigSerializer::new);

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

        Identifier potionMatcherID = PotionIconsMod.id( "potion");
        InlineClientAPI.INSTANCE.addMatcher(new RegexMatcher.Standard("potion", Standard.IDENTIFIER_REGEX_INSENSITIVE, potionMatcherID,
            (String potionId) ->{
                Identifier potActualId = Identifier.of(potionId.toLowerCase());
                var optPot = Registries.POTION.getEntry(potActualId);
                if(optPot.isEmpty()) return null;
                var potion = optPot.get();
                var potStack = Items.POTION.getDefaultStack();
                potStack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(potion));
                HoverEvent hover = new HoverEvent(HoverEvent.Action.SHOW_ITEM, new ItemStackContent(potStack));
                var itemData = new ItemInlineData(potStack);
                return new DataMatch(itemData, Style.EMPTY.withHoverEvent(hover));
            }, MatcherInfo.fromId(potionMatcherID)));
    }

    public static PotionIconsConfig getConfig(){
        return AutoConfig.getConfigHolder(PotionIconsConfig.class).getConfig();
    }
}
