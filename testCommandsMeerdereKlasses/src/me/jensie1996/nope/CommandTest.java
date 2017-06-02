package me.jensie1996.nope;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTest implements CommandExecutor {
	
	public CommandTest(testClass plugin){}
	
    public boolean onCommand(CommandSender zender, Command cmd, String alias, String[] args) {
        
    	Player player = (Player) zender;
        player.sendMessage("You executed a basic command");

        return true;
    }
}
