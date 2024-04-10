package uk.firedev.vanishchecker;

import com.Zrips.CMI.Containers.CMIUser;
import com.earth2me.essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class VanishChecker {

    public static boolean isVanished(@NotNull Player player) {
        return (isEssentialsVanished(player) || isCMIVanished(player));
    }

    public static boolean isEssentialsVanished(@NotNull Player player) {
        if (Bukkit.getPluginManager().isPluginEnabled("Essentials")) {
            Plugin plugin = Bukkit.getPluginManager().getPlugin("Essentials");
            if (plugin instanceof Essentials) {
                Essentials essentials = (Essentials) plugin;
                return essentials.getOfflineUser(player.getName()).isVanished();
            }
        }
        return false;
    }

    public static boolean isCMIVanished(@NotNull Player player) {
        if (Bukkit.getPluginManager().isPluginEnabled("CMI")) {
            return CMIUser.getUser(player).isCMIVanished();
        }
        return false;
    }

    public static List<Player> getVanishedPlayersFromList(@NotNull List<Player> players) {
        return players.stream().filter(VanishChecker::isVanished).collect(Collectors.toList());
    }

    public static List<Player> getVanishedOnlinePlayers() {
        return Bukkit.getOnlinePlayers().stream().filter(VanishChecker::isVanished).collect(Collectors.toList());
    }

    public static List<Player> getUnvanishedPlayersFromList(@NotNull List<Player> players) {
        return players.stream().filter(player -> !isVanished(player)).collect(Collectors.toList());
    }

    public static List<Player> getUnvanishedOnlinePlayers() {
        return Bukkit.getOnlinePlayers().stream().filter(player -> !isVanished(player)).collect(Collectors.toList());
    }

}