package team.tekkitandtiger.minigame.teams;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import team.tekkitandtiger.minigame.PlayerQueue;

public class TeamHandler {
	
	public static String[] teams = new String[1];
	public static ArrayList<String> red = new ArrayList<String>();
	public static ArrayList<String> blue = new ArrayList<String>();
	
	int numOfPlayers = PlayerQueue.players.size();
	
	public static void addTeams() {
		teams[0] = "Red";
		teams[1] = "Blue";
	}
	
	public static void addPlayer(TeamType type, Player player) {
		if(isInTeam(player)) {
			player.sendMessage(ChatColor.RED + "[Minigame] You are already part of a team!");
		}
		switch (type) {
		case RED:
			red.add(player.getName());
			break;
		case BLUE:
			blue.add(player.getName());
			break;
		}
		player.sendMessage(ChatColor.BLUE + "[Minigame] You have been added to the " + type.name() + " team.");
	}
	
	public static boolean isInTeam(Player player) {
		return red.contains(player.getName()) || blue.contains(player.getName());
	}
	public static void removePlayer(Player player) {
		if(isInTeam(player)) {
			if(getTeamType(player) == TeamType.BLUE) {
				blue.remove(player.getName());
			} else {
				red.remove(player.getName());
			}
		} else {
			player.sendMessage(ChatColor.RED + "[Minigame] You are not part of a team!");
		}
	}
	
	public static void clearTeams() {
		red.clear();
		blue.clear();
	}
	
	public static List<String> getRedTeam() {
		return red;
	}
	
	public static List<String> getBlueTeam() {
		return blue;
	}
	
	public static ArrayList<String> getAllPlayers() {
		ArrayList<String> combinedTeams = new ArrayList<String>();
		combinedTeams.addAll(red);
		combinedTeams.addAll(blue);
		return combinedTeams;
	}
	
	public static TeamType getTeamType(Player player) {
		if(!isInTeam(player)) {
			return null;
		}
		return (red.contains(player.getName()) ? TeamType.RED : TeamType.BLUE);
	}
} 
