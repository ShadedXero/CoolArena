package me.none030.coolarena.arena;

import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class Arena {

    private final ArenaLocation battlefield;
    private final ArenaLocation lobby;

    public Arena(ArenaLocation battlefield, ArenaLocation lobby) {
        this.battlefield = battlefield;
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

    public void teleportBattlefield(Player player) {
        player.teleport(battlefield.getLocation());
    }

    public void teleportLobby(Player player) {
        player.teleport(lobby.getLocation());
    }

    public boolean isBattlefield(World battlefield) {
        return this.battlefield.getWorld().equals(battlefield);
    }

    public boolean isLobby(World lobby) {
        return this.lobby.getWorld().equals(lobby);
    }

    public ArenaLocation getBattlefield0() {
        return battlefield;
    }

    public ArenaLocation getLobby() {
        return lobby;
    }
}
