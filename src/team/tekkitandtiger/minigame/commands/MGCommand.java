package team.tekkitandtiger.minigame.commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import team.tekkitandtiger.minigame.GameState;
import team.tekkitandtiger.minigame.Minigame;
import team.tekkitandtiger.minigame.PlayerQueue;

public class MGCommand implements CommandExecutor {
	
	public static Minigame mg = Minigame.instance;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("mg")) {
			if(args.length == 0) {
				if(!(sender instanceof Player)) {
					sender.sendMessage("You cannot use this command!");
				} else {
					if(args.length == 0) {
						sender.sendMessage(ChatColor.BLUE + "[Minigame] Do /mg join to join a game!");
						return true;
					} else if(args.length == 1) {
						switch(args[0]) {
						case "join":
							if(GameState.getCurrentState() == GameState.LOBBY_STATE) {
								PlayerQueue.addPlayer(sender.getName());
								Bukkit.broadcastMessage(ChatColor.BLUE + "[Minigame] Player " + sender.getName() + " has been added to the game!");
								break;
							} else {
								sender.sendMessage(ChatColor.RED + "[Minigame] You cannot join the game while state is not LOBBY_STATE");
								break;
							}
						case "leave":
							PlayerQueue.removePlayer(sender.getName());
							Bukkit.broadcastMessage(ChatColor.BLUE + "[Minigame] Player " + sender.getName() + " has left the game!");
							break;
						case "stats":
							sender.sendMessage(ChatColor.BLUE + "===== STATS =====");
							
							Statement statement = null;
							try {
								statement = mg.c.createStatement();
							} catch (SQLException e1) {
								Bukkit.getServer().getLogger().severe("[Minigame] Couldn't create statement!");
								e1.printStackTrace();
							}
							ResultSet level = null;
							try {
								level = statement.executeQuery("SELECT level FROM stats WHERE player = '" + sender.getName() + "';");
							} catch (SQLException e1) {
								Bukkit.getServer().getLogger().severe("[Minigame] Couldn't execute query!");
								e1.printStackTrace();
							}
							try {
								level.next();
							} catch (SQLException e1) {
								Bukkit.getServer().getLogger().severe("[Minigame] Couldn't go to next row with level.next()");
								e1.printStackTrace();
							}
							ResultSet exp = null;
							try {
								exp = statement.executeQuery("SELECT experience FROM stats WHERE player = '" + sender.getName() + "';");
							} catch (SQLException e1) {
								Bukkit.getServer().getLogger().severe("[Minigame] Couldn't execute query!");
								e1.printStackTrace();
							}
							try {
								exp.next();
							} catch (SQLException e1) {
								Bukkit.getServer().getLogger().severe("[Minigame] Couldn't go to next row with level.next()");
								e1.printStackTrace();
							}
							int expNeeded = 0;
							try {
								expNeeded = exp.getInt("level") * 32;
							} catch (SQLException e) {
								sender.sendMessage(ChatColor.RED + "[Minigame] Couldn't retrieve exp needed!");
								e.printStackTrace();
							}
							try {
								sender.sendMessage(ChatColor.BLUE + "Level: " + level.getInt("level"));
							} catch (SQLException e) {
								sender.sendMessage(ChatColor.RED + "[Minigame] Couldn't retrieve level!");
								e.printStackTrace();
							}
							try {
								sender.sendMessage(ChatColor.BLUE + "Exp: " + exp.getInt("experience") + "/" + expNeeded);
							} catch (SQLException e) {
								sender.sendMessage(ChatColor.RED + "[Minigame] Couldn't retrieve your exp points!");
								e.printStackTrace();
							}
							break;
						default:
							sender.sendMessage(ChatColor.RED + "[Minigame] Unknown Command!");
							break;
						}
					}
				}
			}
		}
		return true;
	}
}
