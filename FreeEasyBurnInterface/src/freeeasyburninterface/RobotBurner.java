/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package freeeasyburninterface;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class RobotBurner {

    private Desktop desk;
    private Robot robot;

    public RobotBurner() {
        try {
            if (!Desktop.isDesktopSupported()) {
                throw new Error("shit! desktop not supported!");
            }
            desk = Desktop.getDesktop();
            robot = new Robot();
            robot.setAutoDelay(10);
            execute("C:/Program Files/Free Easy Burner/FreeEasyBurner.exe");
        } catch (IOException ex) {
            throw new Error("can not execute burner program");
        } catch (AWTException ex) {
            throw new Error("can not start stupid robot");
        }
    }

    private void execute(String path) throws IOException {
        File asd = new File(path);
        desk.open(asd);
    }

    protected void moveMouse(int x, int y) {
        robot.mouseMove(x, y);
    }

    protected void click() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    protected void keyPress(int keycode) {
        robot.keyPress(keycode);
        robot.keyRelease(keycode);
    }

    protected void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RobotBurner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected BufferedImage capture(Rectangle r) {
        return robot.createScreenCapture(r);
    }
}
