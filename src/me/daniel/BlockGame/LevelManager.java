package me.daniel.BlockGame;

import java.util.ArrayList;

import org.bukkit.Material;

public class LevelManager {
	public static void remove(ArrayList<Material> old, ArrayList<Material> removed) {

		for (int i = 0; i < removed.size(); i++) {

			if (old.contains(removed.get(i))) {
				old.remove(removed.get(i));
			}

		}

	}

	public static void add(ArrayList<Material> old, ArrayList<Material> added) {

		for (int i = 0; i < added.size(); i++) {

			if (!(old.contains(added.get(i)))) {
				old.add(added.get(i));
			}

		}

	}

	public static ArrayList<Material> getLevelList(int level, ArrayList<Material> list) {
		ArrayList<Material> beginningList = new ArrayList<Material>();
		ArrayList<Material> netherList = new ArrayList<Material>();
		ArrayList<Material> hardList = new ArrayList<Material>();
		ArrayList<Material> endList = new ArrayList<Material>();

		for (Material e : Material.values()) {
			String str = e.toString().toLowerCase();

			if (e.isBlock() && !(str.contains("prismarine") || str.contains("end") || str.contains("beacon")
					|| str.contains("spawner") || str.contains("terracotta") || str.contains("wall_banner")
					|| str.contains("coral") || str.contains("shulker") || str.contains("barrier")
					|| str.contains("dragon") || str.contains("sea_") || str.contains("head") || str.contains("skull")
					|| str.contains("command") || str.contains("structure") || str.contains("infested")
					|| str.contains("purpur") || str.contains("wither") || str.contains("chorus") || str.contains("air")
					|| str.contains("stem") || str.contains("beehive") || str.contains("nether")
					|| str.contains("quartz") || str.contains("chipped") || str.contains("conduit")
					|| str.contains("damaged") || str.contains("emerald_block") || str.contains("diamond_block")
					|| str.contains("ice") || str.contains("allum") || str.contains("enchanting")
					|| str.contains("juke") || str.contains("honey") || str.contains("moving")
					|| str.contains("petrified") || str.contains("potatoes") || str.contains("glow")
					|| str.contains("lamp") || str.contains("wall_") || str.contains("egg") || str.contains("sponge")
					|| str.equals("water") || str.contains("slime") || str.contains("red_sand") || str.contains("comparator"))) {
				beginningList.add(e);
			}

		}

		for (Material e : Material.values()) {
			String str = e.toString().toLowerCase();

			if (e.isBlock() && (str.contains("nether") || str.contains("quartz") || str.contains("glow")
					|| str.contains("enchanting") || str.contains("juke") || str.contains("lamp") || str.contains("comparator"))) {
				netherList.add(e);
			}

		}

		for (Material e : Material.values()) {
			String str = e.toString().toLowerCase();

			if (e.isBlock() && (str.contains("diamond_block") || str.contains("emerald_block")
					|| str.contains("prismarine") || str.contains("sponge") || str.contains("allum")
					|| str.contains("conduit") || str.contains("egg") || str.contains("honey") || str.contains("coral")
					|| str.contains("slime"))) {
				hardList.add(e);
			}

		}

		for (Material e : Material.values()) {
			String str = e.toString().toLowerCase();

			if (e.isBlock() && (str.contains("end") || str.contains("purpur") || str.contains("chorus")
					|| str.contains("shulker") || str.contains("dragon"))) {
				endList.add(e);
			}

		}

		if (level < 5) {
			add(list, beginningList);
		} else if (level == 5) {
			remove(list, list);
			add(list, netherList);
		} else if (level < 9) {
			add(list, beginningList);
			add(list, netherList);
		} else if (level < 13) {
			add(list, beginningList);
			add(list, netherList);
			add(list, hardList);
		} else {
			remove(list, list);
			add(list, endList);
		}

		return list;
	}
}
