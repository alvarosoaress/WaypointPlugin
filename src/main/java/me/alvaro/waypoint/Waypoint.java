package me.alvaro.waypoint;

import me.alvaro.waypoint.commands.WaypointAdd;
import org.bukkit.plugin.java.JavaPlugin;

public final class Waypoint extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Ta carregado patrao!");
        getCommand("waypointAdd").setExecutor(new WaypointAdd());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Ta desligado patrao!");
    }
}
