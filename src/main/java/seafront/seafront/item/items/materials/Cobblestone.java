package seafront.seafront.item.items.materials;

import seafront.seafront.item.ItemType;
import seafront.seafront.item.SeafrontItem;
import seafront.seafront.item.SeafrontRarity;
import org.bukkit.Material;

import java.util.Arrays;

public class Cobblestone extends SeafrontItem {

    public Cobblestone() {
        super(
                "Cobblestone",
                Material.COBBLESTONE,
                Arrays.asList("It's stone but it's cobbled?"),
                SeafrontRarity.COMMON,
                ItemType.MATERIAL,
                2
        );
    }
}
