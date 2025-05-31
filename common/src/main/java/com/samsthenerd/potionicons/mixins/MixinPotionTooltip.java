package com.samsthenerd.potionicons.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.samsthenerd.potionicons.EffectInlineData;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.PotionUtil;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

import java.util.List;

@Mixin(PotionUtil.class)
public class MixinPotionTooltip {
    @WrapOperation(
        method="buildTooltip(Ljava/util/List;Ljava/util/List;F)V",
        slice = @Slice(
            from=@At(value="INVOKE", target="Lnet/minecraft/entity/effect/StatusEffectCategory;getFormatting()Lnet/minecraft/util/Formatting;"),
            to=@At(value="INVOKE", target="Lnet/minecraft/entity/effect/StatusEffectCategory;getFormatting()Lnet/minecraft/util/Formatting;",
                shift = At.Shift.BY, by = 2)
        ),
        at = @At(value="INVOKE", target="Ljava/util/List;add(Ljava/lang/Object;)Z"),
        remap = true
    )
    private static boolean potionIcons$addIconToPotionTooltip(List ttConsumer, Object t, Operation<Boolean> original, @Local StatusEffectInstance effect){
        var effData = new EffectInlineData(Registries.STATUS_EFFECT.getEntry(effect.getEffectType()));
        return original.call(ttConsumer, effData.addIconToPotionTooltip((Text) t));
    }
}
