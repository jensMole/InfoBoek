package me.jensie1996.infoBoek;

import org.bukkit.event.Listener;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

	InfoBoekClass plugin;
	  FileConfiguration config;
	  
	  public PlayerListener(InfoBoekClass plugin)
	  {
	    this.plugin = plugin;
	    this.config = plugin.getConfig();
	  }
	  
	  MakeBook maakBoek = new MakeBook();
	  
	  @EventHandler
	  public boolean onJoin(PlayerJoinEvent e)
	  {
	    Player speler = e.getPlayer();
	    if ((speler instanceof Player)) {
	      if (!this.config.getBoolean("iedere-join"))
	      {
	        if (!speler.hasPlayedBefore()) {
	          this.maakBoek.makeBook(this.config, speler);
	        }
	      }
	      else
	      {
	        speler.getInventory().remove(Material.WRITTEN_BOOK);
	        
	        this.maakBoek.makeBook(this.config, speler);
	      }
	    }
	    return false;
	  }
	
}
