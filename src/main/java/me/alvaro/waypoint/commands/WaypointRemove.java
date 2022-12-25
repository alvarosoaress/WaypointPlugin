package me.alvaro.waypoint.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WaypointRemove implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            // verificando se o player não passou argumentos
            if(args[0].isBlank()){
                p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Passe apenas números inteiros no ID!");
                return false;
            }

            try {
                waypointRm(args[0], p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    public boolean waypointRm(String index, Player p) throws IOException {
        FileWriter file = new FileWriter("plugins//coordsList.json", true);

        JSONParser parser = new JSONParser();
        int intIndex = 0;

        // verificando se o argumento passado pelo player é númerico inteiro
        try {
            intIndex = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Passe apenas números inteiros no ID!");
            return false;
        }

        try {
            JSONArray coordsList = (JSONArray) parser.parse(new FileReader("plugins//coordsList.json"));

            // verificando se o numero está dentro do size do array
            if (intIndex > coordsList.size() || intIndex < 0) {
                p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Esse id de coordenada não existe!");
                return false;
            } else {
                JSONObject coords = (JSONObject) coordsList.get(intIndex);

                p.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "Coordenada "+coords.get("Nome")+" removida com sucesso!");

                // efetivamente removendo a coordenada
                coordsList.remove(intIndex);

                FileOutputStream writer = new FileOutputStream("plugins//coordsList.json");
                writer.write(("").getBytes());

                file.write(coordsList.toJSONString());

                file.close();
            }
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}