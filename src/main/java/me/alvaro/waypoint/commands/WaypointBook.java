package me.alvaro.waypoint.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WaypointBook implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            // criando o livro
            Book book = new Book();

            // definindo titulo e autor do livro
            book.setTitle(ChatColor.GREEN + ChatColor.BOLD.toString() + "Lista de Waypoints");
            book.setAuthor(ChatColor.RED + ChatColor.BOLD.toString() + p.getName());

            // criando páginas pegando informações salvas do JSON
            try {
                book.writeCoords(p);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            // adicionando page ao meta
            book.addPage();

            // adicionando meta ao book
            book.addInfo();

            // dando o livro para o player
            book.giveBook(p);

            return true;
        }
        return false;
    }

    public class Book {
        String title;
        String author;
        String currentPage = "";
        int numPages = 0;
        int numLines = 0;

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public int getNumPages() {
            return numPages;
        }

        public void setTitle(String title) {
            this.title = title;
            bookMeta.setTitle(title);
        }

        public void setAuthor(String author) {
            this.author = author;
            bookMeta.setAuthor(author);
        }

        public void setNumPages(int numPages) {
            this.numPages = numPages;
        }

        public void addPage() {
            bookMeta.addPage(currentPage);
            ++numPages;
        }

        public void addToPage(String line) {
            if (numLines == 13) {
                addPage();
                this.currentPage = "";
                this.numLines = 0;
            } else if (numLines == 0) {
                this.currentPage = this.currentPage + title + "\n";
                this.currentPage = this.currentPage + line + "\n";
            } else {
                this.currentPage = this.currentPage + line + "\n";
            }

            ++numLines;
        }

        public void addInfo() {
            book.setItemMeta(bookMeta);
        }

        public void giveBook(Player p) {
            if (p.getInventory().firstEmpty() != -1) {
                p.getInventory().addItem(book);
            } else {
                p.sendMessage(p.getName() + " seu inventário tá cheio!");
            }
        }

        public boolean writeCoords(Player p) throws IOException, ParseException {
            FileWriter file = new FileWriter("plugins//coordsList.json", true);

            FileReader reader = new FileReader("plugins//coordsList.json");

            if (reader.read() < 3) {
                p.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Ainda nenhuma coordenada foi salva!");
                return false;
            } else {
                JSONParser parser = new JSONParser();
                JSONArray coordsList = (JSONArray) parser.parse(new FileReader("plugins//coordsList.json"));
                for (Object obj : coordsList) {
                    JSONObject coords = (JSONObject) obj;
                    addToPage(ChatColor.GOLD + ChatColor.BOLD.toString() + "Nome: " + coords.get("Nome").toString());
                    addToPage(ChatColor.RED + ChatColor.BOLD.toString() + "X: " + coords.get("X").toString());
                    addToPage(ChatColor.GREEN + ChatColor.BOLD.toString() + "Y: " + coords.get("Y").toString());
                    addToPage(ChatColor.BLUE + ChatColor.BOLD.toString() + "Z: " + coords.get("Z").toString());
               //     addToPage(ChatColor.DARK_BLUE + ChatColor.BOLD.toString() + "Mundo: " + coords.get("W").toString());
                    addToPage(ChatColor.DARK_GRAY + ChatColor.BOLD.toString() + "ID: " + coords.get("ID").toString());
                    addToPage(ChatColor.WHITE + "-".repeat(coords.get("Nome").toString().length() * 3));
                }
                file.close();

                return true;
            }
        }
    }
}
