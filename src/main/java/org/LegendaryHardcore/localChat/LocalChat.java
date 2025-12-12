package org.LegendaryHardcore.localChat;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/*
 *  Main LocalChat class
 */
public final class LocalChat extends JavaPlugin {

    // Track players with local chat toggled on
    private Set<UUID> localChatPlayers = new HashSet<>();

    public boolean localEnabled(UUID uuid) {
        return localChatPlayers.contains(uuid);
    }

    public void localChatEnabled(UUID uuid) {
        localChatPlayers.add(uuid);
    }

    public void localChatDisabled(UUID uuid) {
        localChatPlayers.remove(uuid);
    }

    @Override
    public void onEnable() {
        // Prefix and chat colour set in config
        saveDefaultConfig();
        String prefix = getConfig().getString("prefix", "§b[Local]§r");
        String messageColour = getConfig().getString("message-colour", "§x§c§c§f§f§f§f");
        Integer messageRange = getConfig().getInt("message-range", 100);
        boolean dynamicRange = getConfig().getBoolean("dynamic-range", false);

        SendLocal sendLocalMessage = new SendLocal(
                this,
                prefix,
                messageColour,
                messageRange,
                dynamicRange);

        getServer().getPluginManager().registerEvents(sendLocalMessage, this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);

        // /local command
        CommandLocal commandLocal = new CommandLocal(sendLocalMessage);
        getCommand("l").setExecutor(commandLocal);
        getCommand("l").setTabCompleter(commandLocal);

        // /localtoggle command
        CommandLocalToggle commandLocalToggle = new CommandLocalToggle(this);
        getCommand("localtoggle").setExecutor(commandLocalToggle);
        getCommand("localtoggle").setTabCompleter(commandLocal);

        getLogger().info("LocalChat has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("LocalChat plugin disabled!");
    }
}
