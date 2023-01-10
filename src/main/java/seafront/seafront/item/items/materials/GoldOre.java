package seafront.seafront.item.items.materials;

import seafront.seafront.item.ItemType;
import seafront.seafront.item.SeafrontItem;
import seafront.seafront.item.SeafrontRarity;
import org.bukkit.Material;

import java.util.Arrays;

public class GoldOre extends SeafrontItem {

    public GoldOre() {
        super(
                "Gold Ore",
                Material.GOLD_ORE,
                Arrays.asList("I killed man back in 88 for 3 karats of gold"),
                SeafrontRarity.COMMON,
                ItemType.MATERIAL,
                5
        );
    }

}
