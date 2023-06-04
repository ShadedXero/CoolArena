package me.none030.coolarena.config;

import me.none030.coolarena.arena.Arena;
import me.none030.coolarena.arena.ArenaManager;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ArenaConfig extends Config {

    public ArenaConfig(ConfigManager configManager) {
        super("arena.yml", configManager);
    }

    @Override
    public void loadConfig() {
        File file = saveConfig();
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        Arena arena = loadArena(config);
        if (arena == null) {
            return;
        }
        getConfigManager().getManager().setArenaManager(new ArenaManager(arena));
    }

    private Arena loadArena(FileConfiguration config) {
        Location arena = loadLocation(config.getConfigurationSection("arena"));
        if (arena == null) {
            return null;
        }
        Location lobby = loadLocation(config.getConfigurationSection("lobby"));
        if (lobby == null) {
            return null;
        }
        return new Arena(arena, lobby);
    }
}
