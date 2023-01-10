package seafront.seafront.item.items.materials;

import seafront.seafront.item.ItemType;
import seafront.seafront.item.SeafrontItem;
import seafront.seafront.item.SeafrontRarity;
import org.bukkit.Material;

import java.util.Arrays;

public class Coal extends SeafrontItem {

    public Coal() {
        super(
                "Coal",
                Material.COAL,
                Arrays.asList("Actually not a bad type of fuel"),
                SeafrontRarity.COMMON,
                ItemType.MATERIAL,
                6
        );
    }

}
