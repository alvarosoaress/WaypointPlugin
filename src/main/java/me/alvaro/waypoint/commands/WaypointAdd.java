package me.alvaro.waypoint.commands;

import me.alvaro.waypoint.Utility.Utility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class WaypointAdd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            // testar o número de argumentos passados
            if (args.length > 0 && args.length < 4) {
                p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Poucos argumentos para o comando!");
                return false;
            } else if (args.length > 4) {
                p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Muitos argumentos para o comando!");
                return false;
            } else if (!(Utility.isNum(args, p))) {
                // testar para ver se X Y Z passados são númericos
            } else {
                // pegando localização atual do player
                Location loc = p.getLocation();
//
//                try {
//                    Utility.addJson((int) loc.getX(), (int) loc.getY(), (int) loc.getZ(), loc.getWorld());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }

                try {
                    Utility.addJson(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]),Integer.parseInt(args[3]),loc.getWorld());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                p.sendMessage(ChatColor.WHITE + "-".repeat(args[0].length() * 3));
                p.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "\tNova coordenada salva! ");
                p.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Nome: " + args[0]);
                p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "X: " + args[1]);
                p.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "Y: " + args[2]);
                p.sendMessage(ChatColor.BLUE + ChatColor.BOLD.toString() + "Z: " + args[3]);
                p.sendMessage(ChatColor.WHITE + "-".repeat(args[0].length() * 3));
                return true;
            }
        }
        return true;
    }
}
