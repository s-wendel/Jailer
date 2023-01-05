package jailer.jailer.display.npc;

import jailer.jailer.data.storage.StorageManager;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class NPCManager implements Listener {

    public StorageManager<Player, HashMap<String, NPCConversation>> conversationManager;
    public HashMap<String, Class<? extends NPCConversation>> conversations;

    public NPCManager(StorageManager<Player, HashMap<String, NPCConversation>> conversationManager) {
        this.conversationManager = conversationManager;
        this.conversations = new HashMap<>();
    }

    public void addConversation(Class<? extends NPCConversation> conversation) {
        try {
            NPCConversation constructedConversation = conversation.getConstructor().newInstance();
            conversations.put(constructedConversation.npcName, conversation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean constructConversation(String npcName, Player player) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        HashMap<String, NPCConversation> data = conversationManager.getData(player);
        if (!conversations.containsKey(npcName)) return false;
        NPCConversation constructedConversation = conversations.get(npcName).getConstructor().newInstance();
        constructedConversation.setPlayer(player);
        data.put(npcName, constructedConversation);
        return true;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (conversationManager.hasData(event.getPlayer())) {
            conversationManager.loadData(event.getPlayer());
        } else {
            conversationManager.putData(event.getPlayer(), new HashMap<>());
        }


    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        conversationManager.saveData(event.getPlayer());
        conversationManager.removeCachedData(event.getPlayer());
    }

    @EventHandler
    public void onNPCClick(NPCRightClickEvent event) {
        try {
            HashMap<String, NPCConversation> data = conversationManager.getData(event.getClicker());
            String npcName = event.getNPC().getName();
            if (!data.containsKey(npcName)) if (!constructConversation(npcName, event.getClicker())) return;
            data.get(npcName).setPlayer(event.getClicker());
            data.get(npcName).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
