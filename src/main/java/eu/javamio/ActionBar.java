package eu.javamio;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ActionBar extends JavaPlugin implements Listener {

    private String actionBarTest;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();

        getServer().getPluginManager().registerEvents(this, this);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                sendActionBar(player);
            }
        }, 0, 20);
    }


    @Override
    public void onDisable() {
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        sendActionBar(event.getPlayer());
    }

    private void loadConfig() {
        FileConfiguration config = getConfig();
        actionBarTest = ChatColor.translateAlternateColorCodes('&', config.getString("actionBarText", "Welcome to the server!"));
    }

    private void sendActionBar(Player player) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(actionBarTest));
    }
}
