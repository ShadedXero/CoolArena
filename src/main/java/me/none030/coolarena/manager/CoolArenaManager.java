package me.none030.coolarena.manager;

import me.none030.coolarena.CoolArena;
import me.none030.coolarena.arena.ArenaManager;
import me.none030.coolarena.config.ConfigManager;
import me.none030.coolarena.kits.KitManager;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

public class CoolArenaManager {

    private final CoolArena plugin = CoolArena.getInstance();
    private ArenaManager arenaManager;
    private KitManager kitManager;
    private ConfigManager configManager;

    public CoolArenaManager() {
        this.configManager = new ConfigManager(this);
    }

    public void reload() {
        HandlerList.unregisterAll(plugin);
        Bukkit.getScheduler().cancelTasks(plugin);
        setConfigManager(new ConfigManager(this));
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public void setArenaManager(ArenaManager arenaManager) {
        this.arenaManager = arenaManager;
    }

    public KitManager getKitManager() {
        return kitManager;
    }

    public void setKitManager(KitManager kitManager) {
        this.kitManager = kitManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public void setConfigManager(ConfigManager configManager) {
        this.configManager = configManager;
    }
}
