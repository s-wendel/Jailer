package jailer.jailer.data.storage;

import java.util.List;

public interface StorageProvider<I, T> {



    T loadData(I index);

    boolean saveData(T data, I index);

    boolean hasData(I index);


    boolean removeData(I index);

    List<I> keys();

}
