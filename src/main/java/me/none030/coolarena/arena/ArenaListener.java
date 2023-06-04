package me.none030.coolarena.arena;

import me.none030.coolarena.CoolArena;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class ArenaListener implements Listener {

    private final CoolArena plugin = CoolArena.getInstance();
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
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> player.spigot().respawn(), 1L);
        arenaManager.getArena().reset(player);
        player.teleport(arenaManager.getArena().getLobby().getLocation());
    }
}
