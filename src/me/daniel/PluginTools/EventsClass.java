package me.daniel.PluginTools;


import org.bukkit.event.Listener;





public class EventsClass implements Listener {
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		Messages message = new Messages(block.getType().toString());
		message.intoReadable();
		if(player.getName().toLowerCase().equals("etallent323")) {
			//Bukkit.broadcastMessage("Wowww. Emma just broke a block of " + message.getMessage() + ". Everyone give her a high fucking five.");
		}
		
	}*/

	/*@EventHandler
	public void onInteract(PlayerInteractEvent event) {

		/*Action action = event.getAction();
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		if(action.equals(Action.LEFT_CLICK_BLOCK)) {
			player.sendMessage(ChatColor.RED + block.getType().toString());
		}
	}*/
	
	/*
	@EventHandler
	public void entInteract(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player ) {
			Entity entity = event.getEntity();
			Player player = (Player) event.getDamager();
			if(player.getName().toString().toLowerCase().equals("etallent323")) {
				Messages message = new Messages(entity.getType().toString());
				message.intoReadable();
				//Bukkit.broadcastMessage("Fuck off Emma. What did the " + message.getMessage() + " do to you?");
				player.sendMessage("You just lost some health for that.");
				player.setHealth(player.getHealth() - 1);
			}
			
			
			
		}
	}*/
	
	/*@EventHandler
	public void onSpawn(CreatureSpawnEvent event) {
		Entity entity = event.getEntity();
		Location spawnLoc = entity.getLocation();
		World world = entity.getWorld();
		if(!(entity instanceof Player) && !(entity.getType().toString().equals("ENDER_DRAGON")) && !(event.getSpawnReason().equals(SpawnReason.CUSTOM))) {
			for(int i = 1; i < Math.random() * 14; i++) {
				Entity newEnt = world.spawnEntity(spawnLoc, entity.getType());
				newEnt.addScoreboardTag("test");
				entity.addPassenger(newEnt);
				entity = newEnt;
			}
			
		}
		
	}

	@EventHandler
	public void noSuffo(EntityDamageEvent event) {
		if(event.getCause().equals(DamageCause.SUFFOCATION) && !(event.getEntity() instanceof Player) && event.getEntity().getScoreboardTags().toString().equals("[test]")) {
			event.setCancelled(true);
		}
	}
	*/
}
