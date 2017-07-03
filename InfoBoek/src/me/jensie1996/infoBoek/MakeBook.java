package me.jensie1996.infoBoek;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

/*
Created By: JensM TBG
*/
public class MakeBook {

	//boek aanmaken.
	public boolean makeBook(FileConfiguration config, Player speler)
	  {
		//boek aanmaken
	    ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
	    
	    //metadata verkrijgen van het boek.
	    BookMeta bm = (BookMeta)book.getItemMeta();
	    
	    bm.setTitle(config.getString("Title"));
	    bm.setAuthor(config.getString("Author"));
	    
	    //lijst voor al de pagina's
	    List<String> lijstPaginas = config.getStringList("Book.Text");
	    for (String tekst : lijstPaginas)
	    {
	      //tekst omzetten met kleur.
	      String coloredText = ChatColor.translateAlternateColorCodes('&', tekst);
	      
	      //met een array de verschillende teksten er in zetten.
	      bm.addPage(new String[] { coloredText });
	    }
	    book.setItemMeta(bm);
	    
	    //slotnummer verkrijgen waar het boekje moet komen.
	    int slotnummer = config.getInt("Slot");
	    
	    slotnummer--;
	    if ((slotnummer <= 0) || (slotnummer >= 10)) //nakijken voor als het de juiste slot is.
	    {
	      ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	      console.sendMessage(ChatColor.RED + "Je hebt een verkeerd slot nummer opgegeven!! Dit moet tussen 1 en 9 liggen.");
	      speler.sendMessage(ChatColor.RED + "Je hebt een verkeerd slot nummer opgegeven!! Dit moet tussen 1 en 9 liggen.");
	      return false;
	    }
	    //geven van het boek.
	    speler.getInventory().setItem(slotnummer, book);
	    
	    return true;
	  }
	  
	  //verkrijgen van het boek met behulp van het command.
	  public boolean getBook(FileConfiguration config, Player speler)
	  {
	    if ((speler instanceof Player))
	    {
	      if (speler.hasPermission("ibgetbook"))
	      {
	        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
	        
	        BookMeta bm = (BookMeta)book.getItemMeta();
	        
	        bm.setTitle(config.getString("Title"));
	        bm.setAuthor(config.getString("Author"));
	        
	        List<String> lijstPaginas = config.getStringList("Book.Text");
	        for (String tekst : lijstPaginas)
	        {
	          String coloredText = ChatColor.translateAlternateColorCodes('&', tekst);
	          
	          bm.addPage(new String[] { coloredText });
	        }
	        book.setItemMeta(bm);
	        
	        speler.getInventory().addItem(new ItemStack[] { book });
	        
	        return true;
	      }
	      speler.sendMessage(ChatColor.RED + "Je hebt geen permissions. Als je denkt dat dit niet klopt vraag aan een staflid om je de perm ibgetbook te geven.");
	      return true;
	    }
	    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	    console.sendMessage(ChatColor.RED + "Kan alleen uitgevoerd worden in het spel zelf.");
	    return true;
	  }
	
}
