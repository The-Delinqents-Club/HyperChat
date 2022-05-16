package com.github.kingultron99.hyperchat;

import com.github.kingultron99.hyperchat.util.Config;
import com.github.kingultron99.hyperchat.util.Locales;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements CommandExecutor, TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd,
            @NotNull String s, @NotNull String[] strings) {
        List<String> possibleTabs = new ArrayList<>();
        if (sender.hasPermission("hyperchat.clear")) {
            possibleTabs.add("clear");
        }
        return possibleTabs;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label,
            @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Component.text()
                    .append(MiniMessage.miniMessage()
                            .deserialize("<gradient:#D8B4FE:#9333EA>HyperChat</gradient> plugin by "
                                    + HyperChat.getInstance().getDescription().getAuthors()))
                    .build());
            return true;
        } else if (args.length > 1) {
            sender.sendMessage(MiniMessage.miniMessage()
                    .deserialize("<color:#EF4444>Incorrect usage!</color"));
            return true;
        } else {
            if (args[0].equalsIgnoreCase("clear")) {
                if (sender.hasPermission("hyperchat.clear")) {
                    for (int i = 0; i < 50; i++) {
                        Bukkit.broadcast(Component.text("\n"));
                    }
                } else {
                    sender.sendMessage(MiniMessage.miniMessage()
                            .deserialize(Locales.COMMAND_RESULT_NO_PERM.getString(HyperChat.getInstance().getServer().getPlayer(sender.getName())).replaceAll("%perm", "hyperchat.clear")));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("hyperchat.reload")) {
                    sender.sendMessage("Reloading!");
                    Config.reload(true);
                    Locales.reload(true);
                } else {
                    sender.sendMessage(MiniMessage.miniMessage()
                            .deserialize(Locales.COMMAND_RESULT_NO_PERM.getString(HyperChat.getInstance().getServer().getPlayer(sender.getName())).replaceAll("%perm", "hyperchat.reload")));
                }
                return true;
            } else {
                sender.sendMessage(MiniMessage.miniMessage().deserialize("<color:#EF4444>Unknown command!</color>"));
                return true;
            }
        }
    }

}
