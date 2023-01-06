package jailer.jailer.item.items.materials;

import jailer.jailer.item.ItemType;
import jailer.jailer.item.JailerItem;
import jailer.jailer.item.JailerRarity;
import org.bukkit.Material;

import java.util.Arrays;

public class Cobblestone extends JailerItem {

    public Cobblestone() {
        super(
                "Cobblestone",
                Material.COBBLESTONE,
                Arrays.asList(),
                JailerRarity.COMMON,
                ItemType.MATERIAL,
                2
        );
    }
}
