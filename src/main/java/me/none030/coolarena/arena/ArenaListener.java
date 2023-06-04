package me.none030.coolarena.arena;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class ArenaListener implements Listener {

    private final ArenaManager arenaManager;

    public ArenaListener(ArenaManager arenaManager) {
        this.arenaManager = arenaManager;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getPlayer();
        if (!arenaManager.getArena().isBattlefield(player.getWorld())) {
            return;
        }
        arenaManager.getArena().reset(player);
        player.teleport(arenaManager.getArena().getLobby().getLocation());
    }
}
