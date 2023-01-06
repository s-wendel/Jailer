package jailer.jailer;

import jailer.jailer.blocks.LootTable;
import jailer.jailer.data.PlayerData;
import jailer.jailer.data.serialize.GsonSerializer;
import jailer.jailer.data.serialize.PlayerSerializer;
import jailer.jailer.data.storage.FileStorageProvider;
import jailer.jailer.data.storage.StorageManager;
import jailer.jailer.display.Formatter;
import jailer.jailer.events.BlockBreaking;
import jailer.jailer.item.command.JailerItemCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedHashMap;

public final class Jailer extends JavaPlugin {

    private static Jailer instance;

    public final StorageManager<Player, PlayerData> playerData =
            new StorageManager(
                    new FileStorageProvider(
                        new PlayerSerializer(),
                            new GsonSerializer(),
                            getDataFolder())
            );


    @Override
    public void onEnable() {
        instance = this;

        getCommand("items").setExecutor(new JailerItemCommand());

        getServer().getPluginManager().registerEvents(new BlockBreaking(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Jailer getInstance() {
        return instance;
    }

}
