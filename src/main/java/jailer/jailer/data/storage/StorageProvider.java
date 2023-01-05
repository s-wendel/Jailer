package jailer.jailer.data.storage;

public interface StorageProvider<I, T> {



    T loadData(I index);

    boolean saveData(T data, I index);

    boolean hasData(I index);


    boolean removeData(I index);

}
