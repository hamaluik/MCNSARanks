package com.hamaluik.MCNSARanks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
	private MCNSARanks plugin;
	
	// grab the main plug in so we can use it later
	public PlayerListener(MCNSARanks instance) {
		plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void joinHandler(PlayerJoinEvent event) {
		try {
			if(plugin.hasPermission(event.getPlayer(), "mcnsaranks.players")) {
				ListOnline list = new ListOnline(plugin);
				list.List(event.getPlayer());
			}
			
			// set their rank colour for the display list
			String result = plugin.processColours(plugin.permissions.getUser(event.getPlayer()).getPrefix() + event.getPlayer().getName());
			if(result.length() > 16) {
				result = result.substring(0, 16);
			}
			event.getPlayer().setPlayerListName(result);
		}
		catch(Exception e) {
			plugin.log.info("[MCNSARanks] Error: " + e.getMessage());
		}
	}

	
	@EventHandler(priority = EventPriority.LOWEST)
	public void preprocessHandler(PlayerCommandPreprocessEvent event) {
		if(event.isCancelled()) {
			return;
		}
		
		if(event.getMessage().equalsIgnoreCase("/who") || event.getMessage().equalsIgnoreCase("/list") || event.getMessage().equalsIgnoreCase("/playerlist") || event.getMessage().equalsIgnoreCase("/online") || event.getMessage().equalsIgnoreCase("/players")) {
			ListOnline list = new ListOnline(plugin);
			list.List(event.getPlayer());
			event.setCancelled(true);
		}
	}
}
