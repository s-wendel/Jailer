package jailer.jailer.item.items.pickaxes;

import jailer.jailer.item.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
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
                        JailerStat.MINING_SPEED, "4",
                        JailerStat.FORTUNE, "5"
                )
        );
    }

    @Override
    public void abilityTrigger(AbilityAction action) {
        switch(action.type) {
            case BREAK_BLOCK:

                break;
        }
    }
}
