package jailer.jailer.item;

import org.bukkit.Material;

import static jailer.jailer.display.Formatter.*;

public enum JailerStat {

    MINING_SPEED("Mining Speed", "<#f5d742>", "⛏", 1d, Material.IRON_PICKAXE),
    FORTUNE("Fortune", "<#8feb34>", "☘", 0d, Material.GOLD_INGOT),
    FISHING_SPEED("Fishing Speed", "<#1c66e6>", "\uD83C\uDFA3", 0d, Material.FISHING_ROD),
    FISH_WEIGHT("Fish Weight", "<#62789c>", "☄", 0d, Material.COD),
    WALK_SPEED("Walk Speed", "<#b09474>", "→", 100d, Material.LEATHER_BOOTS),
    PLANT_GROWTH("Plant Growth Chance", "<#177822>", "☀", 10d, Material.BEETROOT_SEEDS)
    ;

    private String name;
    private String color;
    private String icon;
    private double base;
    private Material material;

    JailerStat(String name, String color, String icon, double base, Material material) {
        this.name = name;
        this.color = color;
        this.icon = icon;
        this.base = base;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getIcon() {
        return icon;
    }

    public String getFormattedStat(double value, boolean percentage) {

        String number = String.valueOf(value);

        if(number.endsWith(".0")) {
            number = String.valueOf(Integer.parseInt(number.replace(".0", "")));
        }

        return color + (value >= 0 ? "+" : "-") + (number) + (percentage ? "% " : " ") + "<gray>" + name + " " + color + icon;
    }

    public double getBaseValue() {
        return base;
    }

    public Material getMaterial() {
        return material;
    }

}
