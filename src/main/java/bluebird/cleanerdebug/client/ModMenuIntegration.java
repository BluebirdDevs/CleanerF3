package bluebird.cleanerdebug.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Util;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {

    public static final boolean CLOTH_CONFIG_LOADED = FabricLoader.getInstance().isModLoaded("cloth-config2");

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory()
    {
        if (CLOTH_CONFIG_LOADED) {
            return parent -> ClothConfigScreen.create(parent, ConfigManager.INSTANCE);
        } else return parent -> new ConfirmLinkScreen( (open) -> {
            if (open) {
                Util.getPlatform().openUri("https://modrinth.com/mod/cloth-config");
            }
            Minecraft.getInstance().setScreen(parent);
        }, Component.translatable("cleanerf3.cloth_config_not_installed").withStyle(ChatFormatting.RED, ChatFormatting.BOLD, ChatFormatting.UNDERLINE), "https://modrinth.com/mod/cloth-config", true);
    }
}
