package com.hamaluik.MCNSARanks.commands;


import org.bukkit.command.CommandSender;

import com.hamaluik.MCNSARanks.ListOnline;
import com.hamaluik.MCNSARanks.MCNSARanks;

public class CommandPlayers implements Command {
	private MCNSARanks plugin;
	public CommandPlayers(MCNSARanks instance) { plugin = instance; }
	
	public boolean onCommand(CommandSender sender, String[] args) {
		ListOnline list = new ListOnline(plugin);
		list.List(sender);
		
		return true;
	}
	
	public String requiredPermission() {
		return "mcnsaranks.who";
	}
	
	public String getCommand() {
		return "who";
	}
	
	public String getArguments() {
		return "";
	}
	
	public String getDescription() {
		return "list all current online players";
	}
}
