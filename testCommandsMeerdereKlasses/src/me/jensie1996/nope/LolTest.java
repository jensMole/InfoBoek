package me.jensie1996.nope;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LolTest implements CommandExecutor {

	public LolTest(testClass plugin){}
	
	public boolean onCommand(CommandSender zender, Command arg1, String arg2, String[] arg3) {
		Player player = (Player) zender;
        player.sendMessage("You executed a basic command");

        return true;
	}

}
