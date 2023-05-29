package fr.mrcyci6.largage.listeners;

import fr.mrcyci6.largage.Largage;
import fr.mrcyci6.largage.status.ArgentStatus;
import fr.mrcyci6.largage.status.ItemStatus;
import fr.mrcyci6.largage.status.PointStatus;
import fr.mrcyci6.largage.utils.LargageManager;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.swing.text.Position;
import java.util.List;
import java.util.Random;

public class PlayerListener implements Listener {

    private Largage plugin;
    public static Location pos1;
    public static Location pos2;
    private Random random;
    private List<String> commands;
    public PlayerListener(Largage plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(event.getPlayer().hasPermission(this.plugin.getConfig().getString("permission"))) {
            if (player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(this.plugin.prefix + "§eAXE")) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {

        if(event.getPlayer().hasPermission(this.plugin.getConfig().getString("permission"))) {
            if (event.hasItem() && event.getItem().hasItemMeta() && event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(this.plugin.prefix + "§eAXE")) {
                if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {

                    pos1 = event.getClickedBlock().getLocation();
                    event.getPlayer().sendMessage(this.plugin.prefix + "§aPosition 1 enregistrée : " + pos1.getX() + ", " + pos1.getY() + ", " + pos1.getZ());
                }

                if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                    pos2 = event.getClickedBlock().getLocation();
                    event.getPlayer().sendMessage(this.plugin.prefix + "§aPosition 2 enregistrée : " + pos2.getX() + ", " + pos2.getY() + ", " + pos2.getZ());
                }
            }
        }

        if(this.plugin.isItemStatus(ItemStatus.ENABLED)) {
            try {
                if (event.getClickedBlock().getType().equals(Material.CHEST)) {
                    for (int i = 0; i < LargageManager.iChestList.size(); i++) {
                        if (LargageManager.iChestList.get(i).equals(event.getClickedBlock().getLocation())) {

                            Player player = event.getPlayer();

                            String command = getRandomCommand(this.plugin.getConfig().getStringList("item.commands"));
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
                            event.getClickedBlock().setType(Material.AIR);
                            LargageManager.iChestList.remove(LargageManager.iChestList.get(i));
                        }

                    }
                }
            } catch (Exception e) {}
        }

        if(this.plugin.isArgentStatus(ArgentStatus.ENABLED)) {
            try {
                if (event.getClickedBlock().getType().equals(Material.CHEST)) {
                    for (int i = 0; i < LargageManager.aChestList.size(); i++) {
                        if (LargageManager.aChestList.get(i).equals(event.getClickedBlock().getLocation())) {

                            Player player = event.getPlayer();

                            String command = getRandomCommand(this.plugin.getConfig().getStringList("argent.commands"));
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
                            event.getClickedBlock().setType(Material.AIR);
                            LargageManager.aChestList.remove(LargageManager.aChestList.get(i));
                        }

                    }
                }
            } catch (Exception e) {}
        }

        if(this.plugin.isPointStatus(PointStatus.ENABLED)) {
            try {
                if (event.getClickedBlock().getType().equals(Material.CHEST)) {
                    for (int i = 0; i < LargageManager.pChestList.size(); i++) {
                        if (LargageManager.pChestList.get(i).equals(event.getClickedBlock().getLocation())) {

                            Player player = event.getPlayer();

                            String command = getRandomCommand(this.plugin.getConfig().getStringList("point.commands"));
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
                            event.getClickedBlock().setType(Material.AIR);
                            LargageManager.pChestList.remove(LargageManager.pChestList.get(i));
                        }

                    }
                }
            } catch (Exception e) {}
        }
    }

    private String getRandomCommand(List<String> commands) {
        if (commands.isEmpty()) {
            this.plugin.sendLog("§cLa liste des commandes est vide !");
            return "";
        }

        Random random = new Random();
        int index = random.nextInt(commands.size());

        return commands.get(index);
    }
}
