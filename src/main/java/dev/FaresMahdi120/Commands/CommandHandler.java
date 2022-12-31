package dev.FaresMahdi120.Commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

public abstract class CommandHandler {
    public abstract void registerCommand(@NotNull String label, CommandExecutor cmd, TabCompleter tabCompleter);
    public abstract String getLabel();
}
