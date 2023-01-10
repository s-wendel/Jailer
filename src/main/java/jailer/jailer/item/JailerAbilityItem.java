package jailer.jailer.item;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class JailerAbilityItem extends JailerItem {

    protected String abilityName;
    protected List<String> abilityDescription;

    public JailerAbilityItem(String name, Material material, List<String> description, JailerRarity rarity, ItemType itemType, int sellPrice, String abilityName, List<String> abilityDescription) {
        super(name, material, description, rarity, itemType, sellPrice);

        this.abilityName = abilityName;
        this.abilityDescription = abilityDescription;
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

    public abstract void abilityTrigger(AbilityAction action);

    @Override
    public ItemStack toItemStack() {

        List<String> lore = new ArrayList<>();
        lore.add("<dark_gray>" + itemType.getName());

        if(hasAbility()) {
            lore.add("");
            lore.add("<gold><bold>ABILITY</bold> <yellow>" + abilityName);

            for(int i = 0; i < abilityDescription.size(); i++) {

                lore.add("<dark_gray>" + (i == 0 ? "➡" : " ") + " <white>" + abilityDescription.get(i));
            }

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
