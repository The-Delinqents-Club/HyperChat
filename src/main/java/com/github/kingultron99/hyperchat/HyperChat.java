package com.github.kingultron99.hyperchat;

import com.github.kingultron99.hyperchat.events.Events;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class HyperChat extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            System.out.println("Loading HyperChat!");
            getServer().getPluginManager().registerEvents(new Events(), this);
        } else {
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Shutting down HyperChat");
        HandlerList.unregisterAll();
    }
}
