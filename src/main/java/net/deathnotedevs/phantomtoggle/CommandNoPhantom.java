package net.deathnotedevs.phantomtoggle;

import net.deathnotedevs.phantomtoggle.configuration.ConfigManager;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.UUID;

public class CommandNoPhantom implements CommandExecutor {

        Set<UUID> NoPhantom;
        ConfigManager configManager;

        public CommandNoPhantom(ConfigManager configManager) {
            this.NoPhantom = PhantomToggle.NoPhantom;
            this.configManager = configManager;
        }

        public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command is for players only");
                return true;
            }
            Player player = (Player) sender;
            if (!player.hasPermission("phantomtoggle.toggle")) {
                player.sendMessage(configManager.get().getString("NoPerms").replace("&", "§"));
                return true;
            }
            if (NoPhantom.contains(player.getUniqueId())) {
                NoPhantom.remove(player.getUniqueId());
                player.sendMessage(configManager.get().getString("RemovedFromNoPhantoms").replace("&", "§"));
                return true;
            }
            NoPhantom.add(player.getUniqueId());
            player.sendMessage(configManager.get().getString("AddedToNoPhantoms").replace("&", "§"));
            return true;
        }
    }