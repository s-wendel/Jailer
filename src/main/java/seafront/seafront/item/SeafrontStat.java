package seafront.seafront.item;

import org.bukkit.Material;

public enum SeafrontStat {

    MINING_SPEED("Mining Speed", "<#f5d742>", "⛏", "1", Material.IRON_PICKAXE),
    FORTUNE("Fortune", "<#8feb34>", "☘", "0", Material.GOLD_INGOT),
    FISHING_SPEED("Fishing Speed", "<#1c66e6>", "\uD83C\uDFA3", "0", Material.FISHING_ROD),
    FISH_WEIGHT("Fish Weight", "<#62789c>", "☄", "0", Material.COD),
    WALK_SPEED("Walk Speed", "<#b09474>", "→", "100", Material.LEATHER_BOOTS),
    PLANT_GROWTH("Plant Growth Chance", "<#177822>", "☀", "10", Material.BEETROOT_SEEDS)
    ;

    private String name;
    private String color;
    private String icon;
    private String base;
    private Material material;

    SeafrontStat(String name, String color, String icon, String base, Material material) {
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

    public String getFormattedStat(String value) {

        boolean multiplied = value.endsWith("x");
        value = value.replace("x", "");
        double number = Double.parseDouble(value);

        return color + (number >= 0 ? "+" : "") + (value.endsWith(".0") ? Integer.parseInt(value.replace(".0", "")) : value) + (multiplied ? "x" : "") + " " + "<gray>" + name + " " + color + icon;
    }

    public String getBaseValue() {
        return base;
    }

    public Material getMaterial() {
        return material;
    }

}
