package dev.FaresMahdi120.Items.BackPacks;

import dev.FaresMahdi120.Items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BackPackBuilder extends ItemBuilder {

    public BackPackBuilder(String name, Material material, int amount, boolean glow, @NotNull List<String> lore) {
        super(name, material, amount, glow, lore);
    }

    @Override
    public ItemStack Build() {
        return super.Build();
    }
}
