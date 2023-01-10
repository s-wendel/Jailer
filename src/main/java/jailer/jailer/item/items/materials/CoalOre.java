package jailer.jailer.item.items.materials;

import jailer.jailer.item.ItemType;
import jailer.jailer.item.JailerItem;
import jailer.jailer.item.JailerRarity;
import org.bukkit.Material;

import java.util.Arrays;

public class CoalOre extends JailerItem {

    public CoalOre() {
        super(
                "Coal Ore",
                Material.COAL_ORE,
                Arrays.asList("How do you smelt the fuel?", "Wood? Oh yea"),
                JailerRarity.COMMON,
                ItemType.MATERIAL,
                3
        );
    }

}
