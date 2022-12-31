package dev.FaresMahdi120.Items.ItemHandler;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter
public class StoredItems {
    private final String path;
    private final List<ItemStack> items;

    public StoredItems(String path, List<ItemStack> items){

        this.path = path;
        this.items = items;

    }
    public void storeItem(String path, ItemStack itemStack){
        //get config and store this shit booooooooooy
    }
}
