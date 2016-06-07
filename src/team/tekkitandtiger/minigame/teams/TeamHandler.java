package team.tekkitandtiger.minigame.teams;

import java.util.ArrayList;

import team.tekkitandtiger.minigame.PlayerQueue;

public class TeamHandler {
	
	public static String[] teams = new String[1];
	public ArrayList<String> red = new ArrayList<>();
	public ArrayList<String> blue = new ArrayList<>();
	
	int numOfPlayers = PlayerQueue.players.size();
	
	public static void addTeams() {
		teams[0] = "Red";
		teams[1] = "Blue";
	}
	
	public void populateTeams() {
//		for(String player : PlayerQueue.players) {
//			for(int i = 0; i < numOfPlayers; i++) {
//				
//			}
//		}
	}
	
	public void setId() {
		
	}
} 
