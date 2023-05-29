package fr.mrcyci6.largage.taks;

import fr.mrcyci6.largage.Largage;
import fr.mrcyci6.largage.status.ArgentStatus;
import fr.mrcyci6.largage.status.PointStatus;
import fr.mrcyci6.largage.utils.LargageManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class PointTask extends BukkitRunnable {

    private int timer = Largage.getInstance().getConfig().getInt("point.time");
    private Largage plugin;
    public PointTask(Largage plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

        if(LargageManager.pChestList.isEmpty()) {
            Bukkit.broadcastMessage(Largage.getInstance().prefix + this.plugin.getConfig().getString("point.fin").replaceAll("&", "§"));
            this.plugin.setPointStatus(PointStatus.DISABLED);
            cancel();
        }
        if(Largage.getInstance().isPointStatus(PointStatus.DISABLED)) {
            LargageManager.removeChest(LargageManager.pChestList);
            cancel();
        }
        if(timer == 15 || timer == 10 || timer == 5) {
            Bukkit.broadcastMessage(Largage.getInstance().prefix + this.plugin.getConfig().getString("point.timer").replaceAll("%TIMER%", String.valueOf(timer)).replaceAll("&", "§"));
        }
        if(timer == 0) {
            LargageManager.removeChest(LargageManager.pChestList);
            this.plugin.setPointStatus(PointStatus.DISABLED);
            cancel();
        }

        timer--;
    }
}
