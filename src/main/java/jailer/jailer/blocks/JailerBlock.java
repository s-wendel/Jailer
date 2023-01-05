package jailer.jailer.blocks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public enum JailerBlock {
    Stone(new LootTable<>(
            new LootTable.Entry<>(BlockDrop.Stone, 1)
    ), 10),
    Unbreakable(new LootTable(), 100000000);

    public final int durability;
    public final LootTable<BlockDrop> lootTable;

    JailerBlock(LootTable<BlockDrop> lootTable, int durability) {
        this.lootTable = lootTable;
        this.durability = durability;
    }


}
