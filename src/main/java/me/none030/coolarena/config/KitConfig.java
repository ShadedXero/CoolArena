package me.none030.coolarena.config;

import me.none030.coolarena.kits.Kit;
import me.none030.coolarena.kits.KitManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KitConfig extends Config {

    public KitConfig(ConfigManager configManager) {
        super("kits.yml", configManager);
    }

    @Override
    public void loadConfig() {
        File file = saveConfig();
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        getConfigManager().getManager().setKitManager(new KitManager(this));
        loadKits(config.getConfigurationSection("kits"));
    }

    private void loadKits(ConfigurationSection kits) {
        if (kits == null) {
            return;
        }
        for (String id : kits.getKeys(false)) {
            ConfigurationSection section = kits.getConfigurationSection(id);
            if (section == null) {
               continue;
            }
            String name = section.getString("name");
            String inventoryData = section.getString("inventory");
            if (inventoryData == null) {
                continue;
            }
            Inventory inventory = deserializeInventory(inventoryData);
            if (inventory == null) {
                continue;
            }
            Kit kit = new Kit(id, name, inventory);
            getConfigManager().getManager().getKitManager().getKits().add(kit);
            getConfigManager().getManager().getKitManager().getKitById().put(id, kit);
        }
    }

    public void addKit(Kit kit) {
        File file = saveConfig();
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection kits = config.getConfigurationSection("kits");
        if (kits == null) {
            kits = config.createSection("kits");
        }
        ConfigurationSection kitSection = kits.createSection(kit.getId());
        kitSection.set("name", kit.getName());
        kitSection.set("inventory", serializeInventory(kit.getInventory()));
        try {
            config.save(file);
        }catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    public void removeKit(String kitId) {
        File file = saveConfig();
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection kits = config.getConfigurationSection("kits");
        if (kits == null) {
            return;
        }
        kits.set(kitId, null);
    }
}
