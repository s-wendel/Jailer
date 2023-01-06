package jailer.jailer.item;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class JailerEquipmentItem extends JailerItem {

    protected String abilityName;
    protected List<String> abilityDescription;
    protected Map<JailerStat, Double> stats;
    protected int enchantmentSlots;

    public JailerEquipmentItem(String name, Material material, List<String> description, JailerRarity rarity, ItemType itemType, int sellPrice, int enchantmentSlots, String abilityName, List<String> abilityDescription, Map<JailerStat, Double> stats) {
        super(name, material, description, rarity, itemType, sellPrice);

        this.enchantmentSlots = enchantmentSlots;
        this.abilityName = abilityName;
        this.abilityDescription = abilityDescription;
        this.stats = stats;
    }

    public boolean hasAbility() {
        return abilityName != null;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public List<String> getAbilityDescription() {
        return abilityDescription;
    }

    public Map<JailerStat, Double> getStats() {
        return stats;
    }

    public abstract void abilityTrigger(AbilityAction action);

    public ItemStack toItemStack() {

        List<String> lore = new ArrayList<>();
        lore.add("<dark_gray>" + itemType.getName());

        if(!stats.isEmpty()) {
            lore.add("");
            for(JailerStat stat : stats.keySet()) {
                lore.add(stat.getFormattedStat(stats.get(stat), false));
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
