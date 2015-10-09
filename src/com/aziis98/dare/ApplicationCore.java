package com.aziis98.dare;

import javax.swing.*;
import java.awt.*;

public abstract class ApplicationCore {

    public abstract void init();

    public abstract void update();

    public abstract void render(Graphics2D g);


    public static void launch(ApplicationCore core) {
        new SwingWindow(core);
    }

}
