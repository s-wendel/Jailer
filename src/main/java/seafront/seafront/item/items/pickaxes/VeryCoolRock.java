package seafront.seafront.item.items.pickaxes;

import org.bukkit.Material;
import seafront.seafront.item.*;

import java.util.Arrays;
import java.util.Map;

public class VeryCoolRock extends SeafrontEquipmentItem {

    public VeryCoolRock() {
        super(
                "Very Cool Rock",
                Material.RAW_IRON,
                Arrays.asList("It's pretty cool..."),
                SeafrontRarity.COMMON,
                ItemType.PICKAXE,
                -1,
                0,
                null,
                null,
                Map.of(
                        SeafrontStat.MINING_SPEED, "1",
                        SeafrontStat.FORTUNE, "3"
                )
        );
    }

    @Override
    public void abilityTrigger(AbilityAction action) {
        return;
    }
}
