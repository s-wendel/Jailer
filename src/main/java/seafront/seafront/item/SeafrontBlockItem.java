package seafront.seafront.item;

import org.bukkit.Material;
import seafront.seafront.blocks.JailerBlock;

import java.util.List;

public class SeafrontBlockItem extends SeafrontItem{

    private JailerBlock jailerBlock;
    public SeafrontBlockItem(String name, Material material, JailerBlock jailerBlock, List<String> description, SeafrontRarity rarity, ItemType itemType, int sellPrice) {
        super(name, material, description, rarity, itemType, sellPrice);
        this.jailerBlock = jailerBlock;
    }

    public JailerBlock getJailerBlock() {
        return this.jailerBlock;
    }
}
