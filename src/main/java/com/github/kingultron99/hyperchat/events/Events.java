package com.github.kingultron99.hyperchat.events;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100F, 1F);
        Component joinMessage = Component.text("[").
                color(TextColor.color(0x555555)).
                append(Component.text("+").
                        color(TextColor.color(0x4ade80))).
                append(Component.text("] ").
                        color(TextColor.color(0x555555))).
                append(Component.text(PlaceholderAPI.setPlaceholders(player, "%player_name% ")).
                        color(TextColor.color(0xd8b4fe))).
                append(Component.text("joined the server").color(TextColor.color(0x555555)));
        e.joinMessage(joinMessage);
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        Component quitMessage = Component.text("[").
                color(TextColor.color(0x555555)).
                append(Component.text("-").
                        color(TextColor.color(0xef4444))).
                append(Component.text("] ").
                        color(TextColor.color(0x555555))).
                append(Component.text(PlaceholderAPI.setPlaceholders(player, "%player_name% ")).
                        color(TextColor.color(0xd8b4fe))).
                append(Component.text("left the server").color(TextColor.color(0x555555)));
        e.quitMessage(quitMessage);
    }
}
