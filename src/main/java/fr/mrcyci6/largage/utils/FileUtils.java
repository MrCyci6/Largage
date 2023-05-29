package fr.mrcyci6.largage.utils;

import java.io.File;
import java.io.IOException;

import fr.mrcyci6.largage.Largage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileUtils
{
    public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createLocation(File file) {
        if (!file.exists()) {

            Largage.sendLog("§aLocation file was created.");
            Location loc = new Location(Bukkit.getWorld("world"), 60.0D, 60.0D, 60.0D);
            YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
            yml.set("location.example.pos1", loc);
            yml.set("location.example.pos2", loc);
            saveCustomYml((FileConfiguration)yml, file);
        }
    }
}