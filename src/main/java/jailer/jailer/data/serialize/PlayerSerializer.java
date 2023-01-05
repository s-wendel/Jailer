package jailer.jailer.data.serialize;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerSerializer implements Serializer<Player, String> {

    @Override
    public String serialize(Player data) {
        return data.getUniqueId().toString();
    }

    @Override
    public Player deserialize(String data) {
        return Bukkit.getPlayer(UUID.fromString(data));
    }
}
