/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package organizer.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Cristian
 */
public class Camera {

    private String camLocation = "";
    private String moviesPath = "SD_VIDEO";
    private String isCam = "camcninf.dat";
    private StorageCollection storageCollection;
    private int collectionSize = 0;

    public Camera() {
    }

    public void init() {
        camLocation = "";
        collectionSize = 0;
        //search for a connected camera
        camLocation = findCamera();
        storageCollection = new StorageCollection();
        //scan and organize movies in DVDs
        scanDirectoryForMovies(camLocation + moviesPath);
    }

    private String findCamera() {
        String foo = "";
        File[] roots = File.listRoots();
        for (int i = 0; i < roots.length; i++) {
            File[] listOfFiles = roots[i].listFiles();
            if (listOfFiles != null) {
                for (int j = 0; j < listOfFiles.length; j++) {
                    String foo_file = listOfFiles[j].getName();
                    if (foo_file.equalsIgnoreCase(isCam)) {
                        foo = roots[i].getPath();
                    }
                }
            }
        }
        if (foo.equals("")) {
            throw new Error("could not find camera");
        }
        return foo;
    }

    private void scanDirectoryForMovies(String dir) {
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            String filePath;
            try {
                filePath = listOfFiles[i].getCanonicalPath();
            } catch (IOException ex) {
                throw new Error("destination unkown...");
            }

            if (listOfFiles[i].isDirectory()) {
                scanDirectoryForMovies(filePath);
            } else {
                if (filePath.endsWith(".MOD")) {
                    long size = listOfFiles[i].length();
                    storageCollection.addToCollection(
                            new MovieFile(filePath, size));
                    collectionSize++;
                }
            }
        }
    }

    public ArrayList<Storage> getStorageCollection() {
        if (camLocation.equals("")) {
            throw new Error("Scan for Camera first!!!");
        }

        if (storageCollection.isEmpty()) {
            throw new Error("There are no movies in the Camera!");
        }

        return storageCollection.getCollection();
    }

    public int getStorageCollectionSize() {
        return collectionSize;
    }
}
