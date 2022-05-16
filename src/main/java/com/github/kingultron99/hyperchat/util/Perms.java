package com.github.kingultron99.hyperchat.util;

import org.bukkit.entity.Player;

import java.util.Collection;

public class Perms {
    public static String getPlayerGroup(Player player, Collection<String> possibleGroups) {
        for (String group : possibleGroups) {
            String raw = group.replaceAll("(<[A-z]+:[A-z0-9#!.]+:?[\\/#!\"'A-z0-9.\\s]+(:?[\"'A-z0-9#!\\/.\\s]*)?(:?[\"'A-z0-9#!\\/.\\s]*)?(:?[\"'A-z0-9#!\\/.\\s]*)?>?)|(<[A-z0-9:!#'\"<>]+>)|(<\\/[A-z]+>)", "").toLowerCase();
            if (player.hasPermission("group." + raw)) {
                return group;
            }
        }
        return null;
    }
}
