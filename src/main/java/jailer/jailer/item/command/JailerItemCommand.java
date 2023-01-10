package jailer.jailer.item.command;

import jailer.jailer.item.JailerItem;
import jailer.jailer.item.JailerItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public class JailerItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("admin")) {
            return true;
        }

        Inventory inventory = Bukkit.createInventory(null, 54, "Jailer's Items :)");
        int index = 0;



        for(Map.Entry<String, JailerItem> item : JailerItemFactory.classes.entrySet()) {
            inventory.setItem(index, item.getValue().toItemStack());
            index++;
        }

        player.openInventory(inventory);

        return true;
    }
}
