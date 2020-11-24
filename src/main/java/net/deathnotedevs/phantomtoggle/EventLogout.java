package net.deathnotedevs.phantomtoggle;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import java.util.UUID;
import java.util.Set;
import org.bukkit.event.Listener;

public class EventLogout implements Listener {
    Set<UUID> NoPhantom;

    public EventLogout() {
        this.NoPhantom = PhantomToggle.NoPhantom;
    }

    @EventHandler
    public void onLogout(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        NoPhantom.remove(player.getUniqueId());
    }
}
