/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package organizer.controller;

import java.io.File;
import java.util.ArrayList;
import organizer.model.Camera;
import organizer.model.MovieFile;
import organizer.model.Storage;
import organizer.view.MainFrame;
import other.Util;

/**
 *
 * @author Cristian
 */
public class Controller {

    private Camera cam;
    private MainFrame mainFrame = null;

    public Controller(Camera cam) {
        this.cam = cam;
    }

    public void setView(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void scan() {
        try {
            cam.init();
            mainFrame.updateLabels(cam.getStorageCollection().size(),
                    cam.getStorageCollectionSize());
        } catch (Error e) {
            mainFrame.error(e.getMessage());
        }
    }

    public void copy() {
        new Thread() {

            @Override
            public void run() {
                try {
                    ArrayList<Storage> storageCollection = cam.getStorageCollection();
                    int totalCount = cam.getStorageCollectionSize();
                    int copiedCount = 0;
                    mainFrame.initProgressBar(totalCount);

                    for (int i = 0; i < storageCollection.size(); i++) {
                        Storage storage = storageCollection.get(i);

                        String dest = mainFrame.getSaveLocation()
                                + String.valueOf(i) + "/";

                        int temp = i;
                        boolean canSave = false;
                        while (!canSave) {
                            if (!new File(dest).exists()) {
                                canSave = true;
                            } else {

                                temp++;
                                dest = mainFrame.getSaveLocation()
                                        + String.valueOf(temp) + "/";
                            }
                        }

                        for (int j = 0; j < storage.size(); j++) {
                            MovieFile mf = storage.getContent().get(j);

                            String src = mf.getPath();

                            //haha
                            String finalDestination = dest + j + "."
                                    + mf.extensionToMpg();

                            try {
                                copiedCount++;
                                new Util().copyFile(src, finalDestination);
                                mainFrame.setProgress(copiedCount);
                            } catch (Exception e) {
                                mainFrame.error(e.getMessage());
                            }
                        }
                    }
                    mainFrame.enableGUI(true);
                    mainFrame.alert("Success! Remember to delete movies from Camera!");
                } catch (Error e) {
                    mainFrame.error(e.getMessage());
                }
            }
        }.start();
    }
}
