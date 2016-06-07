package team.tekkitandtiger.minigame;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerQueue {
	
	public static ArrayList<String> players = new ArrayList<>();
	public static ArrayList<String> ids = new ArrayList<>();
	public static Player player = null;
	
	public static void addPlayer(String name) {
		players.add(name);
	}
	
	public static void removePlayer(String name) {
		players.remove(name);
	}

}
