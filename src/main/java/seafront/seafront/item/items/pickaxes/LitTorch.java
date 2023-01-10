package seafront.seafront.item.items.pickaxes;

import org.bukkit.Material;
import seafront.seafront.item.*;

import java.util.Arrays;
import java.util.Map;

public class LitTorch extends SeafrontEquipmentItem {

    public LitTorch() {
        super(
                "Lit Torch",
                Material.TORCH,
                Arrays.asList("The torch is so lit omg!!!"),
                SeafrontRarity.RARE,
                ItemType.PICKAXE,
                50,
                0,
                "So Hot",
                Arrays.asList("Automatically smelts mined items"),
                Map.of(
                        SeafrontStat.MINING_SPEED, "4",
                        SeafrontStat.FORTUNE, "5"
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
