package seafront.seafront.blocks;

import seafront.seafront.item.SeafrontItem;
import seafront.seafront.item.items.materials.Cobblestone;

public enum BlockDrop {

    STONE(new Cobblestone(), null);

    public final SeafrontItem item;
    public final SeafrontItem compactedItem;

    BlockDrop(SeafrontItem item, SeafrontItem compactedItem) {
        this.item = item;
        this.compactedItem = compactedItem;
    }

    public boolean canCompact() {
        return (compactedItem != null);
    }

}
