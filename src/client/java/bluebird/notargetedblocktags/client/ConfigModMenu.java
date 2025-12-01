package bluebird.notargetedblocktags.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import bluebird.notargetedblocktags.client.ModConfig;

@Environment(EnvType.CLIENT)
public class ConfigModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory()
    {
        return parent -> AutoConfig.getConfigScreen(ModConfig.class, parent).get();
    }
}
