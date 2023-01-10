package jailer.jailer.gardening.plants;

import jailer.jailer.blocks.LootTable;
import jailer.jailer.gardening.Plant;
import org.bukkit.Color;
import org.bukkit.Material;

public class CrystalSprout extends Plant {

    public CrystalSprout() {
        super(
                "Crystal Sprout",
                new Material[] { Material.SMALL_AMETHYST_BUD, Material.LARGE_AMETHYST_BUD, Material.AMETHYST_CLUSTER },
                Color.fromRGB(179, 142, 243),
                4,
                new LootTable<>(
                        new LootTable.Entry<>(null, 4),
                        new LootTable.Entry<>(null, 100)),
                12
        );
    }

}
