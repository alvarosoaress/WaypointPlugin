package me.alvaro.waypoint.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static me.alvaro.waypoint.Utility.Utility.verifyJSON;

public class WaypointRemove implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            int intIndex = 0;

            try {
                intIndex = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Passe apenas números inteiros no ID!");
                return false;
            }

            try {
                waypointRm(intIndex, p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public boolean waypointRm(int intIndex, Player p) throws IOException {
        verifyJSON("plugins//coordsList.json");
        FileWriter file = new FileWriter("plugins//coordsList.json", true);

        JSONParser parser = new JSONParser();

        try {
            JSONArray coordsList = (JSONArray) parser.parse(new FileReader("plugins//coordsList.json"));

            if (intIndex > coordsList.size() || intIndex < coordsList.size()) {
                p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Esse id de coordenada não existe!");
                return false;
            } else {
                removeAux(intIndex, coordsList);

                p.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "Coordenada removida com sucesso!");

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

    // a seguinte função é necessária para evitar o Exception
    // caso implementado o seguite bloco no codigo irá dar erro
    // pelo fator do index estar entre try catch
    // podendo index exisitr ou não, podendo ser out of bounds ou não
    public void removeAux(int index, JSONArray coordsList) {
        if (index >= coordsList.size()) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        coordsList.remove(index);
    }
}