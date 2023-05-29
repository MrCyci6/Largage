package fr.mrcyci6.largage.taks;

import com.sun.org.apache.xpath.internal.Arg;
import fr.mrcyci6.largage.Largage;
import fr.mrcyci6.largage.status.ArgentStatus;
import fr.mrcyci6.largage.status.ItemStatus;
import fr.mrcyci6.largage.utils.LargageManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class ArgentTask extends BukkitRunnable {

    private int timer = Largage.getInstance().getConfig().getInt("argent.time");
    private Largage plugin;
    public ArgentTask(Largage plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {

        if(LargageManager.aChestList.isEmpty()) {
            Bukkit.broadcastMessage(Largage.getInstance().prefix + this.plugin.getConfig().getString("argent.fin").replaceAll("&", "§"));
            this.plugin.setArgentStatus(ArgentStatus.DISABLED);
            cancel();
        }
        if(Largage.getInstance().isArgentStatus(ArgentStatus.DISABLED)) {
            LargageManager.removeChest(LargageManager.aChestList);
            cancel();
        }
        if(timer == 15 || timer == 10 || timer == 5) {
            Bukkit.broadcastMessage(Largage.getInstance().prefix + this.plugin.getConfig().getString("argent.timer").replaceAll("%TIMER%", String.valueOf(timer)).replaceAll("&", "§"));
        }
        if(timer == 0) {
            LargageManager.removeChest(LargageManager.aChestList);
            this.plugin.setArgentStatus(ArgentStatus.DISABLED);
            cancel();
        }

        timer--;
    }
}
