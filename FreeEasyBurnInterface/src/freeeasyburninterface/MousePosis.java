/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package freeeasyburninterface;

/**
 *
 * @author Cristian
 */
import java.awt.MouseInfo;

public class MousePosis {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Thread.sleep(100);
            System.out.println("(" + MouseInfo.getPointerInfo().getLocation().x + ", " + MouseInfo.getPointerInfo().getLocation().y + ")");
        }
    }
}
//263 683