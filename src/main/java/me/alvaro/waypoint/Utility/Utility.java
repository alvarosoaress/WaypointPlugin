package me.alvaro.waypoint.Utility;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utility {
    public boolean isNum(String[] args, Player p) {
        for (int i = 1; i < 4; i++) {
            try {
                Double.parseDouble(args[i]);
            } catch (NumberFormatException e) {
                p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Passe apenas números inteiros nas coordenadas!");
                return false;
            }
        }
        return true;
    }

    public void addJson(int x, int y, int z, World w) throws IOException, ParseException {
        FileWriter file = new FileWriter("coordsList.json", false);

        JSONParser parser = new JSONParser();
        JSONObject coordsList = (JSONObject) parser.parse(new FileReader("coordsList.json"));

        JSONObject coords = new JSONObject();

        coords.put("X", x);
        coords.put("Y", y);
        coords.put("Z", z);
        coords.put("W", w.toString());

        coordsList.put(coordsList.size()+1, coords);

        file.write(coordsList.toJSONString());

        file.close();
        System.out.println("JSON Criado e atualizado");
    }
}
