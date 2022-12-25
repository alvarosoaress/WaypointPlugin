package me.alvaro.waypoint.Utility;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utility {
    public static boolean isNum(String[] args, Player p) {
        if(args.length <= 0){
            p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Preencha todos os parametros!");
            return false;
        }
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

    // verificar se o arquivo JSON está vazio
    // se estiver vazio irá escrever um  []
    // para poder funcionar
    // motivo? eu não faço ideia, só sei q funciona
    public static void verifyJSON(String path) throws IOException {
        FileWriter file = new FileWriter(path, true);
        FileReader reader = new FileReader(path);

        if (reader.read() == -1) {
            file.write("[]");
            file.close();
        }
    }

    // função para adicionar coordenada ao JSON
    public static boolean addJson(String nome, int x, int y, int z, World w, Player p) throws IOException {
        verifyJSON("plugins//coordsList.json");
        FileWriter file = new FileWriter("plugins//coordsList.json", true);

        JSONParser parser = new JSONParser();
        try {
            JSONArray coordsList = (JSONArray) parser.parse(new FileReader("plugins//coordsList.json"));

            // verificando se o nome passado já existe no JSON
            for (Object obj: coordsList) {
                JSONObject coords = (JSONObject) obj;
                if(coords.get("Nome").toString() == nome){
                    p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Esse nome de coordenada já existe!");
                    return false;
                }
            }

            JSONObject coords = new JSONObject();

            coords.put("Nome", nome);
            coords.put("X", x);
            coords.put("Y", y);
            coords.put("Z", z);
            coords.put("W", w.toString());
            coords.put("ID", coordsList.size());

            coordsList.add(coords);

            FileOutputStream writer = new FileOutputStream("plugins//coordsList.json");
            writer.write(("").getBytes());

            file.write(coordsList.toJSONString());

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static void printCoords(Player p, JSONArray coordsList) {
        for (Object obj: coordsList) {
            JSONObject coords = (JSONObject) obj;
            p.sendMessage(ChatColor.WHITE + "-".repeat(coords.get("Nome").toString().length() * 3));
            p.sendMessage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Nome: " + coords.get("Nome").toString());
            p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "X: " + coords.get("X").toString());
            p.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "Y: " + coords.get("Y").toString());
            p.sendMessage(ChatColor.BLUE + ChatColor.BOLD.toString() + "Z: " + coords.get("Z").toString());
            p.sendMessage(ChatColor.DARK_BLUE + ChatColor.BOLD.toString() + "Mundo: " + coords.get("W").toString());
            p.sendMessage(ChatColor.DARK_GRAY + ChatColor.BOLD.toString() + "ID: " + coords.get("ID").toString());
            p.sendMessage(ChatColor.WHITE + "-".repeat(coords.get("Nome").toString().length() * 3));
        }

    }

    public static boolean readJson(Player p) throws IOException {
        FileWriter file = new FileWriter("plugins//coordsList.json", true);

        FileReader reader = new FileReader("plugins//coordsList.json");

        if (reader.read() < 3) {
            p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Ainda nenhuma coordenada foi salva!");
            return false;
        } else {
            JSONParser parser = new JSONParser();
            try {
                JSONArray coordsList = (JSONArray) parser.parse(new FileReader("plugins//coordsList.json"));
                printCoords(p, coordsList);

                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}