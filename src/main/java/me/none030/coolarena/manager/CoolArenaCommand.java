package me.none030.coolarena.manager;

import me.none030.coolarena.kits.Kit;
import me.none030.coolarena.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CoolArenaCommand implements TabExecutor {

    private final CoolArenaManager coolArenaManager;

    public CoolArenaCommand(CoolArenaManager coolArenaManager) {
        this.coolArenaManager = coolArenaManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            if (!player.hasPermission("coolarena.join")) {
                player.sendMessage(MessageUtils.color("&cYou don't have permission to use this"));
                return false;
            }
            coolArenaManager.getArenaManager().getArena().teleportArena(player);
            Kit kit = coolArenaManager.getKitManager().getRandomKit();
            kit.give(player);
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (!player.hasPermission("coolarena.reload")) {
                player.sendMessage(MessageUtils.color("&cYou don't have permission to use this"));
                return false;
            }
            coolArenaManager.reload();
            player.sendMessage(MessageUtils.color("&aCoolArena Reloaded"));
            return true;
        }
        if (!player.hasPermission("coolarena.join")) {
            player.sendMessage(MessageUtils.color("&cYou don't have permission to use this"));
            return false;
        }
        List<String> kitIds = new ArrayList<>(coolArenaManager.getKitManager().getKitById().keySet());
        for (String kitId : kitIds) {
            if (kitId.equalsIgnoreCase(args[0])) {
                continue;
            }
            Kit kit = coolArenaManager.getKitManager().getKitById().get(kitId);
            kit.give(player);
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(coolArenaManager.getKitManager().getKitById().keySet());
        }
        return null;
    }
}
