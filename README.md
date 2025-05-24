# Potion Icons

<img alt="Potion Icon mod icon" height="128" src="https://github.com/SamsTheNerd/PotionIcons/blob/main/common/src/main/resources/assets/potionicons/icon.png?raw=true"/>

A mod that puts effect icons in more places, primarily overlaid over potion items and in text.

## Features

### Item Overlay

Any item with a potion component has its effect's icon(s) rendered on top of it in the inventory, hotbar, etc, making it easier to differentiate potions at a glance.

By default, the icon is always rendered in the top-left corner of the item slot, but this can be configured to match your preference! There are 4 modes that determine where the icon is rendered:
- NONE: Icon is not rendered.
- TRY_BOTTOM_RIGHT: Renders in the bottom right corner for non-stackable items. Top left otherwise.
- TOP_LEFT (default): Renders in the top left corner.
- FULL: Renders across the entire item slot.

These can be set separately based on whether you are holding shift or not. You can also set if the icon should render in front of or behind the item.

<center>
<img alt="Effect icons overlaid on top of various potion items" width="256" src="https://github.com/SamsTheNerd/PotionIcons/blob/main/images/itemoverlays.png?raw=true"/>
</center>

### Potion Tooltips

Effect icons are rendered in potion tooltips next to their listed effect. This can be disabled in the config.

<center>
<img alt="Tooltip for potion of fire resistance with the fire resistance effect icon in front of the effect name." width="512" src="https://github.com/SamsTheNerd/PotionIcons/blob/main/images/tooltip.png?raw=true"/>
</center>

### Typable in Text

Effect icons can be displayed in chat, signs, or anywhere else that you can type text in game. For example, `[effect:fire_resistance]` will show as the fire resistance effect icon. 

For more info, see [Inline's Page](https://github.com/SamsTheNerd/inline), this is just another matcher.

<center>
<img alt="Shows effect icons in chat along with a hover tooltip that gives a description of the effect" width="512" src="https://github.com/SamsTheNerd/PotionIcons/blob/main/images/chat.png?raw=true"/>
</center>

<center>
<img alt="Shows effect icons in chat along with a hover tooltip that gives a description of the effect" width="512" src="https://github.com/SamsTheNerd/PotionIcons/blob/main/images/witchshop.png?raw=true"/>
</center>