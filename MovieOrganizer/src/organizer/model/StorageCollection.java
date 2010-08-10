/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package organizer.model;

import java.util.ArrayList;

/**
 *
 * @author Cristian
 */
public class StorageCollection {

    private ArrayList<Storage> storageCollection;
    private long storageSize = 4600;

    public StorageCollection() {
        storageCollection = new ArrayList<Storage>();
    }

    public StorageCollection(long storageSize) {
        this.storageSize = storageSize;
    }

    public int size() {
        return storageCollection.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public ArrayList<Storage> getCollection() {
        return storageCollection;
    }

    public void addToCollection(MovieFile mf) {
        if (storageCollection.isEmpty()) {
            storageCollection.add(new Storage(storageSize, mf));
            return;
        }

        int dvdIndex = storageCollection.size() - 1;
        Storage foo = storageCollection.get(dvdIndex);

        if (foo.getFreeSpace() >= mf.getSize()) {
            foo.add(mf);
            storageCollection.set(dvdIndex, foo);
        } else {
            storageCollection.add(new Storage(storageSize, mf));
        }
    }
}
