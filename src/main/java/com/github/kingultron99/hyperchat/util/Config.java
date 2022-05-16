package com.github.kingultron99.hyperchat.util;

import com.github.kingultron99.hyperchat.HyperChat;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Config {
    LOCALE("Locale", "en-EN", "Currently Only support en-EN"),
    ROLES("Roles", Arrays.asList("<gradient:#D8B4FE:#9333EA>Owner</gradient>", "<gradient:#5EB1BF:#0B4F6C>Trusted</gradient>"),"A list of all roles on the server");
    private final Object value;
    private final String path;
    private final String description;
    private static YamlConfiguration cfg;
    private static final File f = new File(HyperChat.getInstance().getDataFolder(), "config.yml");

    Config(String path, Object val, String description) {
        this.path = path;
        this.value = val;
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public String getDescription() {
        return description;
    }

    public Object getDefaultValue() {
        return value;
    }

    public boolean getBoolean() {
        return cfg.getBoolean(path);
    }

    public double getDouble() {
        return cfg.getDouble(path);
    }

    public int getInt() {
        return cfg.getInt(path);
    }

    public String getString() {
        return cfg.getString(path);
    }

    public List<String> getStringList() {
        return cfg.getStringList(path);
    }

    public ConfigurationSection getConfigurationSection() {
        return cfg.getConfigurationSection(path);
    }

    public static void load() {
        HyperChat.getInstance().getDataFolder().mkdirs();
        reload(false);
        StringBuilder header = new StringBuilder();
        for (Config c : values()) {
            header.append(c.getPath()).append(": ").append(c.getDescription()).append(System.lineSeparator());
            if (!cfg.contains(c.getPath())) {
                c.set(c.getDefaultValue(), false);
            }
        }
        cfg.options().setHeader(Collections.singletonList(header.toString()));
        try {
            cfg.save(f);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void set(Object value, boolean save) {
        cfg.set(path, value);
        if (save) {
            try {
                cfg.save(f);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            reload(false);
        }
    }

    public static void reload(boolean complete) {
        if (!complete) {
            cfg = YamlConfiguration.loadConfiguration(f);
            return;
        }
        load();
    }
}
