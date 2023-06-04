package me.none030.coolarena.arena;

import me.none030.coolarena.CoolArena;

public class ArenaManager {

    private final Arena arena;

    public ArenaManager(Arena arena) {
        this.arena = arena;
        CoolArena plugin = CoolArena.getInstance();
        plugin.getServer().getPluginManager().registerEvents(new ArenaListener(this), plugin);
    }

    public Arena getArena() {
        return arena;
    }
}
