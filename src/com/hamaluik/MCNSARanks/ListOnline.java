package com.hamaluik.MCNSARanks;

import java.util.Arrays;
import java.util.Comparator;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListOnline {
	private MCNSARanks plugin;
	public ListOnline(MCNSARanks instance) {
		plugin = instance;
	}
	
	public void List(CommandSender sender) {
		// get the list of all online players
		Player[] online = Bukkit.getServer().getOnlinePlayers();
		// create a comparator
		Comparator<Player> playerRankComparator = new PlayerRankComparator();
		// and sort the players
		Arrays.sort(online, playerRankComparator);
		
		// get the max number of players
		int maxNum = Bukkit.getServer().getMaxPlayers();
		
		String out = "&7Online (" + online.length + "/" + maxNum + "): ";

		// print out the players
		for(int i = 0; i < online.length - 1; i++) {
			out += plugin.permissions.getUser(online[i]).getPrefix() + online[i].getDisplayName() + "&7, ";
		}
		// don't add a comma to the last one
		if(online.length > 0) {
			out += plugin.permissions.getUser(online[online.length - 1]).getPrefix() + online[online.length - 1].getDisplayName();
		}
		// and send!
		MCNSARanksCommandExecutor.returnMessage(sender, out);
	}

	// create a comparator class for the group rankings
	class PlayerRankComparator implements Comparator<Player> {
		public int compare(Player a, Player b) {
			int ra = plugin.permissions.getUser(a).getOptionInteger("rank", "", 9999);
			int rb = plugin.permissions.getUser(b).getOptionInteger("rank", "", 9999);
			
			if(ra < rb)
				return 1;
			else if(ra == rb)
				return 0;
			else {
				return -1;
			}
		}
	}
}
