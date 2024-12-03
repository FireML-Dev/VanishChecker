package uk.firedev.vanishchecker;

import com.Zrips.CMI.Containers.CMIUser;
import com.earth2me.essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.sayandev.sayanvanish.api.SayanVanishAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VanishChecker {

    public static boolean isVanished(Player player) {
        if (player == null) {
            return false;
        }
        return (isMetadataVanished(player) || isEssentialsVanished(player) || isCMIVanished(player) || isSayanVanished(player));
    }

    /**
     * Checks whether a player is vanished via the EssentialsX plugin.
     * @param player The player to check
     * @return Whether the player is vanished via the EssentialsX plugin.
     */
    public static boolean isEssentialsVanished(Player player) {
        if (player != null && Bukkit.getPluginManager().isPluginEnabled("Essentials")) {
            Plugin plugin = Bukkit.getPluginManager().getPlugin("Essentials");
            if (plugin instanceof Essentials essentials) {
                com.earth2me.essentials.User user = essentials.getUser(player);
                if (user != null) {
                    return user.isVanished();
                }
            }
        }
        return false;
    }

    /**
     * Checks whether a player is vanished via the CMI plugin.
     * @param player The player to check
     * @return Whether the player is vanished via the CMI plugin.
     */
    public static boolean isCMIVanished(Player player) {
        if (player != null && Bukkit.getPluginManager().isPluginEnabled("CMI")) {
            CMIUser user = CMIUser.getUser(player);
            if (user != null) {
                return user.isCMIVanished();
            }
        }
        return false;
    }

    /**
     * Checks whether a player is vanished via the SayanVanish plugin.
     * @param player The player to check
     * @return Whether the player is vanished via the SayanVanish plugin.
     */
    public static boolean isSayanVanished(Player player) {
        if (player != null && Bukkit.getPluginManager().isPluginEnabled("SayanVanish")) {
            org.sayandev.sayanvanish.api.User user = SayanVanishAPI.user(player.getUniqueId());
            if (user != null) {
                return user.isVanished();
            }
        }
        return false;
    }

    /**
     * Checks whether a player is vanished via the player's Metadata. Provided by the SuperVanish plugin.
     * Supposedly "supported by SuperVanish, PremiumVanish, EssentialsX, VanishNoPacket and many more vanish plugins."
     * @param player The player to check
     * @return Whether the player is marked as vanished in their Metadata.
     */
    public static boolean isMetadataVanished(Player player) {
        if (player == null) {
            return false;
        }
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }

    /**
     * Filters the provided list to only contain vanished players.
     * @param players The list to filter.
     * @return The filtered list, only keeping vanished players.
     */
    public static List<Player> getVanishedPlayersFromList(List<Player> players) {
        if (players == null) {
            return new ArrayList<>();
        }
        return players.stream().filter(VanishChecker::isVanished).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filters the list of online players to only contain those who are vanished.
     * @return A list of all online players who are vanished.
     */
    public static List<Player> getVanishedOnlinePlayers() {
        return Bukkit.getOnlinePlayers().stream().filter(VanishChecker::isVanished).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filters the provided list to only contain visible players.
     * @param players The list to filter.
     * @return The filtered list, only keeping visible players.
     */
    public static List<Player> getVisiblePlayersFromList(List<Player> players) {
        if (players == null) {
            return new ArrayList<>();
        }
        return players.stream().filter(player -> !isVanished(player)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Filters the list of online players to only contain those who are visible.
     * @return A list of all online players who are visible.
     */
    public static List<Player> getVisibleOnlinePlayers() {
        return Bukkit.getOnlinePlayers().stream().filter(player -> !isVanished(player)).collect(Collectors.toCollection(ArrayList::new));
    }

}