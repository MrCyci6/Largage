package fr.mrcyci6.largage.commands;

import fr.mrcyci6.largage.Largage;
import fr.mrcyci6.largage.listeners.PlayerListener;
import fr.mrcyci6.largage.utils.LargageManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

public class LargageCommand implements CommandExecutor {

    private Largage plugin;
    public LargageCommand(Largage plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.hasPermission(this.plugin.getConfig().getString("permission"))) {
            if (args.length < 1) {
                this.plugin.help.forEach(message -> {
                    sender.sendMessage(message);
                });
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage(this.plugin.prefix + this.plugin.getConfig().getString("non-joueur").replaceAll("&", "§"));
                return false;
            }

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("wand")) {
                    Player player = ((Player) sender).getPlayer();

                    ItemStack axe = new ItemStack(Material.GOLD_AXE);
                    ItemMeta axeMeta = axe.getItemMeta();
                    axeMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    axeMeta.setDisplayName(this.plugin.prefix + "§eAXE");

                    List<String> lore = new ArrayList<>();
                    this.plugin.getConfig().getStringList("wand.lore").forEach(line -> {
                        lore.add(line.replaceAll("&", "§"));
                    });

                    axeMeta.setLore(lore);

                    axe.setItemMeta(axeMeta);

                    player.getInventory().addItem(axe);
                    player.updateInventory();
                    player.sendMessage(this.plugin.prefix + this.plugin.getConfig().getString("wand.give").replaceAll("&", "§"));
                    return true;
                }
                this.plugin.help.forEach(message -> {
                    sender.sendMessage(message);
                });
                return false;
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("start")) {
                    if (args[1].equalsIgnoreCase("item")) {

                        LargageManager.start((Player) sender, "item");
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("argent")) {

                        LargageManager.start((Player) sender, "argent");
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("point")) {

                        LargageManager.start((Player) sender, "point");
                        return true;
                    }
                    this.plugin.help.forEach(message -> {
                        sender.sendMessage(message);
                    });
                    return false;
                }
                if (args[0].equalsIgnoreCase("stop")) {
                    if (args[1].equalsIgnoreCase("item")) {

                        LargageManager.stop((Player) sender, "item");
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("argent")) {

                        LargageManager.stop((Player) sender, "argent");
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("point")) {

                        LargageManager.stop((Player) sender, "point");
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("all")) {

                        LargageManager.stop((Player) sender, "all");
                        return true;
                    }
                    this.plugin.help.forEach(message -> {
                        sender.sendMessage(message);
                    });
                    return false;
                }
                if (args[0].equalsIgnoreCase("setzone")) {
                    Player player = ((Player) sender).getPlayer();
                    if (args[1].equalsIgnoreCase("item")) {

                        if(PlayerListener.pos1 == null || PlayerListener.pos2 == null) {
                            player.sendMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("non-zone").replaceAll("&", "§"));
                            return false;
                        }

                        YamlConfiguration ymlLocation = YamlConfiguration.loadConfiguration(this.plugin.location);

                        ymlLocation.set("location.item.pos1", PlayerListener.pos1);
                        ymlLocation.set("location.item.pos2", PlayerListener.pos2);

                        this.plugin.fileUtils.saveCustomYml((FileConfiguration) ymlLocation, this.plugin.location);
                        player.sendMessage(this.plugin.prefix + this.plugin.getConfig().getString("item.zone").replaceAll("&", "§"));

                        return true;
                    }
                    if (args[1].equalsIgnoreCase("argent")) {

                        if(PlayerListener.pos1 == null || PlayerListener.pos2 == null) {
                            player.sendMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("non-zone").replaceAll("&", "§"));
                            return false;
                        }

                        YamlConfiguration ymlLocation = YamlConfiguration.loadConfiguration(this.plugin.location);

                        ymlLocation.set("location.argent.pos1", PlayerListener.pos1);
                        ymlLocation.set("location.argent.pos2", PlayerListener.pos2);

                        this.plugin.fileUtils.saveCustomYml((FileConfiguration) ymlLocation, this.plugin.location);
                        player.sendMessage(this.plugin.prefix + this.plugin.getConfig().getString("argent.zone").replaceAll("&", "§"));

                        return true;
                    }
                    if (args[1].equalsIgnoreCase("point")) {

                        if(PlayerListener.pos1 == null || PlayerListener.pos2 == null) {
                            player.sendMessage(Largage.getInstance().prefix + Largage.getInstance().getConfig().getString("non-zone").replaceAll("&", "§"));
                            return false;
                        }

                        YamlConfiguration ymlLocation = YamlConfiguration.loadConfiguration(this.plugin.location);

                        ymlLocation.set("location.point.pos1", PlayerListener.pos1);
                        ymlLocation.set("location.point.pos2", PlayerListener.pos2);

                        this.plugin.fileUtils.saveCustomYml((FileConfiguration) ymlLocation, this.plugin.location);
                        player.sendMessage(this.plugin.prefix + this.plugin.getConfig().getString("point.zone").replaceAll("&", "§"));

                        return true;
                    }
                    this.plugin.help.forEach(message -> {
                        player.sendMessage(message);
                    });
                    return false;
                }
                this.plugin.help.forEach(message -> {
                    sender.sendMessage(message);
                });
                return false;
            }
            this.plugin.help.forEach(message -> {
                sender.sendMessage(message);
            });
            return false;
        }
        sender.sendMessage(this.plugin.prefix + this.plugin.getConfig().getString("erreur").replaceAll("&", "§"));
        return false;
    }
}
