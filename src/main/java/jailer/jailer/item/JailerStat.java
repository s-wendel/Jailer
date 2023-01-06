package jailer.jailer.item;

import static jailer.jailer.display.Formatter.*;

public enum JailerStat {

    MINING_SPEED("Mining Speed", "<#f5d742>", "⛏"),
    FORTUNE("Fortune", "<#8feb34>", "☘"),
    FISHING_SPEED("Fishing Speed", "<#1c66e6>", "\uD83C\uDFA3"),
    FISH_WEIGHT("Fish Weight", "<#62789c>", "☄"),
    WALK_SPEED("Walk Speed", "<#b09474>", "→"),
    PLANT_GROWTH("Plant Growth Chance", "<#177822>", "☀")
    ;

    private String name;
    private String color;
    private String icon;

    JailerStat(String name, String color, String icon) {
        this.name = name;
        this.color = color;
        this.icon = icon;
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

}
