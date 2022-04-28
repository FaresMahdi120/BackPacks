package dev.maahdi.Ymal;

import dev.maahdi.BackPacks;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.logging.Level;

public class LangYml {
    private BackPacks plugin;
    private FileConfiguration configuration = null;
    private File configFile = null;
    public LangYml(BackPacks main){
        this.plugin = main;
        this.saveDaultthis();
    }
    public void reloadConfig(){
        if(this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "lang.yml");
        this.configuration = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.plugin.getResource("lang.yml");
        if(defaultStream != null){
            YamlConfiguration dafualtconfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.configuration.setDefaults(dafualtconfig);
        }
    }
    public FileConfiguration getConfig(){
        if(this.configuration == null)
            reloadConfig();
        return this.configuration;
    }
    public void SavethisConfig(){
        if(this.configuration == null || this.configFile == null)
            return;
        try {
            this.getConfig().save(this.configFile);
        }catch (Exception e){
            plugin.getLogger().log(Level.SEVERE, "Could not save this File");
        }
    }
    public void saveDaultthis() {
        if (this.configuration == null)
            this.configFile = new File(this.plugin.getDataFolder(), "lang.yml");
        if(!this.configFile.exists())
            this.plugin.saveResource("lang.yml", false);

    }
    public Object get_Value(String Path){
        if (this.getConfig().get(Path) instanceof String){return this.getConfig().getString(Path);}
        if (this.getConfig().get(Path) instanceof Boolean){return this.getConfig().getBoolean(Path);}
        if (this.getConfig().get(Path) instanceof Integer){return this.getConfig().getInt(Path);}
        return null;
    }
}
