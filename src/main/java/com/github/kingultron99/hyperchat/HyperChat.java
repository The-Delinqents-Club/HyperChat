package com.github.kingultron99.hyperchat;

import com.github.kingultron99.hyperchat.events.Chat;
import com.github.kingultron99.hyperchat.events.JoinQuit;
import com.github.kingultron99.hyperchat.util.Config;
import com.github.kingultron99.hyperchat.util.Locales;
import net.luckperms.api.LuckPerms;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class HyperChat extends JavaPlugin {

    private static HyperChat INSTANCE;
    private static LuckPerms luckPerms;

    @Override
    public void onEnable() {
        INSTANCE = this;

        Config.load();
        Locales.load();
        // Plugin startup logic
        RegisteredServiceProvider<LuckPerms> provider = getServer().getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckPerms = provider.getProvider();

        }
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            INSTANCE.getLogger().info("Loading HyperChat!");
            getServer().getPluginManager().registerEvents(new JoinQuit(), this);
            getServer().getPluginManager().registerEvents(new Chat(), this);
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
    public static LuckPerms getLuckPerms() {
        return luckPerms;
    }
}
