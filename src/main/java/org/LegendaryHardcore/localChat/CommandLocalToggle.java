package org.LegendaryHardcore.localChat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

/*
 *  Class for /localtoggle command
 */
public class CommandLocalToggle implements CommandExecutor, TabCompleter {
    private final LocalChat plugin;

    public CommandLocalToggle(LocalChat plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (plugin.localEnabled(player.getUniqueId())) {
            // Toggle off
            plugin.localChatDisabled(player.getUniqueId());
            player.sendMessage(ChatColor.AQUA + "[Local] " + ChatColor.WHITE + "Local chat is now toggled " + ChatColor.RED + "off");
        } else {
            // Toggle on
            plugin.localChatEnabled(player.getUniqueId());
            player.sendMessage(ChatColor.AQUA + "[Local] " + ChatColor.WHITE + "Local chat is now toggled " + ChatColor.GREEN + "on");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // Disable auto-tab complete after the command
        return new ArrayList<>();
    }
}