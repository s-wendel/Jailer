package jailer.jailer.item;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTItem;
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
    protected int sellPrice;

    public JailerItem(String name, Material material, List<String> description, JailerRarity rarity, ItemType itemType, int sellPrice) {
        this.name = name;
        this.material = material;
        this.description = description;
        this.rarity = rarity;
        this.itemType = itemType;
        this.sellPrice = sellPrice;
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

    public int getSellPrice() {
        return sellPrice;
    }

    public ItemStack toItemStack() {

        List<String> lore = new ArrayList<>();
        lore.add("<dark_gray>" + itemType.getName());

        if(sellPrice > 0) {
            lore.add("");
            lore.add("<gray>Sold for <gold>⛃<yellow>" + sellPrice);
        }

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

        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("name", name);

        return nbtItem.getItem();
    }

}
