package bluebird.notargetedblocktags.client;

import net.fabricmc.api.ClientModInitializer;
import bluebird.notargetedblocktags.client.ModConfig;

public class NotargetedblocktagsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModConfig.init();
    }
}
