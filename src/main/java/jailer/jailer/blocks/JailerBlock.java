package jailer.jailer.blocks;

import org.bukkit.Material;
import static org.bukkit.Sound.*;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public enum JailerBlock {
    Stone(new LootTable<>(
            new LootTable.Entry<>(BlockDrop.STONE, 1)
    ), BLOCK_STONE_BREAK,30),
    Unbreakable(new LootTable(), BLOCK_STONE_BREAK,100000000);

    public final int durability;
    public final Sound sound;
    public final LootTable<BlockDrop> lootTable;

    JailerBlock(LootTable<BlockDrop> lootTable, Sound sound, int durability) {
        this.lootTable = lootTable;
        this.durability = durability;
        this.sound = sound;
    }


}
