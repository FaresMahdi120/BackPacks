package dev.maahdi.Utils;

import dev.maahdi.BackPacks;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class MessageUtils {
    private final BackPacks plugin;
    public MessageUtils(BackPacks plugin){
        this.plugin = plugin;
    }
    public String MessageColorCoding(String Message){
        return ChatColor.translateAlternateColorCodes('&', Message);
    }
    public List<String> get_admin_Help_Message(){
        List<String> list = new ArrayList<>();
        list.add("&7---------&eAdmin BackPacks&7---------&c(&e1/1&c)");
        list.add("&c/abp give <BackPack> <Online Player>" +
                "&6-&eGive the player a backpack!");
        list.add("&c/abp remove <BackPack> <Online Player>" +
                "&6-&eRemove a backpack from player's inventory!");
        list.add("&c/abp Reload &6-&e Reload the config file of the plugin!");
        list.add("&c/abp Help &6-&e Opens this menu!");
        return list;
    }
    public List<String> get_reload_messages(){
        List<String> list = new ArrayList<>();
        list.add("&7---------&eAdmin BackPacks&7---------");
        list.add("&bReloading &cConfig.yml&b...");
        list.add("&bReloading &cLang.yml&b...");
        list.add("&bPlugin has reloaded!...");
        list.add("");
        list.add("&c/abp Help &6-&e Opens this help menu!");
        return list;
    }

}
