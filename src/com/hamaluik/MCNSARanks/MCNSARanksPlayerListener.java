package com.hamaluik.MCNSARanks;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class MCNSARanksPlayerListener extends PlayerListener {
	private MCNSARanks plugin;
	
	// grab the main plug in so we can use it later
	public MCNSARanksPlayerListener(MCNSARanks instance) {
		plugin = instance;
	}
	
	@Override
	public void onPlayerJoin(PlayerJoinEvent event) {
		if(plugin.hasPermission(event.getPlayer(), "mcnsaranks.players")) {
			ListOnline list = new ListOnline(plugin);
			list.List(event.getPlayer());
		}
	}
	
	@Override
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
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
