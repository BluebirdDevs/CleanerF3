package bluebird.cleanerdebug.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CleanerDebugClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("cleanerf3");

    @Override
    public void onInitializeClient() {
        ModConfig.init();
    }
}
