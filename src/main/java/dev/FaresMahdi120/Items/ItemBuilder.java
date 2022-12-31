package dev.FaresMahdi120.Items;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
@Getter
public class ItemBuilder extends ItemStack {
    private final String name;
    private final Material material;
    private final int amount;
    private final boolean glow;
    private final List<String> lore;
    public ItemBuilder(String name, Material material, int amount, boolean glow, @NotNull List<String> lore){
        this.name = name;
        this.material = material;

        this.amount = amount;
        this.glow = glow;
        this.lore = lore;
    }

    public ItemStack Build(){
        ItemStack item = new ItemStack(getMaterial(), getAmount());
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getName()));
        if (isGlow()){
            //for later
        }
        if (meta.hasLore()){
            List<String> lore = new ArrayList<>();
            assert getLore() != null;
            for (String line : getLore()){
                lore.add(ChatColor.translateAlternateColorCodes('&', line));
            }
            meta.setLore(lore);
        }
        return item;
    }
}
