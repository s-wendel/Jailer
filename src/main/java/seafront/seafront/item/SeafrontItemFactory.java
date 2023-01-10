package seafront.seafront.item;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;

public class SeafrontItemFactory {


    public static HashMap<String, SeafrontItem> classes = new HashMap<>();

    static {
        Set<Class<? extends SeafrontItem>> classList = new Reflections("jailer.jailer.item.items").getSubTypesOf(SeafrontItem.class);
        for(Class<? extends SeafrontItem> clazz : classList) {
            try {
                SeafrontItem item = clazz.getConstructor().newInstance();
                classes.put(item.name, item);
            } catch(Exception exception) {}
        }

    }

    public static SeafrontItem jailerItemFromItemStack(ItemStack itemStack) {

        if(itemStack == null) {
            return null;
        }

        return classes.get(new NBTItem(itemStack).getString("name"));
    }



}
