package me.daniel.BlockGame;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import me.daniel.PluginTools.Messages;

public class BlockGame {
	private static ArrayList<PlayerOfGame> list = new ArrayList<PlayerOfGame>();
	public static int taskID;
	private static boolean pause = false;

	public static void runGame(ArrayList<Player> players, int duration, int level) {
		list.clear();

		for (int i = 0; i < players.size(); i++) {
			list.add(new PlayerOfGame(players.get(i).getPlayer(), level));
			Bukkit.broadcastMessage("" + ChatColor.BOLD + ChatColor.GREEN + list.get(i).getPlayer().getName()
					+ ChatColor.RESET + ChatColor.AQUA + " Needs To Find " + list.get(i).getArticle() + " "
					+ ChatColor.BOLD + ChatColor.RED + list.get(i).getMessage());
		}

		Timer.setProgress(1);
		Timer.changeTime(duration / 2);
		Timer.addPlayers(list);
		BukkitScheduler scheduler = BlockGamePlugin.plugin.getServer().getScheduler();
		taskID = scheduler.scheduleSyncRepeatingTask(BlockGamePlugin.plugin, new Runnable() {
			int time = duration - 1;
			long count = 1;

			@Override
			public void run() {

				if (list.size() == 0) {
					Bukkit.broadcastMessage(ChatColor.RED + "Game Has Been Ended Because There Are No Players");
					scheduler.cancelTasks(BlockGamePlugin.plugin);
					return;
				}

				for (int i = 0; i < list.size(); i++) {

					if (!list.get(i).getPlayer().isOnline()) {
						Bukkit.broadcastMessage(ChatColor.AQUA + "" + list.get(i).getPlayer().getName().toString()
								+ ChatColor.RED + " Has Left The Game");
						list.remove(i);
					}

				}

				if (!pause) {
					PlayerOfGame.checkMat(list, time, duration);
				}

				if (count % 4 == 0 && !pause) {
					Timer.updateTime(time, duration, !PlayerOfGame.checkIfLost(list, 1));
					PlayerOfGame.checkScores(list, time, duration / 2 + 15, 1);
					PlayerOfGame.checkScores(list, time, 15, 2);

					if (time == 12 && !PlayerOfGame.checkIfLost(list, 2)) {
						Bukkit.broadcastMessage(ChatColor.GOLD + "New Block In...");
					}

					if (time % (duration / 2) <= 10 && time % (duration / 2) != 0
							&& (time < 11 || PlayerOfGame.checkIfLost(list, 1))) {
						Bukkit.broadcastMessage(
								ChatColor.GREEN + "" + time % (duration / 2) + ChatColor.LIGHT_PURPLE + " Seconds!");
					}

					if (time == duration / 2 && PlayerOfGame.checkIfLost(list, 1) || time == 0) {
						ArrayList<PlayerOfGame> losers;

						if (time == 0) {
							losers = PlayerOfGame.checkLoser(list, 2);
						} else {
							losers = PlayerOfGame.checkLoser(list, 1);
						}

						int playerNum = losers.size() + list.size();

						if (losers.size() >= playerNum - 1 && losers.size() != 0) {

							if (time == duration / 2) {
								scheduler.cancelTasks(BlockGamePlugin.plugin);
							}

							if (losers.size() == playerNum) {
								PlayerOfGame.EndMessages(scheduler);
							}

							Timer.removeAll();
							return;
						}

						if (time == duration / 2) {

							for (PlayerOfGame e : losers) {
								list.remove(e);
								Timer.removePlayer(e.getPlayer());
							}

						}

						if (time == 0) {
							ArrayList<Player> newPlayers = new ArrayList<Player>();

							for (PlayerOfGame e : list) {
								newPlayers.add(e.getPlayer());
							}

							scheduler.cancelTasks(BlockGamePlugin.plugin);
							Timer.removeAll();
							runGame(newPlayers, duration, level + 2);
						}

					} else if (time % 60 == 0) {
						PlayerOfGame.updatePlayers(true, PlayerOfGame.listMaker(list, 1, false), true, time, duration);
						PlayerOfGame.updatePlayers(true, PlayerOfGame.listMaker(list, 1, true), false, time, duration);
						ArrayList<PlayerOfGame> successfulPlayers = PlayerOfGame.listMaker(list, 2, true);

						if (successfulPlayers.size() == list.size()) {
							Messages clock = new Messages("" + time);
							String timeClock = clock.intoClock();
							Bukkit.broadcastMessage(ChatColor.AQUA + "Players, You Have " + ChatColor.GOLD + timeClock
									+ ChatColor.AQUA + " Left Until You Get A New Block!");
						}

					}

					time--;
				}

				count++;
			}
		}, 0, 5);
	}

	public static void changeMaterials(Player player) {

		for (PlayerOfGame e : list) {

			if (e.getPlayer().equals(player)) {
				e.changeMaterial();
				Messages message = new Messages(e.getMaterial().toString());
				message.intoReadable();
				Bukkit.broadcastMessage("" + ChatColor.BOLD + ChatColor.AQUA + player.getName() + "'s" + ChatColor.RESET
						+ ChatColor.AQUA + " Block Was Changed To " + ChatColor.BOLD + ChatColor.RED
						+ message.getMessage());
				return;
			}

		}
		
		Bukkit.broadcastMessage(ChatColor.AQUA + "" + player.getName().toString() + " Is Not Playing The Game");

	}

	public static void endGame(Player sender) {
		BukkitScheduler scheduler = BlockGamePlugin.plugin.getServer().getScheduler();
		boolean test = scheduler.isQueued(taskID);

		if (test) {
			scheduler.cancelTask(taskID);
			taskID = 0;
			Bukkit.broadcastMessage(ChatColor.GREEN + "Game Has Been Cancelled By " + sender.getName().toString());
			Timer.removeAll();
		} else {
			sender.sendMessage(ChatColor.RED + "No Game Running To Cancel");
		}

	}

	public static void pause(Player sender) {

		if (pause == true) {
			sender.sendMessage(ChatColor.RED + "Game Is Already Paused");
		} else {
			pause = true;
		}

	}

	public static void play(Player sender) {

		if (pause == false) {
			sender.sendMessage(ChatColor.RED + "Game Is Already Running");
		} else {
			pause = false;
		}

	}
}
