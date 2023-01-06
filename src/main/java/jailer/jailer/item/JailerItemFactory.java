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
import java.util.HashSet;
import java.util.Set;

public class JailerItemFactory {

    public static JailerItem jailerItemFromItemStack(ItemStack itemStack) {
        NBTItem nbtItem = new NBTItem(itemStack);

        for(JailerItem item : getAllJailerItems()) {
            if(item.getName().equalsIgnoreCase(nbtItem.getString("name"))) {
                return item;
            }
        }

        return null;
    }

    public static JailerEquipmentItem jailerEquipmentItemFromItemStack(ItemStack itemStack) {
        NBTItem nbtItem = new NBTItem(itemStack);

        for(JailerEquipmentItem item : getAllJailerEquipmentItems()) {
            if(item.getName().equalsIgnoreCase(nbtItem.getString("name"))) {
                return item;
            }
        }

        return null;
    }

    public static Set<JailerItem> getAllJailerItems() {
        Set<JailerItem> equipment = new HashSet<>();

        Reflections reflections = new Reflections("jailer.jailer.item.items");
        Set<Class<? extends JailerItem>> classes = reflections.getSubTypesOf(JailerItem.class);

        for(Class<?> clazz : classes) {
            try {
                JailerItem item = (JailerItem) clazz.getConstructor().newInstance();
                equipment.add(item);
            } catch(Exception exception) {}
        }

        return equipment;
    }

    public static Set<JailerEquipmentItem> getAllJailerEquipmentItems() {
        Set<JailerEquipmentItem> equipment = new HashSet<>();

        Reflections reflections = new Reflections("jailer.jailer.item.items");
        Set<Class<? extends JailerEquipmentItem>> classes = reflections.getSubTypesOf(JailerEquipmentItem.class);

        for(Class<?> clazz : classes) {
            try {
                JailerEquipmentItem item = (JailerEquipmentItem) clazz.getConstructor().newInstance();
                equipment.add(item);
            } catch(Exception exception) {}
        }

        return equipment;
    }

}
