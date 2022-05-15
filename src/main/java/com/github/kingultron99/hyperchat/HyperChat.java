package com.github.kingultron99.hyperchat;

import com.github.kingultron99.hyperchat.events.JoinQuit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class HyperChat extends JavaPlugin {

    private static HyperChat INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        // Plugin startup logic
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            INSTANCE.getLogger().info("Loading HyperChat!");
            getServer().getPluginManager().registerEvents(new JoinQuit(), this);
            INSTANCE.getCommand("hyperchat").setExecutor(new CommandHandler());
        } else {
            INSTANCE.getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        HyperChat.getInstance().getLogger().info("Shutting down HyperChat");
        HandlerList.unregisterAll();
    }

    public static HyperChat getInstance() {
        return INSTANCE;
    }
}
