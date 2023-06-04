package me.none030.coolarena;

import me.none030.coolarena.manager.CoolArenaManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CoolArena extends JavaPlugin {

    private static CoolArena Instance;
    private CoolArenaManager manager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Instance = this;
        manager = new CoolArenaManager();
    }

    public static CoolArena getInstance() {
        return Instance;
    }

    public CoolArenaManager getManager() {
        return manager;
    }
}
