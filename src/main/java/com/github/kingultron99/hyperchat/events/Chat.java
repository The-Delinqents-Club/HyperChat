package com.github.kingultron99.hyperchat.events;

import com.github.kingultron99.hyperchat.util.Locales;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class Chat implements Listener {
 @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncChatEvent e) {
     Player player = e.getPlayer();
     if (!player.hasPermission("chatex.allowchat")) {
         String msg = Locales.COMMAND_RESULT_NO_PERM.getString(player).replaceAll("%perm", "hyperchat.allowchat");
         player.sendMessage(msg);
         e.setCancelled(true);
         return;
     }
 }
}
