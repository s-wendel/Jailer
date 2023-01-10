package jailer.jailer.item.items.admin;

import jailer.jailer.item.*;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MineWand  extends JailerEquipmentItem {

    public MineWand() {
        super(
                "Mine Wand",
                Material.BLAZE_ROD,
                List.of("Uhmm... I think you do magic with this"),
                JailerRarity.LEGENDARY,
                ItemType.PICKAXE,
                -1,
                0,
                "Select Coordinates",
                List.of("Right or Left click to select coords for mines"),
                Map.of()
        );
    }

    @Override
    public void abilityTrigger(AbilityAction action) {
        return;
    }
}