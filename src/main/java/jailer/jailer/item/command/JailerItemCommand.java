package jailer.jailer.item.command;

import jailer.jailer.item.JailerEquipmentItem;
import jailer.jailer.item.JailerItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class JailerItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        Inventory inventory = Bukkit.createInventory(null, 54, "Jailer's Items :)");
        int index = 0;

        for(JailerEquipmentItem item : JailerItemFactory.getAllEquipmentItems()) {
            inventory.setItem(index, item.toItemStack());
            index++;
        }

        player.openInventory(inventory);

        return true;
    }
}
