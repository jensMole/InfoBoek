package me.jensie1996.infoBoek;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class InfoBoekClass extends JavaPlugin {

	File file = new File(getDataFolder(), "config.yml");
	  
	  public void onEnable()
	  {
		//tekst als server opstart
	    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	    console.sendMessage(ChatColor.GREEN + "InfoBoek has been activated");
	    //Config file aanmaken als deze nog niet bestaat.
	    if (!this.file.exists())
	    {
	      getLogger().info("Config.yml niet gevonden, creeren van config.yml");
	      saveDefaultConfig(); //aanmaken
	    }
	    //aanmaken van de listener om na te kijken als de speler joint.
	    Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
	    
	    //command om het boek ingame te krijgen.
	    getCommand("ibgetbook").setExecutor(new GetBook(this));
	  }
	  
	  //reload command (TO DO)
	  public boolean onCommand(CommandSender zender, Command cmd, String label, String[] args)
	  {
	    if ((cmd.getName().equals("ibreload")) && ((zender instanceof Player)))
	    {
    	  saveDefaultConfig();
	    	
	      reloadConfig();
	      
	      zender.sendMessage(ChatColor.GREEN + "Config herladen.");
	      
	      return true;
	    }
	    return false;
	  }
}