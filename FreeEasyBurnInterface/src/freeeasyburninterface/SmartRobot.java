/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package freeeasyburninterface;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Cristian
 */
public class SmartRobot extends RobotBurner {

    private HistoryManager hm;
    private ArrayList<String> toBurn;
    private String location;

    public SmartRobot(String location) {
        super();
        this.location = location;
        try {
            hm = new HistoryManager();
        } catch (IOException ex) {
            throw new Error("can not access history");
        }
    }

    private ArrayList<String> readStorage() {
        ArrayList<String> foo = new ArrayList<String>();

        File dir = new File(location);
        String[] children = dir.list();
        for (String string : children) {
            //System.out.println(string);
            if (new File(location + string).isDirectory()) {
                foo.add(string);
            }
        }
        return foo;
    }

    public void burn() throws Error {
        toBurn = readStorage();
        ArrayList<String> history = hm.getHistory();
        toBurn.removeAll(history);

        if (toBurn.isEmpty()) {
            throw new Error("nothing to burn");
        }
        burnEach(toBurn.get(0));
        hm.close();
    }

    private void burnEach(String string) {
        sleep(3);
        moveMouse(335, 457);
        click();

        //b
        keyPress(66);
        //i
        keyPress(73);
        //right arrow
        keyPress(39);
        //f
        keyPress(70);
        //i
        keyPress(73);
        //l
        keyPress(76);
        //right arrow
        keyPress(39);

        for (int i = 0; i < string.length(); i++) {
            //TODO: I don't think this should work.. WTF
            int code = string.charAt(i);
            keyPress(code);
        }

        //enter
        keyPress(10);
        moveMouse(752, 735);
        click();
        sleep(1);

        //load the control image
        BufferedImage ctrl = null;
        URL ctrlURL = this.getClass().getResource("image/controll.png");
        try {
            ctrl = ImageIO.read(ctrlURL);
        } catch (IOException ex) {
            Logger.getLogger(SmartRobot.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean identical = true;

        //while the control is the same as the captured means DVD was not inserted
        do {


            //TODO use robot to check if dvd is inserted using screen capture!
            moveMouse(312, 377);
            click();
            sleep(1);

            BufferedImage screencapture = capture(new Rectangle(274, 692, 5, 5));

            for (int i = 0; i < ctrl.getWidth(); i++) {
                for (int j = 0; j < ctrl.getHeight(); j++) {
                    if (ctrl.getRGB(i, j) != screencapture.getRGB(i, j)) {
                        identical = false;
                    }
                }
            }

            //not too much stress please
            sleep(1);
        } while (identical == true);

        //when we are here it means valid CD was inserted
        //start burning it
        moveMouse(312, 377);
        click();

        hm.append(string);

        //System.out.println(identical);
    }
}
