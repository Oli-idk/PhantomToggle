package net.deathnotedevs.phantomtoggle;

import net.deathnotedevs.phantomtoggle.configuration.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public final class PhantomToggle extends JavaPlugin {

    public static PhantomToggle plugin;
    public static Set<UUID> NoPhantom = new HashSet<>();

    ConfigManager configManager;

    @Override
    public void onEnable() {
        plugin = this;
        configManager = new ConfigManager(this);

        getCommand("phantomtoggle").setExecutor(new CommandPhantomToggle(configManager));
        getCommand("nophantom").setExecutor(new CommandNoPhantom(configManager));
        getServer().getPluginManager().registerEvents(new EventLogout(), this);

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, () -> {
            World world = Bukkit.getWorld(configManager.get().getString("World"));
            if (world == null) {
                System.err.println("[PhantomToggle] Invalid world found in config.");
                return;
            }
            if (world.getTime() < 20L) {
                Iterator<UUID> iterator = NoPhantom.iterator();
                while (iterator.hasNext()) {
                    Player player = Bukkit.getPlayer(iterator.next());
                    player.setStatistic(Statistic.TIME_SINCE_REST, 0);
                    iterator.remove();
                    String playername = "{null}";
                    if (player != null) {
                        playername = player.getName();
                    }
                }
            }
        }, 0L, 20L);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
