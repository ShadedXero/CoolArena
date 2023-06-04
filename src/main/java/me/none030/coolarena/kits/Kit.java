package me.none030.coolarena.kits;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Kit {

    private final String id;
    private final String name;
    private final Inventory inventory;

    public Kit(String id, String name, Inventory inventory) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
    }

    public void give(Player player) {
        clearInventory(player);
        clearPotionEffects(player);
        for (int i = 0; i < inventory.getSize(); i++) {
            player.getInventory().setItem(i, inventory.getItem(i));
        }
    }

    private void clearInventory(Player player) {
        player.getInventory().clear();
    }

    private void clearPotionEffects(Player player) {
        player.getActivePotionEffects().clear();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
