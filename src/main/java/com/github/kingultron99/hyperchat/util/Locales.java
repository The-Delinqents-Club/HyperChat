package com.github.kingultron99.hyperchat.util;

import com.github.kingultron99.hyperchat.HyperChat;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public enum Locales {
    COMMAND_RELOAD_DESCRIPTION("Commands.Reload.Description", "Reloads the plugin and its configuration."),
    COMMAND_CLEAR_DESCRIPTION("Commands.Clear.Description", "Clears the chat."),
    COMMAND_CLEAR_CONSOLE("Commands.Clear.Console", "CONSOLE"),
    COMMAND_CLEAR_UNKNOWN("Commands.Clear.Unknown", "UNKNOWN"),
    MESSAGES_RELOAD("Messages.Commands.Reload.Success", "&aConfig was reloaded."),
    MESSAGES_CLEAR("Messages.Commands.Clear.Success", "&aThe chat has been cleared by "),
    COMMAND_RESULT_NO_PERM("Messages.CommandResult.NoPermission", "&4[ERROR] &7You don't have permission for this! &c(%perm)"),
    COMMAND_RESULT_WRONG_USAGE("Messages.CommandResult.WrongUsage", "&c[ERROR] &7Wrong usage! Please type &6%cmd help&7!"),
    ANTI_SPAM_DENIED("Messages.AntiSpam.Denied", "&e[AntiSpam] &7You are not allowed to spam! Please wait another &e%time% &7seconds!"),
    ;

    private final String value;
    private final String path;
    private static YamlConfiguration cfg;
    private static final File localeFolder = new File(HyperChat.getInstance().getDataFolder(), "locales");
    private static File f;

    Locales(String path, String val) {
        this.path = path;
        this.value = val;
    }

    public String getPath() {
        return path;
    }

    public String getDefaultValue() {
        return value;
    }

    public String getString(Player p) {
        String ret = Objects.requireNonNull(cfg.getString(path)).replaceAll("&((?i)[0-9a-fk-or])", "§$1");
        ret = PlaceholderAPI.setPlaceholders(p, ret);
        return ret;
    }

    public static void load() {
        localeFolder.mkdirs();
        f = new File(localeFolder, Config.LOCALE.getString() + ".yml");
        if (!f.exists()) {
            try {
                HyperChat.getInstance().saveResource("locales" + File.separator + Config.LOCALE.getString() + ".yml", true);
                File locale = new File(HyperChat.getInstance().getDataFolder(), Config.LOCALE.getString() + ".yml");
                if (locale.exists()) {
                    locale.delete();
                }
                reload(false);
            } catch (IllegalArgumentException ex) {
                reload(false);
                try {
                    for (Locales c : values()) {
                        if (!cfg.contains(c.getPath())) {
                            c.set(c.getDefaultValue(), false);
                        }
                    }
                    cfg.save(f);
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }
            }
        } else {
            reload(false);
            try {
                for (Locales c : values()) {
                    if (!cfg.contains(c.getPath())) {
                        c.set(c.getDefaultValue(), false);
                    }
                }
                cfg.save(f);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
