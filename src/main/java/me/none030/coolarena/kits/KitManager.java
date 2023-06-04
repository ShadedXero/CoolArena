package me.none030.coolarena.kits;

import me.none030.coolarena.config.KitConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class KitManager {

    private final KitConfig kitConfig;
    private final HashMap<String, Kit> kitById;

    public KitManager(KitConfig kitConfig) {
        this.kitConfig = kitConfig;
        this.kitById = new HashMap<>();
    }

    public void addKit(Kit kit) {
        kitById.put(kit.getId(), kit);
        kitConfig.addKit(kit);
    }

    public void removeKit(Kit kit) {
        kitById.remove(kit.getId());
        kitConfig.removeKit(kit.getId());
    }

    public Kit getRandomKit() {
        List<Kit> kits = getKits();
        if (kits.size() == 0) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(0, kits.size());
        return kits.get(index);
    }

    public List<Kit> getKits() {
        return new ArrayList<>(kitById.values());
    }

    public KitConfig getKitConfig() {
        return kitConfig;
    }

    public HashMap<String, Kit> getKitById() {
        return kitById;
    }
}
