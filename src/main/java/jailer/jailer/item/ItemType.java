package jailer.jailer.item;

public enum ItemType {

    MATERIAL("Material"),
    PICKAXE("Pickaxe"),
    AXE("Axe")
    ;

    private String name;

    ItemType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
