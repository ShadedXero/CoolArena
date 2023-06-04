package me.none030.coolarena;

import org.bukkit.plugin.java.JavaPlugin;

public final class CoolArena extends JavaPlugin {

    private static CoolArena Instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Instance = this;
    }

    public static CoolArena getInstance() {
        return Instance;
    }
}
