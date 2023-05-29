package fr.mrcyci6.largage;

import fr.mrcyci6.largage.commands.LargageCommand;
import fr.mrcyci6.largage.listeners.PlayerListener;
import fr.mrcyci6.largage.status.ArgentStatus;
import fr.mrcyci6.largage.status.ItemStatus;
import fr.mrcyci6.largage.status.PointStatus;
import fr.mrcyci6.largage.utils.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class Largage extends JavaPlugin {

    private static Largage instance;
    public static String prefix;
    public List<String> help = new ArrayList<>();
    public FileUtils fileUtils = new FileUtils();
    public File location = new File(getDataFolder(), "location.yml");
    private ItemStatus itemStatus;
    private ArgentStatus argentStatus;
    private PointStatus pointStatus;

    @Override
    public void onEnable() {
        instance = this;
        setItemStatus(ItemStatus.DISABLED);
        setArgentStatus(ArgentStatus.DISABLED);
        setPointStatus(PointStatus.DISABLED);
        help.add("§7§m---------------------------");
        help.add("");
        help.add("§e/§6largage wand");
        help.add("§e/§6largage start §e« §6argent§e, §6point§e, §6item §e»");
        help.add("§e/§6largage stop §e« §6argent§e, §6point§e, §6item, §6all §e»");
        help.add("§e/§6largage setzone §e« §6argent§e, §6point§e, §6item §e»");
        help.add("");
        help.add("§7    By Kellogg's#0001");
        help.add("§7§m---------------------------");

        // CONFIG
        saveDefaultConfig();
        prefix = this.getConfig().getString("prefix").replaceAll("&", "§");
        this.fileUtils.createLocation(this.location);

        // EVENTS & COMMANDS
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
        getCommand("largage").setExecutor(new LargageCommand(this));

        sendLog("§aPlugin is enabled");

    }

    @Override
    public void onDisable() {

        sendLog("§cPlugin is disabled");
    }

    public static Largage getInstance() {
        return instance;
    }

    public static void sendLog(String string) {
        Bukkit.getConsoleSender().sendMessage(prefix + string);
    }

    public boolean isItemStatus(ItemStatus itemStatus) {
        return this.itemStatus == itemStatus;
    }

    public boolean isArgentStatus(ArgentStatus argentStatus) {
        return this.argentStatus == argentStatus;
    }

    public boolean isPointStatus(PointStatus pointStatus) {
        return this.pointStatus == pointStatus;
    }

    public void setItemStatus(ItemStatus itemStatus) {
        this.itemStatus = itemStatus;
    }

    public void setArgentStatus(ArgentStatus argentStatus) {
        this.argentStatus = argentStatus;
    }

    public void setPointStatus(PointStatus pointStatus) {
        this.pointStatus = pointStatus;
    }
}
