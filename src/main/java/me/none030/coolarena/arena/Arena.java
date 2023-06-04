package me.none030.coolarena.arena;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class Arena {

    private final Location arena;
    private final Location lobby;

    public Arena(Location arena, Location lobby) {
        this.arena = arena;
        this.lobby = lobby;
    }

    public void reset(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().clear();
        removePotionEffects(player);
        player.setHealth(20);
        player.setAbsorptionAmount(0);
    }

    private void removePotionEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }

    public void teleportArena(Player player) {
        player.teleport(arena);
    }

    public void teleportLobby(Player player) {
        player.teleport(lobby);
    }

    public boolean isArena(World arena) {
        return this.arena.getWorld().equals(arena);
    }

    public boolean isLobby(World lobby) {
        return this.lobby.getWorld().equals(lobby);
    }

    public Location getArena() {
        return arena;
    }

    public Location getLobby() {
        return lobby;
    }
}
