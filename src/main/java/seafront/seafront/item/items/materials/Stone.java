package seafront.seafront.item.items.materials;

import seafront.seafront.blocks.JailerBlock;
import seafront.seafront.item.ItemType;
import seafront.seafront.item.SeafrontBlockItem;
import seafront.seafront.item.SeafrontItem;
import seafront.seafront.item.SeafrontRarity;
import org.bukkit.Material;

import java.util.Arrays;

public class Stone extends SeafrontBlockItem {

    public Stone() {
        super(
                "Stone",
                Material.STONE,
                JailerBlock.STONE,
                Arrays.asList("Yea yea yea yea"),
                SeafrontRarity.COMMON,
                ItemType.MATERIAL,
                6
        );
    }

}
