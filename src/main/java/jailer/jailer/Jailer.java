package jailer.jailer;

import jailer.jailer.data.PlayerData;
import jailer.jailer.data.listener.PlayerDataListener;
import jailer.jailer.data.listener.PlayerToolAutoEquipListener;
import jailer.jailer.data.serialize.GsonSerializer;
import jailer.jailer.data.serialize.PlayerSerializer;
import jailer.jailer.data.storage.FileStorageProvider;
import jailer.jailer.data.storage.StorageManager;
import jailer.jailer.blocks.listener.BlockBreaking;
import jailer.jailer.item.command.JailerItemCommand;
import jailer.jailer.item.listener.PlayerToolSwitchListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Jailer extends JavaPlugin {

    private static Jailer instance;

    public final StorageManager<Player, PlayerData> playerData =
            new StorageManager<Player, PlayerData>(
                    new FileStorageProvider<Player, PlayerData>(
                            new PlayerSerializer(),
                            new GsonSerializer<PlayerData>(),
                            getDataFolder())
            );


    @Override
    public void onEnable() {
        instance = this;

        getCommand("items").setExecutor(new JailerItemCommand());

        getServer().getPluginManager().registerEvents(new BlockBreaking(), this);
        getServer().getPluginManager().registerEvents(new PlayerToolAutoEquipListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerToolSwitchListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDataListener(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Jailer getInstance() {
        return instance;
    }

}
