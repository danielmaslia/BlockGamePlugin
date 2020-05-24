package me.daniel.BlockGame;



import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import me.daniel.PluginTools.Messages;

public class PlayerOfGame{
	
	private Player player;
	private int timesFound;
	private Material material;
	private Messages message;

	
	PlayerOfGame(Player player){
		
		this.player = player;
		timesFound = 0;
		material = RandomBlock.createBlock();
		message = new Messages(material.toString());
		message.intoReadable();
		
	}
	
	public void addTimes() {
		
		timesFound ++;
		
	}
	
	public int getTimes() {
		
		return timesFound;
		
	}
	
	public Player getPlayer() {
		
		return player;
		
	}
	
	public void changeMaterial() {
		
		material = RandomBlock.createBlock();
		message = new Messages(material.toString());
		message.intoReadable();
		
	}
	
	public void changeMaterial(Material material1) {
		
		material = material1;
		
	}
	
	public Material getMaterial() {
		
		return material;
		
	}
	
	public String getMessage() {
		
		return message.getMessage();
		
	}
	
	public String getArticle() {
		
		return message.getArticle();
		
	}
	
	public Block getBlock() {
		
		Location loc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() - 1, player.getLocation().getZ());
		return player.getWorld().getBlockAt(loc);
		
	}
	
	public static void checkScores(ArrayList<PlayerOfGame> list, int time, int timeCompare, int score) {
		
		for(PlayerOfGame e : list) {
			if(time == timeCompare) {
			
				if(e.getTimes() < score) {
				
					Bukkit.broadcastMessage(ChatColor.AQUA + e.getPlayer().getName() + ChatColor.RED + " Still Needs Their Block!");
				
				}
			
			}
		}
		
		
	}
	
	
	public static void checkMat(ArrayList<PlayerOfGame> list, int time, int duration) {
		
		for(PlayerOfGame e : list) {
			Player player = e.getPlayer();
			if(e.getBlock().getType().equals(e.getMaterial())) {
				
				e.addTimes();
				if(e.getTimes() == 1) {
					
					Messages clock = new Messages(Integer.toString(time - duration/2));
					String timeClock = clock.intoClock();
					Bukkit.broadcastMessage("" + ChatColor.BOLD + ChatColor.GREEN + player.getName() + ChatColor.RESET + ChatColor.AQUA + 
							" Has Found Their Block With " + ChatColor.GOLD + timeClock + ChatColor.AQUA + " Remaining!");
					e.changeMaterial();
					clock = new Messages(Integer.toString(time));
					timeClock = clock.intoClock();
					Bukkit.broadcastMessage("" + ChatColor.BOLD + ChatColor.GREEN + player.getName() + ChatColor.RESET + ChatColor.AQUA + " Has "  + ChatColor.GOLD + 
							timeClock + ChatColor.AQUA + " To Find " + e.getArticle() + " " + ChatColor.BOLD + ChatColor.RED +  e.getMessage());
					
				}
				
				else {
					
					Messages clock = new Messages(Integer.toString(time));
					String timeClock = clock.intoClock();
					Bukkit.broadcastMessage("" + ChatColor.BOLD + ChatColor.GREEN + player.getName() + ChatColor.RESET + ChatColor.AQUA + 
							" Has Found Their Block With " + ChatColor.GOLD + timeClock + ChatColor.AQUA + " Remaining! They Can Chill For The Time Remaining!");
					e.changeMaterial(Material.BARRIER);
				}
				
			}
		}
		
		
		
	}
	
	public static ArrayList<PlayerOfGame> checkLoser(ArrayList<PlayerOfGame> winners, int score) {
		
		ArrayList<PlayerOfGame> losers = new ArrayList<PlayerOfGame>();
		
		int num = winners.size();
		
		for(PlayerOfGame e : winners) {
			if(e.getTimes() < score) {
				losers.add(e);
			}
		}
		
		for(PlayerOfGame e : losers) {
			winners.remove(e);
		}
		
		if(losers.size() == num) {
			
			return losers;
		}
		
		if(winners.size() == 1 && losers.size() != 0) {
			Bukkit.broadcastMessage(ChatColor.GREEN + winners.get(0).getPlayer().getDisplayName() + " Has Won!");
			return losers;
			
		}
		
		for(PlayerOfGame e : losers) {
			Bukkit.broadcastMessage("" + ChatColor.BOLD + ChatColor.GREEN + e.getPlayer().getDisplayName() + ChatColor.RESET + ChatColor.RED + " Has Lost!");
		}
		return losers;
		
	}
	
	public static boolean checkIfLost(ArrayList<PlayerOfGame> list, int score) {
		for(PlayerOfGame e : list) {
			if(e.getTimes() < score) {
				return true;
			}
		}
		return false;
	}
	
	public static void updatePlayers(boolean timeCheck, ArrayList<PlayerOfGame> list, boolean changeTime, int time, int duration) {
		if(list.size() > 0 && timeCheck) {
			int tempTime = time;
			if(changeTime) {
				tempTime = time-(duration/2);
			}
			
			String names = list.get(0).getPlayer().getDisplayName();
			
			for(int i = 1; i < list.size(); i++) {
				names += ", " + list.get(i).getPlayer().getDisplayName();
			}
			
			
		
			Messages clock = new Messages(Integer.toString(tempTime));
			String timeClock = clock.intoClock();
			Bukkit.broadcastMessage("" + ChatColor.BOLD + ChatColor.GREEN + names + ", " + ChatColor.RESET + ChatColor.AQUA + 
					"You Have " + ChatColor.GOLD + timeClock + ChatColor.AQUA + " Left To Find Your Block!");
		}
		
	}
	
	public static ArrayList<PlayerOfGame> listMaker(ArrayList<PlayerOfGame> list, int score, boolean equality){
		ArrayList<PlayerOfGame> madeList = new ArrayList<PlayerOfGame>();
		
		for(PlayerOfGame e : list) {
			if(equality && e.getTimes() == score) {
				madeList.add(e);
			}
			else if(!equality && e.getTimes() < score) {
				madeList.add(e);
			}
			
		}
		return madeList;
	}
	
	public static void EndMessages(BukkitScheduler scheduler) {
		scheduler.scheduleSyncRepeatingTask(BlockGamePlugin.plugin, new Runnable() {
			int count1 = 0;
			public void run() {
				if(count1 == 0) {
					Bukkit.broadcastMessage("" + ChatColor.BOLD + ChatColor.GREEN + "Y'all Are " + ChatColor.DARK_RED + " Garbage ");
					
				}
				else if(count1 == 1) {
					Bukkit.broadcastMessage("" + ChatColor.BOLD + ChatColor.GREEN + "Eat My Ass I'm Done With This Shit");
					
				}
				else {
					Bukkit.broadcastMessage("" + ChatColor.ITALIC +  "*GameBotXDXD has left the chat*");
					scheduler.cancelTasks(BlockGamePlugin.plugin);
				}
				count1++;
			}
		}, 0, 50);
	}

}
