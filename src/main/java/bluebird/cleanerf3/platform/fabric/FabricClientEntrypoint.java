package bluebird.cleanerf3.platform.fabric;

//? fabric {

import bluebird.cleanerf3.Main;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ClientModInitializer;

@Entrypoint("client")
public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		Main.onInitializeClient();
	}

}
//?}
