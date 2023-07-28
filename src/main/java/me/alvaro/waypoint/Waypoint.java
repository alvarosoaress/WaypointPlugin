package me.alvaro.waypoint;

import me.alvaro.waypoint.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Waypoint extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Ta carregado patrao!");
        getCommand("waypointAdd").setExecutor(new WaypointAdd());
        getCommand("waypointList").setExecutor(new WaypointList());
        getCommand("waypointBook").setExecutor(new WaypointBook());
        getCommand("waypointRemove").setExecutor(new WaypointRemove());
        getCommand("waypointAddCurrent").setExecutor(new WaypointAddCurrent());
        getCommand("waypointTeleport").setExecutor(new WaypointTeleport());
        getCommand("waypointTeleport").setTabCompleter(new WaypointTeleportTabCompletion());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Ta desligado patrao!");
    }
}
