package dev.maahdi;

import dev.maahdi.Commands.AdminCommands;
import dev.maahdi.Utils.ItemsUtil;
import dev.maahdi.Utils.MessageUtils;
import dev.maahdi.Ymal.LangYml;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public final class BackPacks extends JavaPlugin {
    public AdminCommands acmd;
    public LangYml lang = new LangYml(this);
    public ItemsUtil util = new ItemsUtil(this);
    public final MessageUtils msg = new MessageUtils(this);
    @Override
    public void onEnable() {
        acmd = new AdminCommands(this);
        System.out.println("Plugin starts");
    }

    @Override
    public void onDisable() {System.out.println("Plugin stops");}

    public FileConfiguration getLangConfig(){
        return lang.getConfig();
    }
    public void Reload_Plugin(){
        this.lang.reloadConfig();
        lang.SavethisConfig();
        this.reloadConfig();
        this.saveConfig();
    }
}
