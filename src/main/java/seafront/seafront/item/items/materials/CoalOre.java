package seafront.seafront.item.items.materials;

import seafront.seafront.item.ItemType;
import seafront.seafront.item.SeafrontItem;
import seafront.seafront.item.SeafrontRarity;
import org.bukkit.Material;

import java.util.Arrays;

public class CoalOre extends SeafrontItem {

    public CoalOre() {
        super(
                "Coal Ore",
                Material.COAL_ORE,
                Arrays.asList("How do you smelt the fuel?", "Wood? Oh yea"),
                SeafrontRarity.COMMON,
                ItemType.MATERIAL,
                3
        );
    }

}
