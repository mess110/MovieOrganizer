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
public class Storage {

    private long storageSize = 4600; //MB
    private ArrayList<MovieFile> dvdContent = new ArrayList<MovieFile>();

    public Storage() {
    }

    public Storage(long storageSize, MovieFile mf) {
        this.storageSize = storageSize;
        add(mf);
    }

    public void add(MovieFile mf) {
        if (getFreeSpace() >= mf.getSize()) {
            dvdContent.add(mf);
        } else {
            throw new Error("file too big!");
        }
    }

    public int size() {
        return dvdContent.size();
    }

    public long getFreeSpace() {
        long freeSpace = storageSize;
        for (int i = 0; i < dvdContent.size(); i++) {
            freeSpace = freeSpace - dvdContent.get(i).getSize();
        }
        return freeSpace;
    }

    public ArrayList<MovieFile> getContent() {
        return dvdContent;
    }
}
