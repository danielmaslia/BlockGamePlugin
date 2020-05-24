package me.daniel.BlockGame;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player && cmd.getName().equalsIgnoreCase("startgame")) {
		
			int duration = Integer.parseInt(args[args.length - 1]) * 120;
			ArrayList<Player> players = new ArrayList<Player>();
			for(int i = 0; i < args.length - 1; i++) {
				if(Bukkit.getServer().getPlayerExact(args[i]).equals(null)) {
					sender.sendMessage(ChatColor.DARK_RED + "The Name " + args[i] + " Does Not Exist");
					return true;
				}
				players.add(Bukkit.getServer().getPlayerExact(args[i]));
				
			}
			
			BlockGame.runGame(players, duration);
			
		}
		
		if(sender instanceof Player && cmd.getName().equalsIgnoreCase("skip")) {
			
			Player player = Bukkit.getServer().getPlayerExact(args[0]);
			BlockGame.changeMaterials(player);
			
		}
		
		
		
		return true;
		
	}
	
}
