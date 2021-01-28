package net.deathnotedevs.phantomtoggle;

import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGivePhantom implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command is for players only");
            return true;
        }
        Player player = (Player) sender;
        if (player.hasPermission("phantomtoggle.admin")) {
            player.sendMessage(ChatColor.RED + "watch out for your new friends");
            player.setStatistic(Statistic.TIME_SINCE_REST, 72000000);
            return true;
        }
        return true;
    }
}
