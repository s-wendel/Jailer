package jailer.jailer.blocks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public enum JailerBlock {
    Stone(new LootTable<>(
            new LootTable.Entry<>(BlockDrop.Stone, 1)
    ), 10);

    public final Integer durability;
    public final LootTable<BlockDrop> lootTable;

    JailerBlock(LootTable<BlockDrop> lootTable, Integer durability) {
        this.lootTable = lootTable;
        this.durability = durability;
    }


}
