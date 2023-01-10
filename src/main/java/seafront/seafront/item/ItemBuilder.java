package seafront.seafront.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import seafront.seafront.display.Formatter;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta itemMeta;

    public ItemBuilder(Material material) {
        this.item = new ItemStack(material);
        this.itemMeta = item.getItemMeta();
    }

    public ItemBuilder setName(String name) {
        itemMeta.displayName(Formatter.text(name));
        return this;
    }

    public ItemBuilder setLore(String... lines) {
        List<Component> newLines = new ArrayList<>();

        for(String line : lines) {
            newLines.add(Formatter.text(line));
        }

        itemMeta.lore(newLines);

        return this;
    }

    public ItemStack toItemStack() {
        item.setItemMeta(itemMeta);
        return item;
    }

}