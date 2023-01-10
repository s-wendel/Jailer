package jailer.jailer.item.items.materials;

import jailer.jailer.item.ItemType;
import jailer.jailer.item.JailerItem;
import jailer.jailer.item.JailerRarity;
import org.bukkit.Material;

import java.util.Arrays;

public class Coal extends JailerItem {

    public Coal() {
        super(
                "Coal",
                Material.COAL,
                Arrays.asList("Actually not a bad type of fuel"),
                JailerRarity.COMMON,
                ItemType.MATERIAL,
                6
        );
    }

}
