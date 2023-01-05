package jailer.jailer.data.serialize;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jailer.jailer.display.npc.NPCConversation;
import jailer.jailer.display.npc.NPCManager;
import jailer.jailer.display.npc.NPCVariable;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class NPCSerializer implements Serializer<HashMap<String, NPCConversation>, String> {

    private final Gson GSON;
    private final NPCManager manager;
    public NPCSerializer(NPCManager manager) {
        this.manager = manager;
        GSON = new Gson();
    }
    @Override
    public String serialize(HashMap<String, NPCConversation> data) {
        HashMap<String, HashMap<String, Object>> conversationData = new HashMap<>();
        for (Map.Entry<String, NPCConversation> conversation : data.entrySet()) {
            Class<? extends NPCConversation> clazz = conversation.getValue().getClass();
            HashMap<String, Object> conversationVariables = new HashMap<>();
            for (Field field : clazz.getFields()) {
                try {
                    if (field.getAnnotation(NPCVariable.class) != null) {
                        conversationVariables.put(field.getName(), field.get(conversation.getValue()));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            conversationData.put(conversation.getKey(), conversationVariables);
        }
        return GSON.toJson(conversationData);
    }

    @Override
    public HashMap<String, NPCConversation> deserialize(String data) {
        TypeToken<HashMap<String, HashMap<String, Object>>> type = new TypeToken() {};
        HashMap<String, HashMap<String, Object>> parsedData = GSON.fromJson(data, type.getType());
        if (parsedData == null) return null;
        HashMap<String, NPCConversation> conversationData = new HashMap<>();
        for (Map.Entry<String, HashMap<String, Object>> conversation : parsedData.entrySet()) {
            try {

                NPCConversation constructedConversation = manager.conversations.get(conversation.getKey()).getConstructor().newInstance();

                for (Map.Entry<String, Object> conversationVariable : conversation.getValue().entrySet()) {
                    Field variableField = constructedConversation.getClass().getDeclaredField(conversationVariable.getKey());
                    variableField.set(constructedConversation,conversationVariable.getValue());

                }
                conversationData.put(conversation.getKey(), constructedConversation);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conversationData;
    }
}
