package dev.maahdi.Utils;

import dev.maahdi.BackPacks;

import net.kyori.adventure.key.Key;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ItemsUtil extends ItemStack {
    private final BackPacks plugin;
    public ItemsUtil(BackPacks plugin){
        this.plugin = plugin;
    }
    public ItemStack CreateItem(String namespacedkey,Material material, String name, int Amount, List<String> lore, boolean glow, ItemFlag flag1, ItemFlag flag2){
        ItemStack item = new ItemStack(material, Amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&',name));
        if(glow) {meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1,true);}
        List<String> loreof = new ArrayList<>();
        for (String line : lore) {
            loreof.add(ChatColor.translateAlternateColorCodes('&', line));
        }
        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, namespacedkey), PersistentDataType.BYTE, (byte) 0);
        meta.setLore(loreof);
        meta.addItemFlags(flag1);
        meta.addItemFlags(flag2);
        item.setItemMeta(meta);
        return item;
    }

}
