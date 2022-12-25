package me.alvaro.waypoint.commands;

import me.alvaro.waypoint.Utility.Utility;
import me.alvaro.waypoint.Utility.WayUtility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class WaypointAdd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
                WayUtility.wayAdd(p,args,1,4);
                return true;
            }
        return false;
        }
    }