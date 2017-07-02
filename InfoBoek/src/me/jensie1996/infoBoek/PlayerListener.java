package me.jensie1996.InfoBoek;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/*
Created By: MLGEditz
*/
public class PlayerListener implements Listener {
	//mainclass inladen
	InfoBoekClass plugin;
	//file config aanmaken
	FileConfiguration config;
  
	//gegevens inladen van de MainClass
	public PlayerListener(InfoBoekClass plugin)
	{
		//gegevens krijgen van de mainclass
		this.plugin = plugin;
		//config verkrijgen van de MainClass
	    this.config = plugin.getConfig();
	}
	
	//klasse inladen van MakeBook
	MakeBook maakBoek = new MakeBook();
	  
	//als de speler joint wordt dit geactiveerd
	@EventHandler
	 public boolean onJoin(PlayerJoinEvent e)
	 {
	   Player speler = e.getPlayer();
	   if ((speler instanceof Player)) {
	     //nakijken als het boekje niet iedere keer dat iemand joint moet gegeven worden.
	     if (!this.config.getBoolean("iedere-join"))
	     {
	       if (!speler.hasPlayedBefore()) { //nakijken als de speler nog nooit heeft gejoind.
	         this.maakBoek.makeBook(this.config, speler); //maken van het boek
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
