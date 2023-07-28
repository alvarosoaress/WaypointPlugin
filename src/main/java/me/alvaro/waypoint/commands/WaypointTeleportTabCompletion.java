package me.alvaro.waypoint.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WaypointTeleportTabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        // verificando se o player não passou argumentos
        if (args.length == 1) {
            List<String> WaypointNames = new ArrayList<>();
            List<String> completions = new ArrayList<>();

            JSONParser parser = new JSONParser();

            try {
                JSONArray coordsList = (JSONArray) parser.parse(new FileReader("plugins//coordsList.json"));

                // lendo todos os nomes de coordenadas e os guardando em WaypointNames
                for (Object obj : coordsList) {
                    JSONObject coords = (JSONObject) obj;

                    WaypointNames.add(String.valueOf(coords.get("Nome")));
                }

                // verificando qual nome de coord contem o argumento passado
                // qual nome tiver será adiconado à lista
                // (auto-complete)
                for (String name : WaypointNames){
                    if(name.toLowerCase().contains(args[0].toLowerCase())){
                        completions.add(name);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            return completions;
        }
        return null;
    }
};