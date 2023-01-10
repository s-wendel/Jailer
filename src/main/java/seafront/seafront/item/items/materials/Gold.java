package seafront.seafront.item.items.materials;

import seafront.seafront.item.ItemType;
import seafront.seafront.item.SeafrontItem;
import seafront.seafront.item.SeafrontRarity;
import org.bukkit.Material;

import java.util.Arrays;

public class Gold extends SeafrontItem {

    public Gold() {
        super(
                "Gold",
                Material.GOLD_INGOT,
                Arrays.asList("January 30th, 1988."),
                SeafrontRarity.COMMON,
                ItemType.MATERIAL,
                15
        );
    }

}
