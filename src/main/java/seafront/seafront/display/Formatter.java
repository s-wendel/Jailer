package seafront.seafront.display;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;

public class Formatter {


    private static final MiniMessage format = MiniMessage.builder()
            .tags(TagResolver.builder()
                    .resolver(StandardTags.color())
                    .resolvers(StandardTags.decorations()).build())
            .build();
    private static final LegacyComponentSerializer legacyFormat = LegacyComponentSerializer.builder().useUnusualXRepeatedCharacterHexFormat().hexColors().character('&').build();

    public static Component text(String text) {
        return format.deserialize("<i:false>" + text);
    }
    public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', legacyFormat.serialize(format.deserialize("<i:false>" + text)));
    }

}
