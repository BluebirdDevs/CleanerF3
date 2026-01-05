package bluebird.cleanerdebug.client;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "cleanerdebug")
public class ModConfig implements ConfigData {

    @ConfigEntry.Gui.Excluded
    public static ModConfig INSTANCE;

    public static void init()
    {
        AutoConfig.register(ModConfig.class, JanksonConfigSerializer::new);
        INSTANCE = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
    }

    @ConfigEntry.Gui.Tooltip()
    @Comment("Disables the targeted block tags")
    public boolean targetedBlockTags = false;

    @ConfigEntry.Gui.Tooltip()
    @Comment("Disables the targeted block state")
    public boolean targetedBlockState = true;

    @ConfigEntry.Gui.Tooltip()
    @Comment("Disables the debug hints")
    public boolean debugHints = false;

    @ConfigEntry.Gui.Tooltip()
    @Comment("Only pos and facing")
    public boolean simplified_pos = false;

    @ConfigEntry.Gui.Tooltip()
    @Comment("Position name")
    public String pos_name = "XYZ";

    @ConfigEntry.Gui.Tooltip()
    @Comment("E.x. changes \"facing negative x\" to \"-x\"")
    public boolean simplified_direction = true;

    @ConfigEntry.Gui.Tooltip()
    @Comment("Hide chunk position")
    public boolean hide_chunk_position = false;

    @ConfigEntry.Gui.Tooltip()
    @Comment("Hide facing")
    public boolean hide_facing = false;
}