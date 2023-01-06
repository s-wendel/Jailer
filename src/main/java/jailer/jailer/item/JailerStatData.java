package jailer.jailer.item;

import org.bukkit.Material;

public class JailerStatData {

    /**
     * This class is used for /stats command so it can show where all your stats come from
     */

    private String name;
    private Material icon;
    private double value;

    public JailerStatData(String name, Material icon, double value) {
        this.name = name;
        this.icon = icon;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Material getIcon() {
        return icon;
    }

    public double getValue() {
        return value;
    }

}
