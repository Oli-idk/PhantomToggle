package net.deathnotedevs.phantomtoggle;

import net.deathnotedevs.phantomtoggle.configuration.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class CommandPhantomToggle implements CommandExecutor {
    Set<UUID> nosleep;

    ConfigManager configManager;

    public CommandPhantomToggle(ConfigManager configManager) {
        this.nosleep = PhantomToggle.NoPhantom;
        this.configManager = configManager;
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length == 0) {
            return false;
        }
        if (args[0].equals("reload")) {
            if (!sender.hasPermission("phantomtoggle.reload")) {
                sender.sendMessage(configManager.get().getString("NoPerms").replace("&", "§"));
                return true;
            }
            configManager.reload();
            sender.sendMessage("§6PhantomToggle config reloaded.");
            return true;
        }
        if (args[0].equals("clear")) {
            if (!sender.hasPermission("phantomtoggle.clear")) {
                sender.sendMessage(configManager.get().getString("NoPerms").replace("&", "§"));
                return true;
            }
            int count = nosleep.size();
            String names = configManager.get().getString("NoPhantomClear").replace("&", "§").replace("%count%", Integer.toString(count));
            Iterator<UUID> iterator = nosleep.iterator();
            while (iterator.hasNext()) {
                Player player = Bukkit.getPlayer(iterator.next());
                iterator.remove();
                String playername = "{null}";
                if (player != null) {
                    playername = player.getName();
                }
                player.sendMessage(configManager.get().getString("RemovedFromNoPhantoms").replace("&", "§"));
                names += " " + playername;
            }
            sender.sendMessage(names);
            return true;
        }
        return false;
    }
}
