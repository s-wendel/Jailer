package jailer.jailer.item.items.materials;

import jailer.jailer.item.ItemType;
import jailer.jailer.item.JailerItem;
import jailer.jailer.item.JailerRarity;
import org.bukkit.Material;

import java.util.Arrays;

public class Stone extends JailerItem {

    public Stone() {
        super(
                "Stone",
                Material.STONE,
                Arrays.asList("Yea yea yea yea"),
                JailerRarity.COMMON,
                ItemType.MATERIAL,
                6
        );
    }

}
