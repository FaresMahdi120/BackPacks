package dev.maahdi.Commands;

import dev.maahdi.BackPacks;
import dev.maahdi.Items.BackPackItems;
import dev.maahdi.Utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
import java.util.Arrays;
import java.util.List;

public class AdminCommands implements TabExecutor {
    BackPacks plugin;
    private final BackPackItems bpitems;
    public AdminCommands(BackPacks plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginCommand("abp").setExecutor(this);
        plugin.getServer().getPluginCommand("abp").setTabCompleter(this);
        bpitems = new BackPackItems(plugin);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(command.getName().equalsIgnoreCase("abp")){
                switch (args.length){
                    case 0:
                        for (String line: plugin.msg.get_admin_Help_Message()) {
                            player.sendMessage(plugin.msg.MessageColorCoding(line));
                        }
                        break;
                    case 1:
                        switch (args[0]){
                            case "Upgrade":
                                //call upgrade method
                                break;
                            case "Reload":
                                for (String line: plugin.msg.get_reload_messages()) {
                                    player.sendMessage(plugin.msg.MessageColorCoding(line));
                                }
                                this.plugin.Reload_Plugin();
                                break;
                            case "Help":
                                for (String line: plugin.msg.get_admin_Help_Message()) {
                                    player.sendMessage(plugin.msg.MessageColorCoding(line));
                                }
                                break;
                        }
                        break;
                    default:
                        break;

                }
                if (args[0].equalsIgnoreCase("give")){
                    give_and_remove_BackPacks(args, player,"give");
                }else if (args[0].equalsIgnoreCase("remove")){
                    give_and_remove_BackPacks(args,player,"remove");
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
    public void give_and_remove_BackPacks(String[] args, Player player, String input){
        NamespacedKey basic_key = new NamespacedKey(plugin, (String) plugin.lang.get_Value("BackPacks.Basic.name-spaced-key"));
        NamespacedKey advanced_key = new NamespacedKey(plugin, (String) plugin.lang.get_Value("BackPacks.Advanced.name-spaced-key"));
        NamespacedKey extra_key = new NamespacedKey(plugin, (String) plugin.lang.get_Value("BackPacks.Extra.name-spaced-key"));
        switch (input){
            case "give":
                if (args.length == 3){
                    if(args[1].equalsIgnoreCase("basic")){
                        Player playername = Bukkit.getPlayer(args[2]);
                        if (Bukkit.getOnlinePlayers().contains(playername)){
                            assert playername != null;
                            playername.sendMessage(plugin.msg.MessageColorCoding(plugin.msg.MessageColorCoding((String) plugin.lang.get_Value("BackPacks.Basic.giving-message"))));
                            playername.getInventory().addItem(this.bpitems.get_BackPacks("basic"));
                        }else if (!Bukkit.getOnlinePlayers().contains(playername)){
                            player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer " + "&f" + playername.getName() + " &cis not online!"));
                        }
                    }
                    if(args[1].equalsIgnoreCase("advanced")){
                        Player playername = Bukkit.getPlayer(args[2]);
                        if (Bukkit.getOnlinePlayers().contains(playername)){
                            assert playername != null;
                            playername.sendMessage(plugin.msg.MessageColorCoding(plugin.msg.MessageColorCoding((String) plugin.lang.get_Value("BackPacks.Advanced.giving-message"))));
                            playername.getInventory().addItem(this.bpitems.get_BackPacks("advanced"));
                        }else if (!Bukkit.getOnlinePlayers().contains(playername)){
                            assert playername != null;
                            player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer " + "&f" + playername.getName() + " &cis not online!"));
                        }
                    }
                    if(args[1].equalsIgnoreCase("extra")){
                        Player playername = Bukkit.getPlayer(args[2]);
                        if (Bukkit.getOnlinePlayers().contains(playername)){
                            assert playername != null;
                            playername.sendMessage(plugin.msg.MessageColorCoding(plugin.msg.MessageColorCoding((String) plugin.lang.get_Value("BackPacks.Extra.giving-message"))));
                            playername.getInventory().addItem(this.bpitems.get_BackPacks("extra"));
                        }else if (!Bukkit.getOnlinePlayers().contains(playername)){
                            assert playername != null;
                            player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer " + "&f" + playername.getName() + " &cis not online!"));
                        }
                    }
                    if (args[1].equalsIgnoreCase("all")){
                        Player playername = Bukkit.getPlayer(args[2]);
                        if (Bukkit.getOnlinePlayers().contains(playername)){
                            assert playername != null;
                            playername.getInventory().addItem(this.bpitems.get_BackPacks("basic"));
                            playername.sendMessage(plugin.msg.MessageColorCoding("&cPlayer " + "&f" + player.getName() + " &cHave given you all the available backpacks!"));
                            playername.getInventory().addItem(this.bpitems.get_BackPacks("advanced"));
                            playername.getInventory().addItem(this.bpitems.get_BackPacks("extra"));
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
                        if (Bukkit.getOnlinePlayers().contains(playername)){
                            assert playername != null;
                            playername.getInventory().spliterator().forEachRemaining(item -> {
                                if (item.getItemMeta().getPersistentDataContainer().has(basic_key, PersistentDataType.BYTE)) {
                                    playername.getInventory().remove(item);
                                    playername.sendMessage(plugin.msg.MessageColorCoding(plugin.msg.MessageColorCoding((String) plugin.lang.get_Value("BackPacks.Basic.removing-message"))));

                                }else if(!item.getItemMeta().getPersistentDataContainer().has(basic_key, PersistentDataType.BYTE)){
                                    player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer Does not have the basic backpack in their inventory!"));
                                }
                            });
                        }else if (!Bukkit.getOnlinePlayers().contains(playername)){
                            assert playername != null;
                            player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer " + "&f" + playername.getName() + " &cis not online!"));
                        }

                    }
                    if(args[1].equalsIgnoreCase("advanced")){
                        Player playername = Bukkit.getPlayer(args[2]);
                        if (Bukkit.getOnlinePlayers().contains(playername)){
                            assert playername != null;
                            playername.getInventory().spliterator().forEachRemaining(item -> {
                                if (item.getItemMeta().getPersistentDataContainer().has(advanced_key, PersistentDataType.BYTE)) {
                                    playername.getInventory().remove(item);
                                    playername.sendMessage(plugin.msg.MessageColorCoding(plugin.msg.MessageColorCoding((String) plugin.lang.get_Value("BackPacks.Advanced.removing-message"))));
                                }else if(!item.getItemMeta().getPersistentDataContainer().has(advanced_key, PersistentDataType.BYTE)){
                                    player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer Does not have the Advanced backpack in their inventory!"));
                                }
                            });
                        }else if (!Bukkit.getOnlinePlayers().contains(playername)){
                            assert playername != null;
                            player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer " + "&f" + playername.getName() + " &cis not online!"));
                        }
                    }
                    if(args[1].equalsIgnoreCase("extra")){
                        Player playername = Bukkit.getPlayer(args[2]);
                        if (Bukkit.getOnlinePlayers().contains(playername)){
                            assert playername != null;
                            playername.getInventory().spliterator().forEachRemaining(item -> {
                                if (item.getItemMeta().getPersistentDataContainer().has(extra_key, PersistentDataType.BYTE)) {
                                    playername.getInventory().remove(item);
                                    playername.sendMessage(plugin.msg.MessageColorCoding(plugin.msg.MessageColorCoding((String) plugin.lang.get_Value("BackPacks.Extra.removing-message"))));
                                }else if(!item.getItemMeta().getPersistentDataContainer().has(extra_key, PersistentDataType.BYTE)){
                                    player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer Does not have the extra backpack in their inventory!"));
                                }
                            });
                        }else if (!Bukkit.getOnlinePlayers().contains(playername)){
                            assert playername != null;
                            player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer " + "&f" + playername.getName() + " &cis not online!"));
                        }
                    }
                    if (args[1].equalsIgnoreCase("all")){
                        Player playername = Bukkit.getPlayer(args[2]);
                        if (Bukkit.getOnlinePlayers().contains(playername)){
                            assert playername != null;
                            playername.getInventory().spliterator().forEachRemaining(item -> {
                                if (item.getItemMeta().getPersistentDataContainer().has(basic_key, PersistentDataType.BYTE)) {
                                    playername.getInventory().remove(item);
                                    playername.sendMessage(plugin.msg.MessageColorCoding(plugin.msg.MessageColorCoding((String) plugin.lang.get_Value("BackPacks.Basic.removing-message"))));
                                }else if(!item.getItemMeta().getPersistentDataContainer().has(basic_key, PersistentDataType.BYTE)){
                                    player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer Does not have the extra backpack in their inventory!"));
                                }
                            });
                            playername.getInventory().spliterator().forEachRemaining(item -> {
                                if (item.getItemMeta().getPersistentDataContainer().has(advanced_key, PersistentDataType.BYTE)) {
                                    playername.getInventory().remove(item);
                                    playername.sendMessage(plugin.msg.MessageColorCoding(plugin.msg.MessageColorCoding((String) plugin.lang.get_Value("BackPacks.Advanced.removing-message"))));
                                }else if(!item.getItemMeta().getPersistentDataContainer().has(advanced_key, PersistentDataType.BYTE)){
                                    player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer Does not have the extra backpack in their inventory!"));
                                }
                            });
                            playername.getInventory().spliterator().forEachRemaining(item -> {
                                if (item.getItemMeta().getPersistentDataContainer().has(extra_key, PersistentDataType.BYTE)) {
                                    playername.getInventory().remove(item);
                                    playername.sendMessage(plugin.msg.MessageColorCoding(plugin.msg.MessageColorCoding((String) plugin.lang.get_Value("BackPacks.Extra.removing-message"))));
                                }else if(!item.getItemMeta().getPersistentDataContainer().has(extra_key, PersistentDataType.BYTE)){
                                    player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer Does not have the extra backpack in their inventory!"));
                                }
                            });
                        }else if (!Bukkit.getOnlinePlayers().contains(playername)){
                            assert playername != null;
                            player.sendMessage(plugin.msg.MessageColorCoding("&cPlayer " + "&f" + playername.getName() + " &cis not online!"));
                        }
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

}
