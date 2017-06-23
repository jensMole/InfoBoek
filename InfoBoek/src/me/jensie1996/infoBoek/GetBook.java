package me.jensie1996.infoBoek;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class GetBook implements CommandExecutor {

	InfoBoekClass plugin;
	  FileConfiguration config;
	  
	  public GetBook(InfoBoekClass plugin)
	  {
	    this.plugin = plugin;
	    this.config = plugin.getConfig();
	  }
	  
	  MakeBook maakBoek = new MakeBook();
	  
	  //als het command uitgevoerd is.
	  public boolean onCommand(CommandSender zender, Command cmd, String arg2, String[] args)
	  {
	    Player speler = (Player)zender;
	    
	    //boek aanmaken met behulp van de MakeBook class getbook.
	    this.maakBoek.getBook(this.config, speler);
	    
	    return true;
	  }
}