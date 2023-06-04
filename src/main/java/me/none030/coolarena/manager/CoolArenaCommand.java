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
            coolArenaManager.getArenaManager().getArena().teleportBattlefield(player);
            Kit kit = coolArenaManager.getKitManager().getRandomKit();
            kit.give(player);
            return true;
        }
        if (args[0].equalsIgnoreCase("add")) {
            if (!player.hasPermission("coolarena.add")) {
                player.sendMessage(MessageUtils.color("&cYou don't have permission to use this"));
                return false;
            }
            if (args.length != 2) {
                player.sendMessage(MessageUtils.color("&cUsage: /coolarena add <kit-id>"));
                return false;
            }
            String kitId = args[1];
            Kit kit = new Kit(kitId, kitId, player.getInventory());
            coolArenaManager.getKitManager().addKit(kit);
        }
        if (args[0].equalsIgnoreCase("remove")) {
            if (!player.hasPermission("coolarena.remove")) {
                player.sendMessage(MessageUtils.color("&cYou don't have permission to use this"));
                return false;
            }
            if (args.length != 2) {
                player.sendMessage(MessageUtils.color("&cUsage: /coolarena remove <kit-id>"));
                return false;
            }
            String kitId = args[1];
            coolArenaManager.getKitManager().getKitConfig().removeKit(kitId);
            player.sendMessage(MessageUtils.color("&aKit Removed"));
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
            coolArenaManager.getArenaManager().getArena().teleportBattlefield(player);
            Kit kit = coolArenaManager.getKitManager().getKitById().get(kitId);
            kit.give(player);
            break;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> arguments = new ArrayList<>(coolArenaManager.getKitManager().getKitById().keySet());
            if (sender.hasPermission("coolarena.add")) {
                arguments.add("add");
            }
            if (sender.hasPermission("coolarena.remove")) {
                arguments.add("remove");
            }
            if (sender.hasPermission("coolarena.reload")) {
                arguments.add("reload");
            }
            return arguments;
        }
        return null;
    }
}
