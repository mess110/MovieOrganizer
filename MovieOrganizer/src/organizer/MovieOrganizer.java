/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package organizer;

import organizer.controller.Controller;
import organizer.model.Camera;
import organizer.view.MainFrame;

/**
 *
 * @author Cristian
 */
public class MovieOrganizer {

    private Camera cam;
    private Controller ctrl;
    private MainFrame mf;

    public MovieOrganizer() {
        cam = new Camera();
        ctrl = new Controller(cam);
        mf = new MainFrame(ctrl);
        ctrl.setView(mf);
    }

    public void display() {
        mf.setVisible(true);
    }

    public String getDefaulPath() {
        return mf.getSaveLocation();
    }
}
