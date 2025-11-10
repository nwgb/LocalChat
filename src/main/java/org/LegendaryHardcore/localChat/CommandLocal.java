package org.LegendaryHardcore.localChat;

import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import java.util.ArrayList;
import java.util.List;

/*
 *  Class for /l or /local commands
 */
public class CommandLocal implements CommandExecutor, TabCompleter {
    private final SendLocal sendLocal;

    public CommandLocal(SendLocal sendLocal) {
        this.sendLocal = sendLocal;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Escape if message is empty
        if (args.length == 0) {
            return true;
        }

        Player player = (Player) sender;
        String message = String.join(" ", args);
        this.sendLocal.sendLocalMessage(player, message);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // Disable auto-tab complete after the command
        return new ArrayList<>();
    }
}