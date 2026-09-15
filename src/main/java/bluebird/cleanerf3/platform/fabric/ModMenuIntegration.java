package bluebird.cleanerf3.platform.fabric;

//? fabric {

import bluebird.cleanerf3.config.ClothConfigScreen;
import bluebird.cleanerf3.config.ConfigManager;
//? if >= 26.3 {
import com.mojang.blaze3d.Blaze3D;
import java.net.URI;
//?} else {
/*import net.minecraft.util.Util;
*///?}
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.network.chat.Component;


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
                //? if >= 26.3 {
                Blaze3D.openUri(URI.create("https://modrinth.com/mod/cloth-config"));
                //?} else {
                /*Util.getPlatform().openUri("https://modrinth.com/mod/cloth-config");
                *///?}
            }
            Minecraft.getInstance()/*? if >= 26.2 {*/.gui/*?}*/.setScreen(parent);
        }, Component.translatable("cleanerf3.cloth_config_not_installed").withStyle(ChatFormatting.RED, ChatFormatting.BOLD, ChatFormatting.UNDERLINE), /*? if >= 26.2 {*/URI.create(/*?}*/"https://modrinth.com/mod/cloth-config"/*? if >= 26.2 {*/)/*?}*/, true);
    }
}
//?}
