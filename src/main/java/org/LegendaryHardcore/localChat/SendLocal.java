package org.LegendaryHardcore.localChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/*
 * SendLocal class.
 * Contains chat listener and function to format and send a local chat message
 */
public class SendLocal implements Listener {
    private final LocalChat plugin;

    // Set maximum message distance to server render distance
    private final double maxMessageDistance;

    public SendLocal(LocalChat plugin) {

        this.plugin = plugin;
        this.maxMessageDistance = Math.pow(Bukkit.getServer().getViewDistance() * 16,2);
    }

    // Chat listener
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player sender = event.getPlayer();

        // If this is a local message, cancel sending globally
        if (plugin.localEnabled(sender.getUniqueId())) {
            event.setCancelled(true);

            String rawMessage = event.getMessage();
            // Schedule on sender's region
            sender.getScheduler().execute(plugin, () -> {
                this.sendLocalMessage(sender, rawMessage);
            }, null, 1L);
        }
    }

    // Format and send message locally
    public void sendLocalMessage(Player sender, String messageRaw) {
        World world = sender.getWorld();
        Location location = sender.getLocation().clone();
        String message = plugin.prefix
                + ChatColor.WHITE + " <"
                + sender.getDisplayName()
                + ChatColor.WHITE + "> "
                + messageRaw;

        // If player is within the same world and within range or is OP, format and send the message
        for (Player recipient : Bukkit.getOnlinePlayers()) {
                if (recipient.isOp() || (recipient.getWorld().equals(world) &&
                        recipient.getLocation().distanceSquared(location) <= maxMessageDistance)) {
                    recipient.sendMessage(message);

                }

            }
        plugin.getLogger().info("<" + sender.getName() + "> " + message);
    }
}