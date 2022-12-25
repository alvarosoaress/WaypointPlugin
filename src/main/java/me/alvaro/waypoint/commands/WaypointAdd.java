package me.alvaro.waypoint.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WaypointAdd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Utility util = new Utility();

        if (sender instanceof Player) {
            Player p = (Player) sender;

            // testar o número de argumentos passados
            if (args.length > 0 && args.length < 4) {
                p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Poucos argumentos para o comando!");
                return false;
            } else if (args.length > 4) {
                p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Muitos argumentos para o comando!");
                return false;
            } else {
                // testar para ver se X Y Z passados são númericos
                util.isNum(args, p);

                for (String qlqr : args) {
                    p.sendMessage(ChatColor.WHITE + "------------------------------");
                    p.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Nome: " + args[0]);
                    p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "X: " + args[1]);
                    p.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "Y: " + args[2]);
                    p.sendMessage(ChatColor.BLUE + ChatColor.BOLD.toString() + "Z: " + args[3]);
                    p.sendMessage(ChatColor.WHITE + "------------------------------");
                    return true;
                }
            }
        }
        return true;
    }
}
