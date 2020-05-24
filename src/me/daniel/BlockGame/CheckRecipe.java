package me.daniel.BlockGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CheckRecipe {
	
	public static Map<ItemStack, Integer> getRecipe(Material block){
		ItemStack stack = new ItemStack(block);
		ShapedRecipe recipe = (ShapedRecipe) Bukkit.getRecipesFor(stack).get(0);
		Map<ItemStack, Integer> materials = new HashMap<ItemStack, Integer>();
		for(Entry<Character, ItemStack> entry : recipe.getIngredientMap().entrySet()) {
			ItemStack item = entry.getValue();
			if(item != null) {
				materials.putIfAbsent(item, 0);
				for(Entry<ItemStack, Integer> e : materials.entrySet()) {
					if(e.getKey().toString().equals(item.toString())) {
						e.setValue(e.getValue() + 1);
					}
				}
			}
			
			
			
		}
		return materials;
	}
	

}
