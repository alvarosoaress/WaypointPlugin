package me.alvaro.waypoint.Utility;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utility {
    public boolean isNum(String[] args, Player p){
        for (int i = 1; i < 4; i++) {
            try {
                Double.parseDouble(args[i]);
            } catch (NumberFormatException e) {
                p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Passe apenas números inteiros nas coordenadas!");
                return false;
            }
        }
        return true;
    }
}
