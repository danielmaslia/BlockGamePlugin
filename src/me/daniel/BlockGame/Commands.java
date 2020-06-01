package me.daniel.BlockGame;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player && cmd.getName().equalsIgnoreCase("startgame")) {
			
			if(args.length < 2) {
				return false;
			}

			try {
				Integer.parseInt(args[args.length - 1]);
			} catch (NumberFormatException e) {
				sender.sendMessage(ChatColor.RED + "Time Must Be A Whole Number");
				return false;
			}

			int duration = Integer.parseInt(args[args.length - 1]) * 120;
			ArrayList<Player> players = new ArrayList<Player>();

			for (int i = 0; i < args.length - 1; i++) {

				if (Bukkit.getServer().getPlayerExact(args[i]) == null) {
					sender.sendMessage(ChatColor.RED + "The Name " + args[i] + " Does Not Exist");
					return true;
				}

				players.add(Bukkit.getServer().getPlayerExact(args[i]));
			}

			if (BlockGame.taskID == 0) {
				BlockGame.runGame(players, duration, 1);
			} else {
				sender.sendMessage(ChatColor.RED + "You Must End The Previous Game Before Starting A New One");
			}

		}

		if (sender instanceof Player && cmd.getName().equalsIgnoreCase("skip")) {
			if(args.length != 1) {
				return false;
			}
			if (Bukkit.getServer().getPlayerExact(args[0]) == null) {
				sender.sendMessage(ChatColor.RED + "The Name " + args[0] + " Does Not Exist");
				return true;
			}
			Player player = Bukkit.getServer().getPlayerExact(args[0]);
			BlockGame.changeMaterials(player);
		}

		if (sender instanceof Player && cmd.getName().equalsIgnoreCase("endgame")) {
			BlockGame.endGame((Player) sender);
		}

		if (sender instanceof Player && cmd.getName().equalsIgnoreCase("pause")) {
			BlockGame.pause((Player) sender);
		}

		if (sender instanceof Player && cmd.getName().equalsIgnoreCase("play")) {
			BlockGame.play((Player) sender);
		}

		return true;
	}
}
