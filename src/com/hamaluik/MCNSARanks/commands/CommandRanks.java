package com.hamaluik.MCNSARanks.commands;

import java.util.Arrays;
import java.util.Comparator;

import org.bukkit.command.CommandSender;

import ru.tehkode.permissions.PermissionGroup;

import com.hamaluik.MCNSARanks.MCNSARanks;
import com.hamaluik.MCNSARanks.MCNSARanksCommandExecutor;

public class CommandRanks implements Command {
	private MCNSARanks plugin;
	public CommandRanks(MCNSARanks instance) { plugin = instance; }
	
	public boolean onCommand(CommandSender sender, String[] args) {
		// get the groups
		PermissionGroup[] groups = plugin.permissions.getGroups();
		
		// create a comparator
		Comparator<PermissionGroup> rankComparator = new RankComparator();
		// and sort the groups
		Arrays.sort(groups, rankComparator);
		
		// print out the ranks
		String ranks = "&fRanks in order of least to greatest: ";
		for(int i = 0; i < groups.length - 1; i++) {
			// exclude unranked groups
			if(groups[i].getOptionInteger("rank", "", 9999) != 9999) {
				ranks += groups[i].getPrefix() + groups[i].getName() + "&f, ";				
			}
		}
		// don't add a comma to the last one
		if(groups.length > 0) {
			ranks += groups[groups.length - 1].getPrefix() + groups[groups.length - 1].getName();
		}
		// and send!
		MCNSARanksCommandExecutor.returnMessage(sender, ranks);
		
		return true;
	}
	
	public String requiredPermission() {
		return "mcnsaranks.ranks";
	}
	
	public String getCommand() {
		return "ranks";
	}
	
	public String getArguments() {
		return "";
	}
	
	public String getDescription() {
		return "list all current ranks";
	}
}
