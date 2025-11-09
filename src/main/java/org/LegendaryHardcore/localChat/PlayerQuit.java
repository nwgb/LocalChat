package org.LegendaryHardcore.localChat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/*
 * Player quit listener. Remove from localChatPlayers on disconnect
 */
public class PlayerQuit implements Listener {

    private final LocalChat plugin;

    public PlayerQuit(LocalChat plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.localChatDisabled(event.getPlayer().getUniqueId());
    }
}
