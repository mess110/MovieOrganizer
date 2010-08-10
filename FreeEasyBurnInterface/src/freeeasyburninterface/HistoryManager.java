/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package freeeasyburninterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class HistoryManager {

    private PrintWriter pw;
    private BufferedReader br;
    private ArrayList<String> transferedStorage;

    public HistoryManager() throws IOException {

        File file = new File("D:/filmeTata/history.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        transferedStorage = new ArrayList<String>();


        FileWriter outFile = new FileWriter(file, true);
        pw = new PrintWriter(outFile);
        br = new BufferedReader(new FileReader(file));

        read();
    }

    private synchronized void read() {
        try {
            while (br.ready()) {
                // Print file line to screen
                transferedStorage.add(br.readLine());
                //System.out.println(dis.readLine());
            }
        } catch (IOException ex) {
            Logger.getLogger(HistoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void append(String line) {
        pw.println(line);
    }

    public synchronized ArrayList<String> getHistory() {
        return transferedStorage;
    }

    public synchronized void close() {
        try {
            if (br != null) {
                br.close();
            }
            if (pw != null) {
                pw.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(HistoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
