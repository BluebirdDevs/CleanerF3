package bluebird.cleanerf3.config;

import bluebird.cleanerf3.Main;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.nio.file.Path;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {
    private static final Path CONFIG_PATH = Main.platform().getConfigDir();
    private static final File CONFIG_FILE = new File(CONFIG_PATH.toString(), "cleanerf3.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static ConfigValues INSTANCE = new ConfigValues();

    public static void load() {
        if (!CONFIG_FILE.exists()) {
            save();
            return;
        }

        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            INSTANCE = GSON.fromJson(reader, ConfigValues.class);
        } catch (IOException e) {
            Main.LOGGER.error("Could not load config file! Using defaults.", e);
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(INSTANCE, writer);
        } catch (IOException e) {
			Main.LOGGER.error("Could not save config file!", e);
        }
    }
}
