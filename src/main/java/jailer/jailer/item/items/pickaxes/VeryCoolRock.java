package jailer.jailer.item.items.pickaxes;

import jailer.jailer.item.*;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.Map;

public class VeryCoolRock extends JailerEquipmentItem {

    public VeryCoolRock() {
        super(
                "Very Cool Rock",
                Material.RAW_IRON,
                Arrays.asList("It's pretty cool..."),
                JailerRarity.COMMON,
                ItemType.PICKAXE,
                -1,
                0,
                null,
                null,
                Map.of(
                        JailerStat.MINING_SPEED, 2d,
                        JailerStat.FORTUNE, 3d
                )
        );
    }

    @Override
    public void abilityTrigger(AbilityAction action) {
        return;
    }
}
