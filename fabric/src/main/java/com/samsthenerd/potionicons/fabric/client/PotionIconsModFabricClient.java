package com.samsthenerd.potionicons.fabric.client;

import com.samsthenerd.potionicons.PotionIconsModClient;
import net.fabricmc.api.ClientModInitializer;

public final class PotionIconsModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        PotionIconsModClient.init();
    }
}
