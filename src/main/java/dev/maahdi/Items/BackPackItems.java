package dev.maahdi.Items;

import dev.maahdi.BackPacks;
import dev.maahdi.Utils.ItemsUtil;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;


public class BackPackItems {
    private final BackPacks plugin;
    private final ItemsUtil itemsUtil;
    public BackPackItems(BackPacks plugin){
        this.plugin = plugin;
        this.itemsUtil = plugin.util;
    }

    public ItemStack get_BackPacks(String entry){
        switch (entry){
            case "basic":
                return itemsUtil.CreateItem((String) plugin.lang.get_Value("BackPacks.Basic.name-spaced-key"),Material.CHEST, getLangFile().getString("BackPacks.Basic.name"), 1, plugin.getLangConfig().getStringList("BackPacks.Basic.lore") , true, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
            case "advanced":
                return itemsUtil.CreateItem((String) plugin.lang.get_Value("BackPacks.Basic.name-spaced-key"),Material.CHEST, getLangFile().getString("BackPacks.Advanced.name"), 1, plugin.getLangConfig().getStringList("BackPacks.Advanced.lore") , true, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
            case "extra":
                return itemsUtil.CreateItem((String) plugin.lang.get_Value("BackPacks.Basic.name-spaced-key"),Material.CHEST, getLangFile().getString("BackPacks.Extra.name"), 1, plugin.getLangConfig().getStringList("BackPacks.Extra.lore") , true, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        }
        return null;
    }

    public FileConfiguration getLangFile(){
        return this.plugin.getLangConfig();
    }
}
