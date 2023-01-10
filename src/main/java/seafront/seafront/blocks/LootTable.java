package seafront.seafront.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LootTable<T> {

    private final List<Entry<T>> lootTable;
    private Integer totalWeight = 0;


    public LootTable(Entry<T>... entries) {
        this.lootTable = Arrays.stream(entries).toList();
        for ( Entry<T> entry : lootTable) {
            totalWeight += entry.weight;
        }
    }
    public LootTable(List<Entry<T>> lootTable) {
        this.lootTable = lootTable;
        for ( Entry<T> entry : lootTable) {
            totalWeight += entry.weight;
        }
    }

    public LootTable() {
        this.lootTable = new ArrayList<>();
    }


    public void addChance(T data, Integer weight) {
        lootTable.add(new Entry<T>(data, weight));
        totalWeight += weight;
    }

    public T roll() {
        Integer currentItemUpperBound = 0;
        Integer randomValue = new Random().nextInt(totalWeight);
        for ( Entry<T> entry : lootTable) {
            currentItemUpperBound += entry.weight;
            if (randomValue < currentItemUpperBound) return entry.data;
        }

        return lootTable.get(0).data;
    }

    public static class Entry<T> {
        public T data;
        public Integer weight;

        public Entry(T data, Integer weight) {
            this.data = data;
            this.weight = weight;
        }

    }
}
