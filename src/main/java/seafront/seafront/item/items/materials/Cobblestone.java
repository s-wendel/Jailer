package seafront.seafront.item.items.materials;

import seafront.seafront.blocks.JailerBlock;
import seafront.seafront.item.ItemType;
import seafront.seafront.item.SeafrontBlockItem;
import seafront.seafront.item.SeafrontItem;
import seafront.seafront.item.SeafrontRarity;
import org.bukkit.Material;

import java.util.Arrays;

public class Cobblestone extends SeafrontBlockItem {

    public Cobblestone() {
        super(
                "Cobblestone",
                Material.COBBLESTONE,
                JailerBlock.STONE,
                Arrays.asList("It's stone but it's cobbled?"),
                SeafrontRarity.COMMON,
                ItemType.MATERIAL,
                2
        );
    }
}
