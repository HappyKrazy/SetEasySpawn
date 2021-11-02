package happy.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstJoin implements Listener{
	
	@EventHandler
	public void firstJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
	}
}
