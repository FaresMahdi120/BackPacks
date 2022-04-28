package dev.maahdi.Commands;

import dev.maahdi.BackPacks;
import dev.maahdi.Items.BackPackItems;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdminCommands implements TabExecutor {
    private final BackPacks plugin;
    private final BackPackItems bpitems;
    private final NamespacedKey basic_key;
    private final NamespacedKey advanced_key;
    private final NamespacedKey extra_key;
    public AdminCommands(BackPacks plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginCommand("abp").setExecutor(this);
        plugin.getServer().getPluginCommand("abp").setTabCompleter(this);
        bpitems = new BackPackItems(plugin);
        basic_key = new NamespacedKey(plugin, (String) plugin.lang.get_Value("BackPacks.Basic.name-spaced-key"));
        advanced_key = new NamespacedKey(plugin, (String) plugin.lang.get_Value("BackPacks.Advanced.name-spaced-key"));
        extra_key = new NamespacedKey(plugin, (String) plugin.lang.get_Value("BackPacks.Extra.name-spaced-key"));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(command.getName().equalsIgnoreCase("abp")){
                if (args.length == 0) {
                    for (String line : plugin.msg.get_admin_Help_Message()) {
                        player.sendMessage(plugin.msg.MessageColorCoding(line));
                    }
                }else if(args.length > 1){
                    if (args[0].equalsIgnoreCase("give")){
                        give_and_remove_BackPacks(args, player,"give");
                    }else if (args[0].equalsIgnoreCase("remove")){
                        give_and_remove_BackPacks(args,player,"remove");
                    }else if(args[0].equalsIgnoreCase("upgrade")){
                        Upgrade_BakcPack(player, args, command.getName());
                    }
                }
            }
        }else {
            Bukkit.getConsoleSender().sendMessage(plugin.msg.MessageColorCoding("&cThis Command is only for in game players!"));
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> list = new ArrayList<>();
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("abp")) {
            if (args.length == 0){
                list.add("Upgrade");
                list.add("remove");
                list.add("Reload");
                list.add("give");
                return list;
            }
            if (args[0].equalsIgnoreCase("give") && args.length == 1){
                list.add("Basic");
                list.add("Advanced");
                list.add("Extra");
                return list;
            }
        }
        return null;
    }

    public void Upgrade_BakcPack(Player player, String[] args, String command){
        if(command.equalsIgnoreCase("abp") && args[0].equalsIgnoreCase("upgrade")){
            Player playername = Bukkit.getPlayer(args[1]);
            assert playername != null;
            if(Bukkit.getOnlinePlayers().contains(playername)){
                if(!(playername.getInventory().isEmpty())){
                    for (ItemStack item: playername.getInventory().getContents()) {
                        try{
                            assert item != null;
                            if(item.getItemMeta().hasLore()){
                                if(item.getItemMeta().getPersistentDataContainer().has(basic_key, PersistentDataType.BYTE)){
                                    playername.getInventory().remove(item);
                                    give_items(player, playername, "advanced", "BackPacks.Advanced.giving-message");
                                    break;
                                }
                                if(item.getItemMeta().getPersistentDataContainer().has(advanced_key, PersistentDataType.BYTE)){
                                    playername.getInventory().remove(item);
                                    give_items(player, playername, "advanced", "BackPacks.Advanced.giving-message");
                                    break;
                                }
                                if(item.getItemMeta().getPersistentDataContainer().has(extra_key, PersistentDataType.BYTE)){
                                    playername.sendMessage(plugin.msg.MessageColorCoding("&cYou have reached the max level!"));
                                    break;
                                }

                            }
                        }catch (NullPointerException ignore){}
                    }
                }else if(playername.getInventory().isEmpty()){
                    player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer's inventory has nothing!"));
                }
            }else if (!(Bukkit.getOnlinePlayers().contains(playername))){
                player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer " + "&f" + playername.getName() + " &cis not online!"));
            }
        }

    }
    public void give_and_remove_BackPacks(String[] args, Player player, String input){
        switch (input){
            case "give":
                if (args.length == 3){
                    if(args[1].equalsIgnoreCase("basic")){
                        Player playername = Bukkit.getPlayer(args[2]);
                        give_items(player, playername, "basic", "BackPacks.Basic.giving-message");
                    }
                    if(args[1].equalsIgnoreCase("advanced")){
                        Player playername = Bukkit.getPlayer(args[2]);
                        give_items(player, playername, "advanced", "BackPacks.Advanced.giving-message");
                    }
                    if(args[1].equalsIgnoreCase("extra")){
                        Player playername = Bukkit.getPlayer(args[2]);
                        give_items(player, playername, "extra", "BackPacks.Extra.giving-message");
                    }
                    if (args[1].equalsIgnoreCase("all")){
                        Player playername = Bukkit.getPlayer(args[2]);
                        if (Bukkit.getOnlinePlayers().contains(playername)){
                            assert playername != null;
                            playername.sendMessage(plugin.msg.MessageColorCoding("&cPlayer " + "&f" + player.getName() + " &cHave given you all the available backpacks!"));
                            give_items(player, playername, "basic", "BackPacks.Basic.giving-message");
                            give_items(player, playername, "advanced", "BackPacks.Advanced.giving-message");
                            give_items(player, playername, "extra", "BackPacks.Extra.giving-message");
                        }else if (!Bukkit.getOnlinePlayers().contains(playername)){
                            assert playername != null;
                            player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer " + "&f" + playername.getName() + " &cis not online!"));
                        }
                    }
                }else if (args.length == 2){
                    player.sendMessage(plugin.msg.MessageColorCoding("&cInvalid Usage!"));
                    player.sendMessage(plugin.msg.MessageColorCoding("&cPlease use: &e/abp give " + args[1] + " &c<Online Player name>"));
                }else if (args.length == 1){
                    player.sendMessage(plugin.msg.MessageColorCoding("&cInvalid Usage!"));
                    player.sendMessage(plugin.msg.MessageColorCoding("&cPlease use: &e/abp give <Backpack name> &c<Online Player name>"));
                }
                break;
            case "remove":
                if (args.length == 3){
                    if(args[1].equalsIgnoreCase("basic")){
                        Player playername = Bukkit.getPlayer(args[2]);
                        remove_items(player, playername, basic_key, "BackPacks.Basic.removing-message");
                    }
                    if(args[1].equalsIgnoreCase("advanced")){
                        Player playername = Bukkit.getPlayer(args[2]);
                        remove_items(player, playername, advanced_key, "BackPacks.Advanced.removing-message");
                    }
                    if(args[1].equalsIgnoreCase("extra")){
                        Player playername = Bukkit.getPlayer(args[2]);
                        remove_items(player, playername, extra_key, "BackPacks.Extra.removing-message");
                    }
                    if (args[1].equalsIgnoreCase("all")){
                        Player playername = Bukkit.getPlayer(args[2]);
                        remove_items(player, playername, basic_key, "BackPacks.Basic.removing-message");
                        remove_items(player, playername, advanced_key, "BackPacks.Advanced.removing-message");
                        remove_items(player, playername, extra_key, "BackPacks.Extra.removing-message");
                    }
                }else if (args.length == 2){
                    player.sendMessage(plugin.msg.MessageColorCoding("&cInvalid Usage!"));
                    player.sendMessage(plugin.msg.MessageColorCoding("&cPlease use: &e/abp remove " + args[1] + " &c<Online Player name>"));
                }else if (args.length == 1){
                    player.sendMessage(plugin.msg.MessageColorCoding("&cInvalid Usage!"));
                    player.sendMessage(plugin.msg.MessageColorCoding("&cPlease use: &e/abp remove <Backpack name> &c<Online Player name>"));
                }
                break;
        }
    }
    public void remove_items(Player player, Player playername, NamespacedKey key, String message_path){
        if (Bukkit.getOnlinePlayers().contains(playername)){
            assert playername != null;
            if(player.getInventory().isEmpty()){
                player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer Does not have anything in their inventory!"));
            }else if (!(player.getInventory().isEmpty())){
                for (ItemStack item: playername.getInventory().getContents()) {
                    assert item != null;
                    assert item.getItemMeta() != null;
                    try{
                        if(item.getItemMeta().hasLore()){
                            if (item.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.BYTE)) {
                                playername.getInventory().remove(item);
                                playername.sendMessage(plugin.msg.MessageColorCoding((String) plugin.lang.get_Value(message_path)));
                                break;
                            }
                            if(!(item.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.BYTE))){
                                player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer Does not have the basic backpack in their inventory!"));
                            }
                        }
                    }catch (NullPointerException ignored){
                    }
                }
            }
        }else if (!Bukkit.getOnlinePlayers().contains(playername)){
            assert playername != null;
            player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer " + "&f" + playername.getName() + " &cis not online!"));
        }
    }
    public void give_items(Player player, Player playername, String backpackname, String message_path){
        if (Bukkit.getOnlinePlayers().contains(playername)){
            assert playername != null;
            playername.sendMessage(plugin.msg.MessageColorCoding(plugin.msg.MessageColorCoding((String) plugin.lang.get_Value(message_path))));
            playername.getInventory().addItem(this.bpitems.get_BackPacks(backpackname));
        }else if (!Bukkit.getOnlinePlayers().contains(playername)){
            player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer " + "&f" + playername.getName() + " &cis not online!"));
        }

    }

}
