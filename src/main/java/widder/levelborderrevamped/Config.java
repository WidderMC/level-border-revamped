package widder.levelborderrevamped;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.storage.LevelResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    public  boolean resetXPByDeath = true;
    public int globalXp = 0;
    public boolean borderEnabled = false;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static Config INSTANCE;
    private static Path file;

    public static void load(MinecraftServer server) {
        file = server.getWorldPath(LevelResource.ROOT).resolve("level-border-revamped.json");
        try {
            if (Files.exists(file)) {
                INSTANCE = GSON.fromJson(Files.readString(file), Config.class);
                return;
            }
        } catch (Exception e) {
            LevelBorderRevamped.LOGGER.warn("Configuration is corrupted, using default values.", e);
        }
        INSTANCE = new Config();
        save();
    }

    public static void save() {
        if (INSTANCE == null || file == null) return;
        try {
            Files.writeString(file, GSON.toJson(INSTANCE));
        } catch (IOException e) {
            LevelBorderRevamped.LOGGER.warn("Configuration could not be saved.", e);
        }
    }
}