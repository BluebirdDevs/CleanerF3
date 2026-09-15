package bluebird.cleanerf3.platform.neoforge;

//? neoforge {

/*import bluebird.cleanerf3.Main;
import bluebird.cleanerf3.config.ClothConfigScreen;
import bluebird.cleanerf3.config.ConfigManager;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Util;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = Main.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(value = Dist.CLIENT)
public class NeoforgeModConfig {
	public NeoforgeModConfig(ModContainer container) {
		container.registerExtensionPoint(
				IConfigScreenFactory.class,
				ModList.get().isLoaded("cloth_config") ? (_container, parent) -> ClothConfigScreen.create(parent, ConfigManager.INSTANCE) : (_container, parent) -> new ConfirmLinkScreen( (open) -> {
					if (open) {
						Util.getPlatform().openUri("https://modrinth.com/mod/cloth-config");
					}
					Minecraft.getInstance()/^? if >= 26.2 {^/.gui/^?}^/.setScreen(parent);
				}, Component.translatable("cleanerf3.cloth_config_not_installed").withStyle(ChatFormatting.RED, ChatFormatting.BOLD, ChatFormatting.UNDERLINE), "https://modrinth.com/mod/cloth-config", true)
		);
	}
}
*///?}
