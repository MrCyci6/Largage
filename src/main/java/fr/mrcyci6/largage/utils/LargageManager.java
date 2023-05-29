package fr.mrcyci6.largage.utils;

import fr.mrcyci6.largage.Largage;
import fr.mrcyci6.largage.listeners.PlayerListener;
import fr.mrcyci6.largage.status.ArgentStatus;
import fr.mrcyci6.largage.status.ItemStatus;
import fr.mrcyci6.largage.status.PointStatus;
import fr.mrcyci6.largage.taks.ArgentTask;
import fr.mrcyci6.largage.taks.ItemTask;
import fr.mrcyci6.largage.taks.PointTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LargageManager {

    public static List<Location> iChestList = new ArrayList<>();
    public static List<Location> aChestList = new ArrayList<>();
    public static List<Location> pChestList = new ArrayList<>();
    private static int getRandomCord(int cord1, int cord2) {
        int min = Math.min(cord1, cord2);
        int max = Math.max(cord1, cord2);

        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
    
    public static void removeChest(List<Location> chestList) {
        chestList.forEach(chest -> {
            Chest coffre = (Chest) chest.getBlock().getState();
            Inventory inventory = coffre.getInventory();
            inventory.clear();
            chest.getBlock().setType(Material.AIR);
            chestList.remove(chest);
        });
    }

    public static boolean start(Player author, String event) {
        switch (event) {
            case "item":
                if(Largage.getInstance().isItemStatus(ItemStatus.ENABLED)) {
                    author.sendMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("deja-lance").replaceAll("%TYPE%", event).replaceAll("&", "§"));
                    return false;
                }

                YamlConfiguration yml = YamlConfiguration.loadConfiguration(Largage.getInstance().location);

                if(yml.get("location.item.pos1") == null || yml.get("location.item.pos2") == null) {
                    author.sendMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("non-zone").replaceAll("&", "§"));
                    return false;
                }

                for(int i = 0; i < 2; i++) {
                    Location pos1 = (Location) yml.get("location.item.pos1");
                    Location pos2 = (Location) yml.get("location.item.pos2");

                    int X = getRandomCord(pos1.getBlockX(), pos2.getBlockX());
                    int Z = getRandomCord(pos1.getBlockZ(), pos2.getBlockZ());
                    int Y = author.getWorld().getHighestBlockYAt(X, Z);
                    while (author.getWorld().getBlockAt(X, Y, Z).getType() != Material.AIR) {
                        Y++;
                    }

                    Block block = author.getWorld().getBlockAt(X, Y, Z);
                    if(block.getType().equals(Material.AIR)) {
                        block.setType(Material.CHEST);
                        Location chestLoc = new Location(author.getWorld(), X, Y, Z);
                        iChestList.add(chestLoc);
                    }
                }
                ItemTask task = new ItemTask(Largage.getInstance());
                task.runTaskTimer(Largage.getInstance(), 0, 20);
                Largage.getInstance().setItemStatus(ItemStatus.ENABLED);
                return true;
            case "argent":
                if(Largage.getInstance().isArgentStatus(ArgentStatus.ENABLED)) {
                    author.sendMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("deja-lance").replaceAll("%TYPE%", event).replaceAll("&", "§"));
                    return false;
                }

                YamlConfiguration ymll = YamlConfiguration.loadConfiguration(Largage.getInstance().location);

                if(ymll.get("location.argent.pos1") == null || ymll.get("location.argent.pos2") == null) {
                    author.sendMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("non-zone").replaceAll("&", "§"));
                    return false;
                }

                for(int i = 0; i < 2; i++) {
                    Location pos1 = (Location) ymll.get("location.argent.pos1");
                    Location pos2 = (Location) ymll.get("location.argent.pos2");

                    int X = getRandomCord(pos1.getBlockX(), pos2.getBlockX());
                    int Z = getRandomCord(pos1.getBlockZ(), pos2.getBlockZ());
                    int Y = author.getWorld().getHighestBlockYAt(X, Z);
                    while (author.getWorld().getBlockAt(X, Y, Z).getType() != Material.AIR) {
                        Y++;
                    }

                    Block block = author.getWorld().getBlockAt(X, Y, Z);
                    if(block.getType().equals(Material.AIR)) {
                        block.setType(Material.CHEST);
                        Location chestLoc = new Location(author.getWorld(), X, Y, Z);
                        aChestList.add(chestLoc);
                    }
                }
                ArgentTask tasks = new ArgentTask(Largage.getInstance());
                tasks.runTaskTimer(Largage.getInstance(), 0, 20);
                Largage.getInstance().setArgentStatus(ArgentStatus.ENABLED);
                return true;
            case "point":
                if(Largage.getInstance().isPointStatus(PointStatus.ENABLED)) {
                    author.sendMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("deja-lance").replaceAll("%TYPE%", event).replaceAll("&", "§"));
                    return false;
                }

                YamlConfiguration ymlll = YamlConfiguration.loadConfiguration(Largage.getInstance().location);

                if(ymlll.get("location.point.pos1") == null || ymlll.get("location.point.pos2") == null) {
                    author.sendMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("non-zone").replaceAll("&", "§"));
                    return false;
                }

                for(int i = 0; i < 2; i++) {
                    Location pos1 = (Location) ymlll.get("location.point.pos1");
                    Location pos2 = (Location) ymlll.get("location.point.pos2");

                    int X = getRandomCord(pos1.getBlockX(), pos2.getBlockX());
                    int Z = getRandomCord(pos1.getBlockZ(), pos2.getBlockZ());
                    int Y = author.getWorld().getHighestBlockYAt(X, Z);
                    while (author.getWorld().getBlockAt(X, Y, Z).getType() != Material.AIR) {
                        Y++;
                    }

                    Block block = author.getWorld().getBlockAt(X, Y, Z);
                    if(block.getType().equals(Material.AIR)) {
                        block.setType(Material.CHEST);
                        Location chestLoc = new Location(author.getWorld(), X, Y, Z);
                        pChestList.add(chestLoc);
                    }
                }
                PointTask taskss = new PointTask(Largage.getInstance());
                taskss.runTaskTimer(Largage.getInstance(), 0, 20);
                Largage.getInstance().setPointStatus(PointStatus.ENABLED);
                return true;
            default:
                return false;
        }
    }

    public static boolean stop(Player author, String event) {
        switch (event) {
            case "item":
                if(Largage.getInstance().isItemStatus(ItemStatus.DISABLED)) {
                    author.sendMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("non-lance").replaceAll("&", "§"));
                    return false;
                }
                Largage.getInstance().setItemStatus(ItemStatus.DISABLED);
                Bukkit.broadcastMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("stop-event").replaceAll("%TYPE%", event).replaceAll("&", "§"));
                return true;
            case "argent":
                if(Largage.getInstance().isArgentStatus(ArgentStatus.DISABLED)) {
                    author.sendMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("non-lance").replaceAll("&", "§"));
                    return false;
                }
                Largage.getInstance().setArgentStatus(ArgentStatus.DISABLED);
                Bukkit.broadcastMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("stop-event").replaceAll("%TYPE%", event).replaceAll("&", "§"));
                return true;
            case "point":
                if(Largage.getInstance().isPointStatus(PointStatus.DISABLED)) {
                    author.sendMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("non-lance").replaceAll("&", "§"));
                    return false;
                }
                Largage.getInstance().setPointStatus(PointStatus.DISABLED);
                Bukkit.broadcastMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("stop-event").replaceAll("%TYPE%", event).replaceAll("&", "§"));
                return true;
            case "all":
                if(Largage.getInstance().isPointStatus(PointStatus.DISABLED) && Largage.getInstance().isArgentStatus(ArgentStatus.DISABLED) && Largage.getInstance().isItemStatus(ItemStatus.DISABLED)) {
                    author.sendMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("zero-event").replaceAll("&", "§"));
                    return false;
                }

                Largage.getInstance().setPointStatus(PointStatus.DISABLED);
                Largage.getInstance().setArgentStatus(ArgentStatus.DISABLED);
                Largage.getInstance().setItemStatus(ItemStatus.DISABLED);
                Bukkit.broadcastMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("stop-all-event").replaceAll("%TYPE%", event).replaceAll("&", "§"));

                return true;
            default:
                return false;
        }
    }
}
