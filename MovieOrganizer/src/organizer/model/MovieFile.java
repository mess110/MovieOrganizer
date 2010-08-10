/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package organizer.model;

import java.io.File;

/**
 *
 * @author Cristian
 */
public class MovieFile {

    private String path;
    private long size;

    public MovieFile() {
    }

    public MovieFile(String path, long size) {
        this.path = path;
        //convert to mb
        this.size = size / (1024 * 1000);
    }

    public String getName() {
        //TODO:: this is crap
        int lastSeperatorIndex = getPath().lastIndexOf(File.separator);
        return path.substring(lastSeperatorIndex + 1);
    }

    public String getExtension() {
        String name = getName();
        int lastSeperatorIndex = name.lastIndexOf(".");
        return name.substring(lastSeperatorIndex + 1);
    }

    public String extensionToMpg() {
        //this works so haha
        return "mpg";
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the size
     */
    public long getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return path + " - " + Long.toString(size);
    }
}
