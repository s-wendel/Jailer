package jailer.jailer.data;

import jailer.jailer.item.JailerEquipmentItem;
import jailer.jailer.item.JailerStat;
import jailer.jailer.item.JailerStatData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerData {

    private transient Player player;
    private transient JailerEquipmentItem tool;
    private transient Map<JailerStat, Double> cache;
    private transient Map<JailerStat, Map<String, JailerStatData>> stats;

    public PlayerData(Player player) {
        this.player = player;

        cache = new HashMap<>();
        stats = new HashMap<>();

        for(JailerStat stat : JailerStat.values()) {
            setStat(stat, new JailerStatData("Base Value", stat.getMaterial(), stat.getBaseValue()));
        }
    }

    public Player getPlayer() {
        return player;
    }

    public JailerEquipmentItem getTool() {
        return tool;
    }

    public void setTool(JailerEquipmentItem tool) {
        this.tool = tool;

        Map<JailerStat, Double> toolStats = tool.getStats();

        for(JailerStat stat : toolStats.keySet()) {
            setStat(stat, new JailerStatData(tool.getName(), tool.getMaterial(), toolStats.get(stat)));
        }
    }

    public void updateCache() {
        for(JailerStat stat : JailerStat.values()) {

            double value = 0d;

            for(JailerStatData statData : stats.getOrDefault(player, new HashMap<>()).values()) {
                value += statData.getValue();
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
