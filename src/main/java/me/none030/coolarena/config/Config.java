package me.none030.coolarena.config;

import me.none030.coolarena.CoolArena;
import me.none030.coolarena.utils.MessageUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public abstract class Config {

    private final CoolArena plugin = CoolArena.getInstance();
    private final String fileName;
    private final ConfigManager configManager;

    public Config(String fileName, ConfigManager configManager) {
        this.fileName = fileName;
        this.configManager = configManager;
        loadConfig();
    }

    public abstract void loadConfig();

    public Location loadLocation(ConfigurationSection section) {
        if (section == null) {
            return null;
        }
        String worldName = section.getString("world");
        if (worldName == null) {
            return null;
        }
        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            return null;
        }
        double x = section.getDouble("x");
        double y = section.getDouble("y");
        double z = section.getDouble("z");
        float yaw = section.getInt("yaw");
        float pitch = section.getInt("pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

    public String serializeInventory(Inventory inventory) {
        if (inventory == null) {
            return null;
        }
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            for (int i = 0; i < inventory.getSize(); i++) {
                dataOutput.writeObject(inventory.getItem(i));
            }
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return null;
    }

    public Inventory deserializeInventory(String data) {
        if (data == null) {
            return null;
        }
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            Inventory inventory = Bukkit.createInventory(null, InventoryType.PLAYER);
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, (ItemStack) dataInput.readObject());
            }
            dataInput.close();
            return inventory;
        } catch (ClassNotFoundException | IOException exp) {
            exp.printStackTrace();
        }
        return null;
    }

    public File saveConfig() {
        File file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            plugin.saveResource(fileName, true);
        }
        return file;
    }

    public CoolArena getPlugin() {
        return plugin;
    }

    public String getFileName() {
        return fileName;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
