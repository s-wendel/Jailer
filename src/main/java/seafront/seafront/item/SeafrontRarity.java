package seafront.seafront.item;

public enum SeafrontRarity {

    COMMON("Common", "<white>"),
    RARE("Rare", "<#4dd448>"),
    EPIC("Epic", "<#d4cd48>"),
    LEGENDARY("Legendary", "<#d46b48>");

    private String name;
    private String color;

    SeafrontRarity(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getRarityText() {
        return color + "<bold>" + name.toUpperCase();
    }

}
