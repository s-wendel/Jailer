package jailer.jailer.display;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;

public class Formatter {


    private final MiniMessage format;
    private final LegacyComponentSerializer legacyFormat;

    public Formatter(TagResolver... resolvers) {
        TagResolver.Builder resolver = TagResolver.builder()
                .resolver(StandardTags.color())
                .resolvers(StandardTags.decorations());

        for (TagResolver resolve : resolvers) {
            resolver.resolvers(resolve);
        }
        this.format = MiniMessage.builder()
                .tags(resolver.build())
                .build();
        legacyFormat = LegacyComponentSerializer.builder().useUnusualXRepeatedCharacterHexFormat().hexColors().character('&').build();
    }

    public Component text(String text) {
        return format.deserialize(text);
    }
    public String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', legacyFormat.serialize(format.deserialize(text)));
    }

}
