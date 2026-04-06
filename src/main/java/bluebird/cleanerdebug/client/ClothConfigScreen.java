package bluebird.cleanerdebug.client;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ClothConfigScreen {
    public static Screen create(Screen parent, ConfigValues config) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("config.cleanerdebug.title"))
                .setSavingRunnable(ConfigManager::save);
        ConfigEntryBuilder eb = builder.entryBuilder();
        ConfigCategory general = builder.getOrCreateCategory(Component.translatable("category.cleanerdebug.general"));

        general.addEntry(eb.startBooleanToggle(Component.translatable("option.cleanerdebug.hide_debug_hints"), config.hide_debug_hints)
                .setDefaultValue(true)
                .setSaveConsumer(val -> config.hide_debug_hints = val)
                .setTooltip(Component.translatable("option.cleanerdebug.hide_debug_hints.tooltip"))
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("option.cleanerdebug.hide_targeted_blockstate_tags"), config.hide_targeted_blockstate_tags)
                .setDefaultValue(true)
                .setSaveConsumer(val -> config.hide_targeted_blockstate_tags = val)
                .setTooltip(Component.translatable("option.cleanerdebug.hide_targeted_blockstate_tags.tooltip"))
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("option.cleanerdebug.hide_targeted_block_tags"), config.hide_targeted_block_tags)
                .setDefaultValue(false)
                .setSaveConsumer(val -> config.hide_targeted_block_tags = val)
                .setTooltip(Component.translatable("option.cleanerdebug.hide_targeted_block_tags.tooltip"))
                .build());

        general.addEntry(eb.startIntField(Component.translatable("option.cleanerdebug.targeted_block_max_distance"), config.targeted_block_max_distance)
                .setDefaultValue(20)
                .setMax(256)
                .setMin(1)
                .setSaveConsumer(val -> config.targeted_block_max_distance = val)
                .setTooltip(Component.translatable("option.cleanerdebug.targeted_block_max_distance.tooltip"))
                .build());

        var posSub = eb.startSubCategory(Component.translatable("category.cleanerdebug.position"));

        var simplifiedToggle = eb.startBooleanToggle(Component.translatable("option.cleanerdebug.simplified_pos"), config.pos.simplified_pos)
                .setDefaultValue(false)
                .setSaveConsumer(val -> config.pos.simplified_pos = val)
                .setTooltip(Component.translatable("option.cleanerdebug.simplified_pos.tooltip"))
                .build();
        posSub.add(simplifiedToggle);

        posSub.add(eb.startStrField(Component.translatable("option.cleanerdebug.pos_name"), config.pos.pos_name)
                .setDefaultValue("XYZ")
                .setSaveConsumer(val -> config.pos.pos_name = val)
                .setTooltip(Component.translatable("option.cleanerdebug.pos_name.tooltip"))
                .build());

        posSub.add(eb.startBooleanToggle(Component.translatable("option.cleanerdebug.simplified_direction"), config.pos.simplified_direction)
                .setDefaultValue(true)
                .setSaveConsumer(val -> config.pos.simplified_direction = val)
                .setTooltip(Component.translatable("option.cleanerdebug.simplified_direction.tooltip"))
                .build());

        posSub.add(eb.startBooleanToggle(Component.translatable("option.cleanerdebug.hide_chunk_position"), config.pos.hide_chunk_position)
                .setDefaultValue(false)
                .setSaveConsumer(val -> config.pos.hide_chunk_position = val)
                .setTooltip(Component.translatable("option.cleanerdebug.hide_chunk_position.tooltip"))
                .build());

        posSub.add(eb.startBooleanToggle(Component.translatable("option.cleanerdebug.hide_facing"), config.pos.hide_facing)
                .setDefaultValue(false)
                .setSaveConsumer(val -> config.pos.hide_facing = val)
                .setTooltip(Component.translatable("option.cleanerdebug.hide_facing.tooltip"))
                .build());

        posSub.add(eb.startBooleanToggle(Component.translatable("option.cleanerdebug.hide_dimension"), config.pos.hide_dimension)
                .setDefaultValue(false)
                .setSaveConsumer(val -> config.pos.hide_dimension = val)
                .setTooltip(Component.translatable("option.cleanerdebug.hide_dimension.tooltip"))
                .build());

        general.addEntry(posSub.build());

        return builder.build();
    }
}
