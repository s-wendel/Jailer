package jailer.jailer.data;

import jailer.jailer.item.JailerAbilityItem;
import jailer.jailer.item.JailerEquipmentItem;
import jailer.jailer.item.JailerStat;
import jailer.jailer.item.JailerStatData;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerData {

    private transient Player player;
    private transient JailerAbilityItem tool;
    private transient Map<JailerStat, Double> cache;
    private transient Map<JailerStat, Map<String, JailerStatData>> stats;

    public PlayerData(Player player) {
        this.player = player;

        cache = new HashMap<>();
        stats = new HashMap<>();

        for(JailerStat stat : JailerStat.values()) {
            setStat(stat, new JailerStatData(player, "Base Value", stat.getMaterial(), stat.getBaseValue(), -1));
        }
    }

    public Player getPlayer() {
        return player;
    }

    public JailerAbilityItem getTool() {
        return tool;
    }

    public void setTool(JailerAbilityItem tool) {
        this.tool = tool;

        if (tool == null || !(tool instanceof JailerEquipmentItem)) {
            for(JailerStat stat : JailerStat.values()) {
                stats.get(stat).remove("tool");
            }
            updateCache();
            return;
        }

        JailerEquipmentItem equipment = (JailerEquipmentItem) tool;


        Map<JailerStat, String> toolStats = equipment.getStats();

        for(JailerStat stat : toolStats.keySet()) {

            setStat(stat, new JailerStatData(player, "tool", tool.getMaterial(), toolStats.get(stat), -1));
        }
    }

    public void updateCache() {
        for(JailerStat stat : JailerStat.values()) {

            double value = 0d;
            List<JailerStatData> statsToBeRemoved = new ArrayList<>();
            List<Double> multipliers = new ArrayList<>();

            for(JailerStatData statData : stats.getOrDefault(stat, new HashMap<>()).values()) {

                if(statData.hasExpired()) {

                    statsToBeRemoved.add(statData);

                } else {

                    String stringValue = statData.getValue();

                    if(stringValue.endsWith("x")) {
                        multipliers.add(Double.parseDouble(stringValue.replace("x", "")));
                    } else {
                        value += Double.parseDouble(stringValue);
                    }
                }

            }

            for(JailerStatData removable : statsToBeRemoved) {
                stats.get(stat).remove(removable);
            }

            for(double multiplier : multipliers) {
                value *= multiplier;
            }

            cache.put(stat, value);
        }
    }

    public double getStatValue(JailerStat stat) {
        return cache.get(stat);
    }

    public void setStat(JailerStat stat, JailerStatData data) {
        Map<String, JailerStatData> statData = stats.getOrDefault(stat, new HashMap<>());

        statData.put(data.getName(), data);

        stats.put(stat, statData);
        updateCache();
    }

    public void setPlayer(Player player) { if (this.player == null) this.player = player; }

}
