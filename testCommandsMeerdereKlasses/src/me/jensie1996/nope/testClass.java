package me.jensie1996.nope;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class testClass extends JavaPlugin{

	@Override
	public void onEnable(){
		//Een file daar de data van krijgen wat in de config.yml staat.
		File file = new File(getDataFolder(), "config.yml");
		//als de file nog niet bestaat eentje aanmaken.
		if(!(file.exists())){
			getLogger().info("Config.yml niet gevonden, creeren van config.yml");
			saveDefaultConfig(); //config genereren.
		}
		
		//command in andere klasse opvragen.
		getCommand("test").setExecutor(new CommandTest(this));
		
		Bukkit.getServer().getPluginManager().registerEvents(new BlockListener(), this);
		
		getCommand("lol").setExecutor(new LolTest(this));
		
	}
	
	public boolean onCommand(CommandSender zender, Command cmd, String label, String[] args){
		
		//als je pl probeerd te doen
		if(cmd.getName().equalsIgnoreCase("pl")) {
			
			if(!(zender instanceof Player)){
				zender.sendMessage("Je bent geen speler.");
				return true;
			}
			
			Player speler = (Player) zender;
			
			//zend de speler een bericht met het bericht van in de config.
			speler.sendMessage(ChatColor.RED + this.getConfig().getString("text"));
			
			return true;	
		}
		return false;
	}	
}
