package com.samsthenerd.potionicons.mixins;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.samsthenerd.inline.api.InlineAPI;
import com.samsthenerd.potionicons.EffectInlineData;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Slice;

import java.util.function.Consumer;

@Mixin(PotionContentsComponent.class)
public class MixinPotionTooltip {
    @WrapOperation(
        method="Lnet/minecraft/component/type/PotionContentsComponent;buildTooltip(Ljava/lang/Iterable;Ljava/util/function/Consumer;FF)V",
        slice = @Slice(
            from=@At(value="INVOKE", target="Lnet/minecraft/entity/effect/StatusEffectCategory;getFormatting()Lnet/minecraft/util/Formatting;"),
            to=@At(value="INVOKE", target="Lnet/minecraft/entity/effect/StatusEffectCategory;getFormatting()Lnet/minecraft/util/Formatting;",
                shift = At.Shift.BY, by = 2)
        ),
        at = @At(value="INVOKE", target="Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V")
    )
    private static void potionIcons$addIconToPotionTooltip(Consumer ttConsumer, Object t, Operation<Void> original, @Local StatusEffectInstance effect){
        var effData = new EffectInlineData(effect.getEffectType());
        original.call(ttConsumer, effData.addIconToPotionTooltip((Text) t));
    }
}
