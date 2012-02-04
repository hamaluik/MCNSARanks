package com.hamaluik.MCNSARanks.commands;

import java.util.Comparator;

import ru.tehkode.permissions.PermissionGroup;

// create a comparator class for the group rankings
public class RankComparator implements Comparator<PermissionGroup> {
	public int compare(PermissionGroup a, PermissionGroup b) {
		int ra = a.getOptionInteger("rank", "", 9999);
		int rb = b.getOptionInteger("rank", "", 9999);
		
		if(ra < rb)
			return 1;
		else if(ra == rb)
			return 0;
		else {
			return -1;
		}
	}
}
