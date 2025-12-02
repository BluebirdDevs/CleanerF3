package bluebird.cleanerdebug.client;

import net.fabricmc.api.ClientModInitializer;
import bluebird.cleanerdebug.client.ModConfig;

public class CleanerDebugClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModConfig.init();
    }
}
