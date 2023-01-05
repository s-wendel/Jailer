package jailer.jailer;

import jailer.jailer.data.PlayerData;
import jailer.jailer.data.serialize.GsonSerializer;
import jailer.jailer.data.serialize.PlayerSerializer;
import jailer.jailer.data.storage.FileStorageProvider;
import jailer.jailer.data.storage.StorageManager;
import jailer.jailer.display.Formatter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Jailer extends JavaPlugin {

    private static Jailer instance;

    public final StorageManager<Player, PlayerData> playerData =
            new StorageManager(
                    new FileStorageProvider(
                        new PlayerSerializer(),
                            new GsonSerializer(),
                            getDataFolder())
            );

    public final Formatter format = new Formatter();

    @Override
    public void onEnable() {

        instance = this;
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Jailer getInstance() {
        return instance;
    }

}
