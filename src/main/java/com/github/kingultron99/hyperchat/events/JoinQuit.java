package com.github.kingultron99.hyperchat.events;

import com.github.kingultron99.hyperchat.util.Config;
import com.github.kingultron99.hyperchat.util.Perms;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.libs.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class JoinQuit implements Listener {
        @EventHandler(priority = EventPriority.HIGHEST)
        public void onJoin(PlayerJoinEvent e) {
                Player player = e.getPlayer();
                final Component header = MiniMessage.miniMessage().deserialize("Welcome to <gradient:#D8B4FE:#9333EA>The Delinquents Club</color>!");
                player.sendPlayerListHeader(header);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100F, 1F);
                Component playerMessage;


                if (player.hasPlayedBefore()) {
                        playerMessage = Component.text("Welcome back ").color(TextColor.color(0x555555))
                                .append(MiniMessage.miniMessage().deserialize(" <color:#555555>[</color>" + Objects.requireNonNull(Perms.getPlayerGroup(player, Config.ROLES.getStringList())) + "<color:#555555>]</color> "))
                                .append(Component.text(PlaceholderAPI.setPlaceholders(player, "%player_name%"))
                                        .color(TextColor.color(0xd8b4fe)))
                                .append(Component.text("!\n")
                                        .color(TextColor.color(0x555555)));
                } else {
                        playerMessage = Component.text("Welcome to The Delinquents Club, ")
                                        .color(TextColor.color(0x555555))
                                        .append(Component.text(PlaceholderAPI.setPlaceholders(player, "%player_name%"))
                                                .color(TextColor.color(0xd8b4fe)))
                                        .append(Component.text("!\n")
                                                .color(TextColor.color(0x555555)))
                                        .append(Component.text("If you haven't already, come join our ")
                                                .color(TextColor.color(0x555555)))
                                        .append(Component.text()
                                                .content("discord server!\n")
                                                .color(TextColor.color(0x5865F2))
                                                .clickEvent(ClickEvent.openUrl("https://discord.gg/xKbwTNbtUk"))
                                                .hoverEvent(HoverEvent.showText(Component.text("Click me to join the discord!")
                                                        .color(TextColor.color(NamedTextColor.GOLD.value()))))
                                                .build())
                                        .append(Component.text("here you can stay up to date with community announcements and give us your feedback").color(TextColor.color(0x555555)));
                }

                Component joinMessage = Component.text("[").color(TextColor.color(0x555555))
                                .append(Component.text("+").color(TextColor.color(0x4ade80)))
                                .append(Component.text("] ").color(TextColor.color(0x555555)))
                                .append(Component.text(PlaceholderAPI.setPlaceholders(player, "%player_name% "))
                                                .color(TextColor.color(0xd8b4fe)))
                                .append(Component.text("joined the server").color(TextColor.color(0x555555)));
                e.joinMessage(joinMessage);

                player.sendMessage(playerMessage);
        }

        @EventHandler(priority = EventPriority.HIGHEST)
        public void onLeave(PlayerQuitEvent e) {
                Player player = e.getPlayer();
                Component quitMessage = Component.text("[").color(TextColor.color(0x555555))
                                .append(Component.text("-").color(TextColor.color(0xef4444)))
                                .append(Component.text("] ").color(TextColor.color(0x555555)))
                                .append(Component.text(PlaceholderAPI.setPlaceholders(player, "%player_name% "))
                                                .color(TextColor.color(0xd8b4fe)))
                                .append(Component.text("left the server").color(TextColor.color(0x555555)));
                e.quitMessage(quitMessage);
        }
}
