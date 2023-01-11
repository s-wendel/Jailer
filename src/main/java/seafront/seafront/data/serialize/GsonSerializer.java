package seafront.seafront.data.serialize;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonSerializer<T> implements Serializer<T, String>{

    private final Gson GSON;
    private final TypeToken<T> type;
    public GsonSerializer(TypeToken<T> type) {
        GSON = new Gson();
        this.type = type;
    }
    @Override
    public String serialize(T data) {
        return GSON.toJson(data);
    }

    @Override
    public T deserialize(String data) {
        return GSON.fromJson(data, type.getType());
    }
}
