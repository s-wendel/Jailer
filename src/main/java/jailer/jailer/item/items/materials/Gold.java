package jailer.jailer.item.items.materials;

import jailer.jailer.item.ItemType;
import jailer.jailer.item.JailerItem;
import jailer.jailer.item.JailerItemFactory;
import jailer.jailer.item.JailerRarity;
import org.bukkit.Material;

import java.util.Arrays;

public class Gold extends JailerItem {

    public Gold() {
        super(
                "Gold",
                Material.GOLD_INGOT,
                Arrays.asList("January 30th, 1988."),
                JailerRarity.COMMON,
                ItemType.MATERIAL,
                15
        );
    }

}
