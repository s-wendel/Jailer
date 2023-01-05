package jailer.jailer.data.storage;


import jailer.jailer.data.serialize.Serializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class FileStorageProvider<I, T> implements StorageProvider<I, T> {

    private final Serializer<T, String> serializer;
    private final Serializer<I, String> indexer;

    private final File saveFolder;
    public FileStorageProvider(Serializer<T, String> serializer, Serializer<I, String> indexer, File saveFolder) {
        this.serializer = serializer;
        this.indexer = indexer;
        this.saveFolder = saveFolder;
    }


    public T loadData(I index) {
        File file = getFile(index);

        try {
            if (!file.exists())  {
                file.createNewFile();
                return null;
            }
            String unparsedData = String.join(" ", Files.readAllLines(file.toPath()));
            T parsedData = serializer.deserialize(unparsedData);
            return parsedData;
        } catch (IOException e) {

            throw new RuntimeException(e);

        }
    }


    public boolean saveData(T data, I index) {
        File file = getFile(index);
        try {

            if (!file.exists()) file.createNewFile();
            FileWriter writer = new FileWriter(file);

            writer.write(serializer.serialize(data));
            writer.flush();
            writer.close();
            return true;

        } catch (IOException e) {

            throw new RuntimeException(e);

        }
    }


    public boolean hasData(I index) { return (getFile(index).exists()); }


    public boolean removeData(I index) {
        return getFile(index).delete();
    }

    private File getFile(I index) {
        return new File(saveFolder.getAbsolutePath()  + "/" + indexer.serialize(index));
    }
}
