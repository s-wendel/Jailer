package jailer.jailer.data.serialize;

public interface Serializer<T, O> {


    public O serialize(T data);

    public T deserialize(String data);

    

}
