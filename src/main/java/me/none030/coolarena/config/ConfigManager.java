package me.none030.coolarena.config;

import me.none030.coolarena.manager.CoolArenaManager;

public class ConfigManager {

    private final CoolArenaManager manager;
    private final ArenaConfig arenaConfig;
    private final KitConfig kitConfig;

    public ConfigManager(CoolArenaManager manager) {
        this.manager = manager;
        this.arenaConfig = new ArenaConfig(this);
        this.kitConfig = new KitConfig(this);
    }

    public CoolArenaManager getManager() {
        return manager;
    }

    public ArenaConfig getArenaConfig() {
        return arenaConfig;
    }

    public KitConfig getKitConfig() {
        return kitConfig;
    }
}
