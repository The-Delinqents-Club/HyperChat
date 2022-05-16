package com.github.kingultron99.hyperchat.util;

import com.github.kingultron99.hyperchat.HyperChat;
import io.papermc.paper.chat.ChatRenderer;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Renderer implements ChatRenderer {
    @Override
    public @NotNull Component render(@NotNull Player source, @NotNull Component displayName, @NotNull Component message, @NotNull Audience viewer) {
        String playerGroup = Perms.getPlayerGroup(source, Config.ROLES.getStringList());
        HyperChat.getInstance().getLogger().info(playerGroup);
        Component player;
        if (!Objects.equals(playerGroup, null)) {
            player = Component.text()
                    .append(MiniMessage.miniMessage().deserialize("<color:#555555>[</color>"))
                    .append(MiniMessage.miniMessage().deserialize(playerGroup))
                    .append(MiniMessage.miniMessage().deserialize("<color:#555555>]</color> "))
                    .append(displayName)
                    .build();
        } else {
            player = Component.text()
                    .append(displayName)
                    .build();
        }

        return Component.text()
                .append(player)
                .append(MiniMessage.miniMessage().deserialize(" <gradient:#D8B4FE:#9333EA>>></gradient> "))
                .append(MiniMessage.miniMessage().deserialize(PlainTextComponentSerializer.plainText().serialize(message)))
                .build();
    }
}