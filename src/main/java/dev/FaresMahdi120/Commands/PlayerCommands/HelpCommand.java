package dev.FaresMahdi120.Commands.PlayerCommands;

import dev.FaresMahdi120.BackPacks;
import dev.FaresMahdi120.Commands.CommandHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.units.qual.N;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HelpCommand extends CommandHandler implements CommandExecutor, TabCompleter {

    public HelpCommand() {
        registerCommand( "BackPackHelp",this, this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] strings) {
         if (sender instanceof Player) {
             if (cmd.getName().equalsIgnoreCase("BackPackHelp")) {
                 //sendhelp odl
             }
         }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }

    @Override
    public void registerCommand(@NotNull String label, CommandExecutor cmd, TabCompleter tabCompleter) {
        JavaPlugin.getPlugin(BackPacks.class).getCommand(label).setExecutor(cmd);
        JavaPlugin.getPlugin(BackPacks.class).getCommand(label).setTabCompleter(tabCompleter);
    }

    @Override
    public String getLabel() {
        return "BackPackHelp";
    }
}
