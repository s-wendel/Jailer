package seafront.seafront.data.storage;


import seafront.seafront.data.serialize.Serializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileStorageProvider<I, T> implements StorageProvider<I, T> {

    private final Serializer<T, String> serializer;
    private final Serializer<I, String> indexer;

    private final File saveFolder;
    public FileStorageProvider(Serializer<I, String> indexer, Serializer<T, String> serializer, File saveFolder) {
        this.serializer = serializer;
        this.indexer = indexer;
        this.saveFolder = saveFolder;
        saveFolder.mkdirs();
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



    public List<I> keys() {
        ArrayList<I> files = new ArrayList<>();
        if (saveFolder.listFiles() == null) return files;
        for (File file : saveFolder.listFiles()) {
            if (file.isFile()) {
                files.add(indexer.deserialize(file.getName()));
            }
        }
        return files;
    }
}
