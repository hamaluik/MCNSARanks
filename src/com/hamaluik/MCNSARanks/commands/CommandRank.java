package com.hamaluik.MCNSARanks.commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;


import com.hamaluik.MCNSARanks.MCNSARanks;
import com.hamaluik.MCNSARanks.MCNSARanksCommandExecutor;

public class CommandRank implements Command {
	private MCNSARanks plugin;
	public CommandRank(MCNSARanks instance) { plugin = instance; }
	
	public boolean onCommand(CommandSender sender, String[] args) {
		if(args.length != 1) {
			return false;
		}
		
		//go through all players to find ours
		OfflinePlayer[] players = plugin.getServer().getOfflinePlayers();
		int total = players.length;
		boolean found = false;
		String player = "";
		for(int i = 0; i < total; i++) {
			if(players[i].getName().equalsIgnoreCase(args[0])){
				found = true;
				player = players[i].getName();
			}
		}
		//did it find a player?
		if(found != true){
			MCNSARanksCommandExecutor.returnMessage(sender, "&cError: player '"+args[0]+"' could not be found!");
			return true;
		}
		/*
		// query the player
		Player player = plugin.getServer().getPlayer(args[0]);
		if(player == null) {
			MCNSARanksCommandExecutor.returnMessage(sender, "&cError: player '"+args[0]+"' could not be found!");
			return true;
		}
		 */
		PermissionUser user = plugin.permissions.getUser(player);
		// ok, we have the player.. get their group!
		PermissionGroup[] groups = user.getGroups();
		
		// make sure they have at least 1 group
		if(groups.length < 1) {
			MCNSARanksCommandExecutor.returnMessage(sender, "&cError: player '"+player+"' doesn't have a rank!");
			return true;
		}
		
		// get the best-ranked group
		int lowest = 9999;
		PermissionGroup group = groups[0];
		for(int i = 1; i < groups.length; i++) {
			if(groups[i].getRank() < lowest) {
				lowest = groups[i].getRank();
				group = groups[i];
			}
		}
		
		// now report it!
		MCNSARanksCommandExecutor.returnMessage(sender, player + " is ranked as a: " + group.getPrefix() + group.getName() + "&f!");
		
		return true;
	}
	
	public String requiredPermission() {
		return "mcnsaranks.rank";
	}
	
	public String getCommand() {
		return "rank";
	}
	
	public String getArguments() {
		return "<player>";
	}
	
	public String getDescription() {
		return "queries a user's rank";
	}
}