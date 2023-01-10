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
    PLANT("Plant"),
    PLANTER("Planter"),
    CONSUMABLE("Consumable")
    ;

    private String name;

    ItemType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
