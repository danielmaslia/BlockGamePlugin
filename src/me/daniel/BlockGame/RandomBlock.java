package me.daniel.BlockGame;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;

public class RandomBlock {
	public static Material createBlock(int level) {
		ArrayList<Material> list = new ArrayList<Material>();
		LevelManager.getLevelList(level, list);
		boolean accepted = false;
		Material material = null;

		while (!accepted) {
			Random rand = new Random();
			material = Material.values()[rand.nextInt(Material.values().length)];

			if (list.contains(material)) {
				accepted = true;
			}

		}

		return material;
	}
}
