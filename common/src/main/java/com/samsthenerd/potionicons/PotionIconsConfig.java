package com.samsthenerd.potionicons;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.EnumHandler;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.EnumHandler.EnumDisplayOption;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.Tooltip;

@Config(name=PotionIconsMod.MOD_ID)
public class PotionIconsConfig implements ConfigData {

    public enum PIRenderMode {
        NONE,
        TRY_BOTTOM_RIGHT,
        TOP_LEFT,
        FULL;
    }

    @Tooltip(count=5)
    @EnumHandler(option= EnumDisplayOption.BUTTON)
    public PIRenderMode normalMode = PIRenderMode.TOP_LEFT;

    @Tooltip(count=5)
    @EnumHandler(option= EnumDisplayOption.BUTTON)
    public PIRenderMode shiftMode = PIRenderMode.TOP_LEFT;

    @Tooltip()
    public boolean normalFront = true;
    @Tooltip()
    public boolean shiftFront = true;

    public boolean showInTooltip = true;


}
