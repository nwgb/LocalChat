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

    // Prefix to display before local messages
    public static final String prefix = ChatColor.AQUA + "[Local]" + ChatColor.RESET;

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
        SendLocal sendLocalMessage = new SendLocal(this);

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
