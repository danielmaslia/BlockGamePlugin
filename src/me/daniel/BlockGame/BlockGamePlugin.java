package me.daniel.BlockGame;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.daniel.PluginTools.EventsClass;

public class BlockGamePlugin extends JavaPlugin {
	public static Plugin plugin;

	@Override
	public void onEnable() {
		plugin = this;
		this.getCommand("startgame").setExecutor(new Commands());
		this.getCommand("skip").setExecutor(new Commands());
		this.getCommand("endgame").setExecutor(new Commands());
		this.getCommand("pause").setExecutor(new Commands());
		this.getCommand("play").setExecutor(new Commands());
		getServer().getPluginManager().registerEvents(new EventsClass(), this);
	}

	@Override
	public void onDisable() {
		Timer.removeAll();
	}
}
