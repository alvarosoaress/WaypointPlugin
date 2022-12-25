package me.alvaro.waypoint.commands;

import me.alvaro.waypoint.Utility.WayUtility;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WaypointAddCurrent implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // MODULARIZAR FUNÇÃO DE ADICIONAR / REMOVER / LISTAR / BOOK
        if (sender instanceof Player) {
            Player p = (Player) sender;

            return WayUtility.wayAddCurrent(p, args, 1, 1, args[0]);
        }
        return false;
    }
}
