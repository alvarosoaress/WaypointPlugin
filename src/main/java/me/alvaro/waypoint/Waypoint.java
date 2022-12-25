package me.alvaro.waypoint;

import me.alvaro.waypoint.commands.WaypointAdd;
import me.alvaro.waypoint.commands.WaypointList;
import org.bukkit.plugin.java.JavaPlugin;

public final class Waypoint extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Ta carregado patrao!");
        getCommand("waypointAdd").setExecutor(new WaypointAdd());
        getCommand("waypointList").setExecutor(new WaypointList());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Ta desligado patrao!");
    }
}
