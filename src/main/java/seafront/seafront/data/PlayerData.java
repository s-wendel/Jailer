package seafront.seafront.data;

import seafront.seafront.item.SeafrontAbilityItem;
import seafront.seafront.item.SeafrontEquipmentItem;
import seafront.seafront.item.SeafrontStat;
import seafront.seafront.item.SeafrontStatData;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerData {

    private transient Player player;
    private transient SeafrontAbilityItem tool;
    private transient Map<SeafrontStat, Double> cache = new HashMap<>();
    private transient Map<SeafrontStat, Map<String, SeafrontStatData>> stats = new HashMap<>();

    public transient HashMap<String, Location> locationMap = new HashMap<>();


    public PlayerData() {
        for(SeafrontStat stat : SeafrontStat.values()) {
            setStat(stat, new SeafrontStatData(player, "Base Value", stat.getMaterial(), stat.getBaseValue(), -1));
        }
    }

    public PlayerData(Player player) {
        this();
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public SeafrontAbilityItem getTool() {
        return tool;
    }

    public void setTool(SeafrontAbilityItem tool) {
        this.tool = tool;

        if (tool == null || !(tool instanceof SeafrontEquipmentItem)) {
            for(SeafrontStat stat : SeafrontStat.values()) {
                stats.get(stat).remove("tool");
            }
            updateCache();
            return;
        }

        SeafrontEquipmentItem equipment = (SeafrontEquipmentItem) tool;


        Map<SeafrontStat, String> toolStats = equipment.getStats();

        for(SeafrontStat stat : toolStats.keySet()) {

            setStat(stat, new SeafrontStatData(player, "tool", tool.getMaterial(), toolStats.get(stat), -1));
        }
    }

    public void updateCache() {
        for(SeafrontStat stat : SeafrontStat.values()) {

            double value = 0d;
            List<SeafrontStatData> statsToBeRemoved = new ArrayList<>();
            List<Double> multipliers = new ArrayList<>();

            for(SeafrontStatData statData : stats.getOrDefault(stat, new HashMap<>()).values()) {

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

            for(SeafrontStatData removable : statsToBeRemoved) {
                stats.get(stat).remove(removable);
            }

            for(double multiplier : multipliers) {
                value *= multiplier;
            }

            cache.put(stat, value);
        }
    }

    public double getStatValue(SeafrontStat stat) {
        return cache.get(stat);
    }

    public void setStat(SeafrontStat stat, SeafrontStatData data) {
        Map<String, SeafrontStatData> statData = stats.getOrDefault(stat, new HashMap<>());

        statData.put(data.getName(), data);

        stats.put(stat, statData);
        updateCache();
    }

    public void setPlayer(Player player) { if (this.player == null) this.player = player; }

}
