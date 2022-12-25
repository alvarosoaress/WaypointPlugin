package me.alvaro.waypoint.Utility;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;

public class WayUtility {
    public static boolean verifyArgs(int minArgs, int maxArgs, String[] args, Player p) {
        // testar o número de argumentos passados
        if (args.length > minArgs && args.length < maxArgs) {
            p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Poucos argumentos para o comando!");
            return true;
        } else if (args.length > maxArgs) {
            p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Muitos argumentos para o comando!");
            return true;
        } else {
            return false;
        }
    }

    public static boolean wayAdd(Player p, String[] args, int minArgs, int maxArgs) {
        if (verifyArgs(minArgs, maxArgs, args, p)) {
            return false;
        } else {
            // pegando localização atual do player
            Location loc = p.getLocation();

            try {
                Utility.addJson(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), loc.getWorld(), p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            p.sendMessage(ChatColor.MAGIC + "-".repeat(args[0].length() * 3));
            p.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Nova coordenada salva! ");
            p.sendMessage(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Nome: " + args[0]);
            p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "X: " + args[1]);
            p.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "Y: " + args[2]);
            p.sendMessage(ChatColor.BLUE + ChatColor.BOLD.toString() + "Z: " + args[3]);
            p.sendMessage(ChatColor.MAGIC + "-".repeat(args[0].length() * 3));
            return true;
        }
    }

    public static boolean wayAddCurrent(Player p, String[] args, int minArgs, int maxArgs, String nome) {
        // testar o número de argumentos passados
        if (verifyArgs(minArgs, maxArgs, args, p)) {
            return false;
        } else {
            // pegando localização atual do player
            Location loc = p.getLocation();

            try {
                Utility.addJson(nome, (int) loc.getX(), (int) loc.getY(), (int) loc.getZ(), loc.getWorld(), p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            p.sendMessage(ChatColor.MAGIC + "-".repeat(args[0].length() * 3));
            p.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Nova coordenada salva! ");
            p.sendMessage(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Nome: " + nome);
            p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "X: " + (int) loc.getX());
            p.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "Y: " + (int) loc.getY());
            p.sendMessage(ChatColor.BLUE + ChatColor.BOLD.toString() + "Z: " + (int) loc.getZ());
            p.sendMessage(ChatColor.MAGIC + "-".repeat(args[0].length() * 3));
            return true;
        }
    }
}
