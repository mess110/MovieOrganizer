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
public class Filename {

    private String fullPath;
    private String pathSeparator = "/";
    private String extensionSeparator = ".";

    public Filename(String str) {
        fullPath = str;
    }

    public Filename(String str, String sep, String ext) {
        fullPath = str;
        pathSeparator = sep;
        extensionSeparator = ext;
    }

    public String extension() {
        int dot = fullPath.lastIndexOf(extensionSeparator);
        return fullPath.substring(dot + 1);
    }

    public String filename() { // gets filename without extension
        int dot = fullPath.lastIndexOf(extensionSeparator);
        int sep = fullPath.lastIndexOf(pathSeparator);
        return fullPath.substring(sep + 1, dot);
    }

    public String path() {
        int sep = fullPath.lastIndexOf(pathSeparator);
        return fullPath.substring(0, sep);
    }

    public String fullFilename() {
        return filename() + "." + extension();
    }
}
