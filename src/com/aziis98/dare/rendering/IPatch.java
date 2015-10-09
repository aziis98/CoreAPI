package com.aziis98.dare.rendering;

import java.awt.*;

public interface IPatch {

    int INHERITED = -1;

    void draw(Graphics2D g, float x, float y, float width, float height);

}
