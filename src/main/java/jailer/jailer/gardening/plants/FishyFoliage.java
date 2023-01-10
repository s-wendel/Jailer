package jailer.jailer.gardening.plants;

import jailer.jailer.blocks.LootTable;
import jailer.jailer.gardening.Plant;
import org.bukkit.Color;
import org.bukkit.Material;

public class FishyFoliage extends Plant {

    public FishyFoliage() {
        super(
                "Fishy Foliage",
                new Material[] { Material.NETHER_SPROUTS, Material.TWISTING_VINES, Material.WARPED_ROOTS },
                Color.fromRGB(17, 158, 117),
                12,
                new LootTable<>(
                        new LootTable.Entry<>(null, 4),
                        new LootTable.Entry<>(null, 100)),
                17
        );
    }

}
