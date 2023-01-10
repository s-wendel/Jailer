package seafront.seafront.data.storage;

import java.util.HashMap;
import java.util.List;

public class StorageManager<I, T> {

    public final StorageProvider<I, T> storageProvider;

    private final HashMap<I, T> cachedData;

    public StorageManager(StorageProvider<I, T> storageProvider) {
        this.storageProvider = storageProvider;
        this.cachedData = new HashMap<>();
    }
    public boolean saveData(I index) {

        if (!cachedData.containsKey(index)) return false;
        else storageProvider.saveData(cachedData.get(index), index);

        return true;
    }
    public T loadData(I index) {


        if (cachedData.containsKey(index)) return null;
        T data = storageProvider.loadData(index);
        cachedData.put(index, data);
        return data;

    }
    public T getOrLoadData(I index) {
        if (cachedData.containsKey(index)) return cachedData.get(index);
        else return loadData(index);
    }

    public void putData(I index, T data) { cachedData.put(index, data); }

    public T getData(I index) { return cachedData.getOrDefault(index, null); }
    public boolean hasCachedData(I index) { return (cachedData.containsKey(index)); }
    public boolean hasData(I index) { return storageProvider.hasData(index); }
    public boolean removeData(I index) { return storageProvider.removeData(index); }
    public T removeCachedData(I index) { return cachedData.remove(index); }

    public List<I> keys() { return storageProvider.keys(); }

    public List<I> cachedKeys() { return cachedData.keySet().stream().toList(); }


}
