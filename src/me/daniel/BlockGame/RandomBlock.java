package me.daniel.BlockGame;

import java.util.ArrayList;
import java.util.Random;


import org.bukkit.Material;

public class RandomBlock {
	
	public static ArrayList<String> createList(){
		
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < Material.values().length; i++) {
			
			String str = Material.values()[i].toString();
			str = str.toLowerCase();
			if(str.contains("prismarine") || str.contains("end") || str.contains("beacon") || str.contains("spawner") || str.contains("terracotta") || str.contains("wall_banner") || str.contains("coral") || 
					str.contains("shulker") || str.contains("barrier") || str.contains("dragon") || str.contains("sea_") || str.contains("head") || str.contains("skull") || str.contains("command") 
					|| str.contains("structure") || str.contains("infested") || str.contains("purpur") || str.contains("wither") || str.contains("chorus")) {
				str = str.toUpperCase();
				list.add(str);
				
			}
			
		}
		
		return list;

	}
	
	public static Material createBlock() {
		
		boolean accepted = false;
		Material material = null;
		while(!accepted) {
			Random rand = new Random();
			material = Material.values()[rand.nextInt(Material.values().length)];
			if(material.isBlock() && !(createList().contains(material.toString()))) {
				
				accepted = true;
				
			}
			
		}
		
		return material;
	
	}
	
}
