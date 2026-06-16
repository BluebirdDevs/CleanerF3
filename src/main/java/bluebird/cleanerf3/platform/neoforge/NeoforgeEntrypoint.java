package bluebird.cleanerf3.platform.neoforge;

//? neoforge {

import bluebird.cleanerf3.Main;
import net.neoforged.fml.common.Mod;

@Mod(Main.MOD_ID)
public class NeoforgeEntrypoint {

	public NeoforgeEntrypoint() {
		Main.onInitialize();
	}
}
//?}
