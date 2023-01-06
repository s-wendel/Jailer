package jailer.jailer.item.items.pickaxes;

import jailer.jailer.item.*;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LitTorch extends JailerEquipmentItem {

    public LitTorch() {
        super(
                "Lit Torch",
                Material.TORCH,
                Arrays.asList("The torch is so lit omg!!!"),
                JailerRarity.RARE,
                ItemType.PICKAXE,
                50,
                0,
                "So Hot",
                Arrays.asList("Automatically smelts mined items"),
                Map.of(
                        JailerStat.MINING_SPEED, 4d,
                        JailerStat.FORTUNE, 5d
                )
        );
    }

    @Override
    public void abilityTrigger(AbilityAction action) {
        switch(action) {
            case BREAK_BLOCK:

                break;
        }
    }
}
