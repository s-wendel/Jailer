package jailer.jailer.gardening;

import jailer.jailer.blocks.LootTable;
import jailer.jailer.item.JailerItem;
import org.bukkit.Color;
import org.bukkit.Material;

public abstract class Plant {

    protected String name;
    protected Material[] stages; // [0] -> < 50% [1] >= 50% [2] = 100%
    protected Color color; // Particle color emitting
    protected int rolls; // The amount of times the loot table is rolled
    protected LootTable<JailerItem> lootTable;
    protected int growthIntervals; // The amount of successful ticks to grow

    public Plant(String name, Material[] stages, Color color, int rolls, LootTable<JailerItem> lootTable, int growthIntervals) {
        this.name = name;
        this.stages = stages;
        this.color = color;
        this.rolls = rolls;
        this.lootTable = lootTable;
        this.growthIntervals = growthIntervals;
    }

    public String getName() {
        return name;
    }

    public Material[] getStages() {
        return stages;
    }

    public Color getColor() {
        return color;
    }

    public int getRolls() {
        return rolls;
    }

    public LootTable<JailerItem> getLootTable() {
        return lootTable;
    }

    public int getGrowthIntervals() {
        return growthIntervals;
    }

    public void harvestPlanter() {
        //TODO
    }


}
