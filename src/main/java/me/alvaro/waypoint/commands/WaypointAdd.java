package me.alvaro.waypoint.commands;

import me.alvaro.waypoint.Utility.Utility;
import me.alvaro.waypoint.Utility.WayUtility;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class WaypointAdd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (Utility.isNum(args, p)) {
                return false;
            }
            return WayUtility.wayAdd(p, args, 1, 4);
        }
        return false;
    }
}