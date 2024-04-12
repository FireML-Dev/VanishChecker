package uk.firedev.vanishchecker;

import com.Zrips.CMI.Containers.CMIUser;
import com.earth2me.essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class VanishChecker {

    public static boolean isVanished(@NotNull Player player) {
        return (isMetadataVanished(player) || isEssentialsVanished(player) || isCMIVanished(player));
    }

    /**
     * Checks whether a player is vanished via the EssentialsX plugin.
     * @param player The player to check
     * @return Whether the player is vanished via the EssentialsX plugin.
     */
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

    /**
     * Checks whether a player is vanished via the CMI plugin.
     * @param player The player to check
     * @return Whether the player is vanished via the CMI plugin.
     */
    public static boolean isCMIVanished(@NotNull Player player) {
        if (Bukkit.getPluginManager().isPluginEnabled("CMI")) {
            return CMIUser.getUser(player).isCMIVanished();
        }
        return false;
    }

    /**
     * Checks whether a player is vanished via the player's Metadata. Provided by the SuperVanish plugin.
     * Supposedly "supported by SuperVanish, PremiumVanish, EssentialsX, VanishNoPacket and many more vanish plugins."
     * @param player The player to check
     * @return Whether the player is marked as vanished in their Metadata.
     */
    public static boolean isMetadataVanished(@NotNull Player player) {
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }

    /**
     * Filters the provided list to remove all unvanished players.
     * @param players The list to filter.
     * @return The filtered list, with all unvanished players removed.
     */
    public static List<Player> getVanishedPlayersFromList(@NotNull List<Player> players) {
        return players.stream().filter(VanishChecker::isVanished).collect(Collectors.toList());
    }

    /**
     * Filters the list of online players to remove unvanished players.
     * @return A list of all online players who are vanished.
     */
    public static List<Player> getVanishedOnlinePlayers() {
        return Bukkit.getOnlinePlayers().stream().filter(VanishChecker::isVanished).collect(Collectors.toList());
    }

    /**
     * Filters the provided list to remove all vanished players.
     * @param players The list to filter.
     * @return The filtered list, with all vanished players removed.
     */
    public static List<Player> getUnvanishedPlayersFromList(@NotNull List<Player> players) {
        return players.stream().filter(player -> !isVanished(player)).collect(Collectors.toList());
    }

    /**
     * Filters the list of online players to remove vanished players.
     * @return A list of all online players who are unvanished.
     */
    public static List<Player> getUnvanishedOnlinePlayers() {
        return Bukkit.getOnlinePlayers().stream().filter(player -> !isVanished(player)).collect(Collectors.toList());
    }

}