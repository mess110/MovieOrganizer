/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package freeeasyburninterface;

import java.awt.AWTException;
import java.io.IOException;

/**
 *
 * @author Cristian
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws AWTException, IOException {
        //get that default location here
        SmartRobot sr = new SmartRobot("D:/filmeTata/");
        sr.burn();
    }
}
