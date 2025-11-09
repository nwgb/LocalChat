package org.LegendaryHardcore.localChat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 *  Class for /localtoggle command
 */
public class CommandLocalToggle implements CommandExecutor {
    private final LocalChat plugin;

    public CommandLocalToggle(LocalChat plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (plugin.localEnabled(player.getUniqueId())) {
            // Toggle on
            plugin.localChatDisabled(player.getUniqueId());
            player.sendMessage(ChatColor.AQUA + "[Local] " + ChatColor.WHITE + "Local chat is now toggled " + ChatColor.RED + "off");
        } else {
            // Toggle off
            plugin.localChatEnabled(player.getUniqueId());
            player.sendMessage(ChatColor.AQUA + "[Local] " + ChatColor.WHITE + "Local chat is now toggled " + ChatColor.GREEN + "on");
        }

        return true;
    }
}