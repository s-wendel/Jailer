package jailer.jailer.blocks;

import jailer.jailer.Jailer;
import jailer.jailer.item.JailerItem;
import jailer.jailer.item.items.materials.Cobblestone;

public enum BlockDrop {

    STONE(new Cobblestone(), null);

    public final JailerItem item;
    public final JailerItem compactedItem;

    BlockDrop(JailerItem item, JailerItem compactedItem) {
        this.item = item;
        this.compactedItem = compactedItem;
    }

    public boolean canCompact() {
        return (compactedItem != null);
    }

}
