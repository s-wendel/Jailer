package jailer.jailer.item;

public enum ItemType {

    MATERIAL("Material"),
    PICKAXE("Pickaxe"),
    AXE("Axe"),
    ROD("Rod"),
    ENCHANTMENT("Enchantment"),
    WEAPON("Weapon"),
    HELMET("Helmet"),
    CHESTPLATE("Chestplate"),
    LEGGINGS("Leggings"),
    BOOTS("Boots"),
    ;

    private String name;

    ItemType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
