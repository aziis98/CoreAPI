package com.aziis98.dare.rendering;

import java.awt.*;
import java.awt.image.*;

public class SinglePatch implements IPatch {

    public BufferedImage image;

    public SinglePatch(BufferedImage image) {
        this.image = image;
    }

    public void draw(Graphics2D g, float x, float y, float width, float height, float alpha) {
        if (width == INHERITED) width = image.getWidth();
        if (height == INHERITED) height = image.getHeight();

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
    }

    @Override
    public void draw(Graphics2D g, float x, float y, float width, float height) {
        draw(g, x, y, width, height, 1F);
    }

    public void draw(Graphics2D g, float x, float y, float width, float height, float rx, float ry) {
        float sx = (rx % image.getWidth());
        float sy = (ry % image.getHeight());

        if (sx + width > image.getWidth()) sx = (sx + image.getWidth()) % image.getWidth();
        if (sy + height > image.getHeight()) sy = (sy + image.getHeight()) % image.getHeight();

        g.drawImage(image, (int) x, (int) y, (int) (x + width), (int) (y + height), (int) sx, (int) sy, (int) (sx + width), (int) (sy + height), null);
    }

    public void drawCentered(Graphics2D g, float x, float y, float alpha) {
        draw(g, x - image.getWidth() / 2F, y - image.getHeight() / 2F, INHERITED, INHERITED, alpha);
    }

    public void drawCentered(Graphics2D g, float x, float y) {
        drawCentered(g, x, y, 1F);
    }

}
