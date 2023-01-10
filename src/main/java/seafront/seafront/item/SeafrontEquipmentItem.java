package seafront.seafront.item;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SeafrontEquipmentItem extends SeafrontAbilityItem {

    protected Map<SeafrontStat, String> stats;
    protected int enchantmentSlots;

    public SeafrontEquipmentItem(String name, Material material, List<String> description, SeafrontRarity rarity, ItemType itemType, int sellPrice, int enchantmentSlots, String abilityName, List<String> abilityDescription, Map<SeafrontStat, String> stats) {
        super(name, material, description, rarity, itemType, sellPrice, abilityName, abilityDescription);

        this.enchantmentSlots = enchantmentSlots;
        this.stats = stats;
    }

    public int getEnchantmentSlots() {
        return enchantmentSlots;
    }

    public Map<SeafrontStat, String> getStats() {
        return stats;
    }

    @Override
    public ItemStack toItemStack() {

        List<String> lore = new ArrayList<>();
        lore.add("<dark_gray>" + itemType.getName());

        if(!stats.isEmpty()) {
            lore.add("");
            for(SeafrontStat stat : stats.keySet()) {
                lore.add(stat.getFormattedStat(stats.get(stat)));
            }
        }

        if(hasAbility()) {
            lore.add("");
            lore.add("<gold><bold>ABILITY</bold> <yellow>" + abilityName);

            for(String line : abilityDescription) {
                lore.add("<dark_gray>➡ <white>" + line);
            }

        }

        if(enchantmentSlots > 0) {
            lore.add("");

            String line = "";
            for(int i = 0; i < enchantmentSlots; i++) {
                line += "<dark_gray>[<gray>?<dark_gray>] ";
            }

            lore.add(line.substring(0, line.length() - 1));
        }

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
