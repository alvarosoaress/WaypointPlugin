package me.alvaro.waypoint.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class WaypointTeleport implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            // verificando se o player não passou argumentos
            if (args.length <= 0 || args[0].isBlank()) {
                p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Coordenada não encontrada!");
                return false;
            }

            try {
                waypointTp(args[0], p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    public boolean waypointTp(String argument, Player p) throws IOException {
        JSONParser parser = new JSONParser();
        int intIndex = 0;

        // verificando se o argumento passado pelo player é númerico e inteiro
        // caso não seja, assumo que é String e passo para o catch
        try {
            intIndex = Integer.parseInt(argument);

            // -> caso onde argumento passado é númerico
            try {
                JSONArray coordsList = (JSONArray) parser.parse(new FileReader("plugins//coordsList.json"));

                // verificando se o numero está dentro do size do array
                if (intIndex > coordsList.size() || intIndex < 0) {
                    p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Esse ID de coordenada não existe!");
                    return false;
                } else {
                    JSONObject coords = (JSONObject) coordsList.get(intIndex);

                    // convertendo double X Y Z baseado no obj
                    // necessário, pois, valor de X Y Z é Long por default
                    double X = Double.parseDouble((String.valueOf(coords.get("X"))));
                    double Y = Double.parseDouble((String.valueOf(coords.get("Y"))));
                    double Z = Double.parseDouble((String.valueOf(coords.get("Z"))));

                    Location location = new Location(Bukkit.getWorld((String) coords.get("W")), X, Y, Z);

                    p.teleport(location);

                    p.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "Se teleportando para " + coords.get("Nome") + " !");
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return true;

        } catch (NumberFormatException e) {

            // -> caso onde argumento passado é String (nome da coordenada)
            try {
                JSONArray coordsList = (JSONArray) parser.parse(new FileReader("plugins//coordsList.json"));

                // encontrando Obj onde Nome seja igual ao argumento passado
                for (Object obj : coordsList) {
                    JSONObject coords = (JSONObject) obj;

                    if (String.valueOf(coords.get("Nome")).equals(argument)) {
                        double X = Double.parseDouble((String.valueOf(coords.get("X"))));
                        double Y = Double.parseDouble((String.valueOf(coords.get("Y"))));
                        double Z = Double.parseDouble((String.valueOf(coords.get("Z"))));

                        Location location = new Location(Bukkit.getWorld((String) coords.get("W")), X, Y, Z);

                        p.teleport(location);

                        p.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "Se teleportando para " + coords.get("Nome") + " !");

                        return true;
                    }
                }
            } catch (IOException error) {
                e.printStackTrace();
            } catch (ParseException error) {
                throw new RuntimeException(error);
            }

            p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Coordenada não encontrada!");
            return false;
        }
    }
}