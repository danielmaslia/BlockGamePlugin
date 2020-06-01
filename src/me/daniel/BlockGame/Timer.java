package me.daniel.BlockGame;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import me.daniel.PluginTools.Messages;

public class Timer {
	private static BossBar bar = Bukkit.createBossBar("Timer", BarColor.RED, BarStyle.SEGMENTED_12, (BarFlag) null);;

	public static BossBar getBar() {
		return bar;
	}

	public static void addPlayers(ArrayList<PlayerOfGame> players) {

		for (PlayerOfGame e : players) {
			bar.addPlayer(e.getPlayer());
		}

	}

	public static void addPlayer(Player player) {
		bar.addPlayer(player);
	}

	public static void removePlayer(Player player) {
		bar.removePlayer(player);
	}

	public static void setProgress(double d) {
		bar.setProgress(d);
	}

	public static void removeAll() {

		for (Player e : bar.getPlayers()) {
			bar.removePlayer(e);
		}

	}

	public static void updateTime(int time, int duration, boolean full) {

		if (full) {
			Timer.setProgress((double) time / duration);
			Timer.changeTime(time);
		} else {
			Timer.setProgress(((double) time % (duration / 2)) / (duration / 2));
			Timer.changeTime(time % (duration / 2));
		}

	}

	public static void changeTime(int time) {
		Messages timerClock = new Messages("" + (time));
		String timer = timerClock.intoClock();
		bar.setTitle(ChatColor.RED + timer);
	}
}
