package bluebird.cleanerf3;

import bluebird.cleanerf3.config.ConfigManager;
import bluebird.cleanerf3.platform.Platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
import bluebird.cleanerf3.platform.fabric.FabricPlatform;
//?} neoforge {
/*import bluebird.cleanerf3.platform.neoforge.NeoforgePlatform;
 *///?} forge {
/*import bluebird.cleanerf3.platform.forge.ForgePlatform;
*///?}

public class Main {

	public static final String MOD_ID = "cleanerf3";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Platform PLATFORM = createPlatformInstance();

	public static void onInitialize() {
	}

	public static void onInitializeClient() {
		ConfigManager.load();
	}

	public static Platform platform() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		//? fabric {
		return new FabricPlatform();
		//?} neoforge {
		/*return new NeoforgePlatform();
		 *///?} forge {
		/*return new ForgePlatform();
		*///?}
	}
}
