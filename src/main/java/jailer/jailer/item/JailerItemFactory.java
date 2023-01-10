package jailer.jailer.item;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import jailer.jailer.item.items.pickaxes.LitTorch;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class JailerItemFactory {


    public static HashMap<String, JailerItem> classes = new HashMap<>();

    static {
        Set<Class<? extends JailerItem>> classList = new Reflections("jailer.jailer.item.items").getSubTypesOf(JailerItem.class);
        for(Class<? extends JailerItem> clazz : classList) {
            try {
                JailerItem item = clazz.getConstructor().newInstance();
                classes.put(item.name, item);
            } catch(Exception exception) {}
        }

    }

    public static JailerItem jailerItemFromItemStack(ItemStack itemStack) {

        if(itemStack == null) {
            return null;
        }

        return classes.get(new NBTItem(itemStack).getString("name"));
    }



}
