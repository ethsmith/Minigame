package team.tekkitandtiger.minigame;

import java.util.ArrayList;

public class PlayerQueue {
	
	public static ArrayList<String> players = new ArrayList<>();
	
	public static void addPlayer(String name) {
		players.add(name);
	}
	
	public static void removePlayer(String name) {
		players.remove(name);
	}

}
