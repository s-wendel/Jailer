package jailer.jailer.item;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class JailerItem {

    protected String name;
    protected Material material;
    protected List<String> description;
    protected JailerRarity rarity;
    protected ItemType itemType;

    public JailerItem(String name, Material material, List<String> description, JailerRarity rarity, ItemType itemType) {
        this.name = name;
        this.material = material;
        this.description = description;
        this.rarity = rarity;
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

    public List<String> getDescription() {
        return description;
    }

    public JailerRarity getRarity() {
        return rarity;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public ItemStack toItemStack() {

        List<String> lore = new ArrayList<>();
        lore.add("<dark_gray>" + itemType.getName());
        lore.add("");
        for(String line : description) {
            lore.add("<#3b305c>" + line);
        }
        lore.add("");
        lore.add(rarity.getRarityText());

        ItemStack item = new ItemBuilder(material)
                .setName(rarity.getColor() + name)
                .setLore(lore.toArray(new String[0]))
                .toItemStack();

        ReadWriteNBT nbt = NBT.itemStackToNBT(item);
        nbt.setString("name", name);

        return item;
    }

}
