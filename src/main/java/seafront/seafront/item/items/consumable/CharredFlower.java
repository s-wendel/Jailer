package seafront.seafront.item.items.consumable;

import seafront.seafront.Seafront;
import seafront.seafront.data.PlayerData;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import seafront.seafront.item.*;

import java.util.Arrays;

public class CharredFlower extends SeafrontAbilityItem {

    private static final double MULTIPLIER = 1.25;
    private static final int DURATION = 30; // in minutes

    public CharredFlower() {
        super(
                "Charred Flower",
                Material.WITHER_ROSE,
                Arrays.asList("How is the flower still alive?"),
                SeafrontRarity.RARE,
                ItemType.CONSUMABLE,
                500,
                "",
                Arrays.asList(
                        "<yellow>Right-Click <white>to grant " + SeafrontStat.MINING_SPEED.getColor() + MULTIPLIER + "x " + SeafrontStat.MINING_SPEED.getName() + " " + SeafrontStat.MINING_SPEED.getIcon(),
                        "<white>for <yellow>" + DURATION + " minutes"
                )
        );
    }

    @Override
    public void abilityTrigger(AbilityAction action) {

        switch(action.type) {
            case RIGHT_CLICK:
                Player player = action.player;
                ItemStack tool = player.getInventory().getItemInMainHand();
                tool.setAmount(tool.getAmount() - 1);

                PlayerData playerData = Seafront.getInstance().playerData.getData(player);

                playerData.setStat(SeafrontStat.MINING_SPEED, new SeafrontStatData(player, "Charred Flower", Material.WITHER_ROSE,MULTIPLIER + "x", System.currentTimeMillis() + (DURATION * 60 * 60)));
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 0.5f, 1.5f);

                new BukkitRunnable() {

                    int value = 1;


                    @Override
                    public void run() {

                        Location location = player.getLocation().clone().add(0, 1.5, 0);

                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_HURT_ON_FIRE, 1f, 0.75f);

                        player.getWorld().spawnParticle(Particle.ASH, location.getX(), location.getY(), location.getZ(), (int) Math.pow(15, 1 + 0.25 * value), 0.5d, 0.5d, 0.5d);
                        player.getWorld().spawnParticle(Particle.SCULK_SOUL, location.getX(), location.getY(), location.getZ(), (int) Math.pow(5, 1 + 0.25 * value), 0.5d, 0.5d, 0.5d, 0);
                        player.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, location.getX(), location.getY(), location.getZ(), (int) Math.pow(5, 1 + 0.25 * value), 0.5d, 0.5d, 0.5d, 0);

                        value++;

                        if(value == 6) {
                            cancel();
                        }

                    }

                }.runTaskTimer(Seafront.getInstance(), 0, 20);

                break;
        }

    }
}
