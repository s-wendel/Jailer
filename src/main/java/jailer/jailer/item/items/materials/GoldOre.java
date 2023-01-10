package jailer.jailer.item.items.materials;

import jailer.jailer.item.ItemType;
import jailer.jailer.item.JailerItem;
import jailer.jailer.item.JailerRarity;
import org.bukkit.Material;

import java.util.Arrays;

public class GoldOre extends JailerItem {

    public GoldOre() {
        super(
                "Gold Ore",
                Material.GOLD_ORE,
                Arrays.asList("I killed man back in 88 for 3 karats of gold"),
                JailerRarity.COMMON,
                ItemType.MATERIAL,
                5
        );
    }

}
