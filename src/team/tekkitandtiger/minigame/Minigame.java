package team.tekkitandtiger.minigame;

import java.sql.Connection;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.huskehhh.mysql.mysql.MySQL;

import team.tekkitandtiger.minigame.commands.MGCommand;
import team.tekkitandtiger.minigame.teams.TeamHandler;

public class Minigame extends JavaPlugin {
	
	public static Minigame instance;
	
	MySQL MySQL = new MySQL(getConfig().getString("db_host"), getConfig().getString("db_port"), getConfig().getString("db_name"), 
			getConfig().getString("db_user"), getConfig().getString("db_pass"));
	public Connection c = null;
	
	/**
	 * TODO: EVERYTHING
	 */
	public void onEnable() {
		
		instance = this;
		try {
			c = MySQL.openConnection();
		} catch (ClassNotFoundException e) {
			Bukkit.getServer().getLogger().severe("[Minigame] Can't find MySQL class!");
			e.printStackTrace();
		} catch (SQLException e) {
			Bukkit.getServer().getLogger().severe("[Minigame] There was an SQL problem :(");
			e.printStackTrace();
		}
		Bukkit.getServer().getLogger().info("[Minigame] Setting gamestate!");
		GameState.setState(GameState.LOBBY_STATE);
		Bukkit.getServer().getLogger().info("[Minigame] Registering teams...");
		TeamHandler.addTeams();
		Bukkit.getServer().getLogger().info("[Minigame] Enabled!");
	}
	
	public void onDisable() {
		Bukkit.getServer().getLogger().info("[Minigame] Disabled!");
	}
	
	public void initCommands() {
		getCommand("mg").setExecutor(new MGCommand());
	}
}
