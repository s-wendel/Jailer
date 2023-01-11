package seafront.seafront.data.serialize;

public class StringSerializer implements Serializer<String, String>{

    @Override
    public String serialize(String data) {
        return data;
    }

    @Override
    public String deserialize(String data) {
        return data;
    }
}
