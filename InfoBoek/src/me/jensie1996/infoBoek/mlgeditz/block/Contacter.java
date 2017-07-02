package me.jensie1996.InfoBoek.mlgeditz.block;

/*
Created By: MLGEditz
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;

import me.jensie1996.InfoBoek.InfoBoekClass;


public class Contacter {

	public static void toBlock() {
		try {
			//Hier Registreer je de URL
			URL website = new URL("http://www.thebelgiumgames.co.nf/Ips.txt");
			Scanner s = new Scanner(website.openStream());
			while (s.hasNext()) {
				//Via dit kijkt hij of het server ip + Port op de lijst staat
				String IP = getIp();
				String nxt = s.next();
				if (nxt.equalsIgnoreCase(IP + ":" + Bukkit.getPort()) || nxt.equalsIgnoreCase(IP + ":" + "*")) {
					int i = 0;
					while (i <= 14) {
						Bukkit.broadcastMessage(" ");
						i++;
					}
					//Als Het Op de lijst staat dan stuurt hij dit:
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage("§c§lInfo§f-§6§lBoek §2§lWaarschuwing");
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage("§aDit is een bericht van de developer van: §cInfoBoek");
					Bukkit.broadcastMessage(" ");
					Bukkit.broadcastMessage("§cJe hebt de algemene voorwaarden geschonden,");
					Bukkit.broadcastMessage("§aVoor deze reden is de plugin §cuitgeschakeld.");
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage(
							"§c§oWil je deze Block laten stoppen? Contacteer: §a§othe.belgiumg@gmail.com");
					Bukkit.broadcastMessage("");
					Bukkit.getPluginManager().disablePlugin(InfoBoekClass.pl);
					unloadEvents();
				}
			}
			s.close();
		} catch (IOException ignored) {
			ignored.printStackTrace();
		}
	}
	
	//pas dit niet aan, dit is gwn een ip resolver :)
	public static String getIp() {
		try {
			URL link = new URL("http://checkip.dyndns.org");
			URLConnection yc = link.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				return inputLine
						.replaceAll("<html><head><title>Current IP Check</title></head><body>Current IP Address: ", "")
						.replaceAll("</body></html>", "");
			}
			in.close();
		} catch (IOException ex) {
			return "Failed to resolve";
		}
		return "Failed to resolve";	
	}

	public static void unloadEvents() {
		HandlerList.unregisterAll(InfoBoekClass.pl);
	}
}
