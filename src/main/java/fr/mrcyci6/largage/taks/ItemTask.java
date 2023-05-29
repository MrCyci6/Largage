package fr.mrcyci6.largage.taks;

import fr.mrcyci6.largage.Largage;
import fr.mrcyci6.largage.status.ItemStatus;
import fr.mrcyci6.largage.utils.LargageManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemTask extends BukkitRunnable {

    private int timer = Largage.getInstance().getConfig().getInt("item.time");
    private Largage plugin;
    public ItemTask(Largage plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

        if(LargageManager.iChestList.isEmpty()) {
            Bukkit.broadcastMessage(Largage.getInstance().prefix + this.plugin.getConfig().getString("item.fin").replaceAll("&", "§"));
            this.plugin.setItemStatus(ItemStatus.DISABLED);
            cancel();
        }
        if(Largage.getInstance().isItemStatus(ItemStatus.DISABLED)) {
            LargageManager.removeChest(LargageManager.iChestList);
            cancel();
        }
        if(timer == 15 || timer == 10 || timer == 5) {
            Bukkit.broadcastMessage(Largage.getInstance().prefix + this.plugin.getConfig().getString("item.timer").replaceAll("%TIMER%", String.valueOf(timer)).replaceAll("&", "§"));
        }
        if(timer == 0) {
            LargageManager.removeChest(LargageManager.iChestList);
            this.plugin.setItemStatus(ItemStatus.DISABLED);
            cancel();
        }

        timer--;
    }
}
