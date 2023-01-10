package seafront.seafront.blocks;

import seafront.seafront.item.ItemType;

import static org.bukkit.Sound.*;
import org.bukkit.Sound;

public enum JailerBlock {

    STONE(
            new LootTable<>(
                new LootTable.Entry<>(BlockDrop.STONE, 1)
            ),
            BLOCK_STONE_BREAK,
            ItemType.PICKAXE,
            30
    ),
    UNBREAKABLE(
            new LootTable(),
            BLOCK_STONE_BREAK,
            null,
            100000000
    );

    public final int durability;

    public final ItemType itemType;
    public final Sound sound;
    public final LootTable<BlockDrop> lootTable;


    JailerBlock(LootTable<BlockDrop> lootTable, Sound sound, ItemType itemType ,int durability) {
        this.itemType = itemType;
        this.lootTable = lootTable;
        this.durability = durability;
        this.sound = sound;
    }


}
