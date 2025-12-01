package bluebird.notargetedblocktags.client;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "ntbt")
public class ModConfig implements ConfigData {

    @ConfigEntry.Gui.Excluded
    public static ModConfig INSTANCE;

    public static void init()
    {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        INSTANCE = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }

    @ConfigEntry.Gui.Tooltip()
    @Comment("Enables this mod")
    public boolean enabled = true;
}