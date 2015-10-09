package com.aziis98.dare.rendering;

import java.awt.*;
import java.awt.image.*;

public class NinePatch implements IPatch {

    private static final int TOP_LEFT      = 0;
    private static final int TOP_CENTER    = 1;
    private static final int TOP_RIGHT     = 2;
    private static final int CENTER_LEFT   = 3;
    private static final int CENTER_CENTER = 4;
    private static final int CENTER_RIGHT  = 5;
    private static final int BOTTOM_LEFT   = 6;
    private static final int BOTTOM_CENTER = 7;
    private static final int BOTTOM_RIGHT  = 8;

    private BufferedImage[] tiles = new BufferedImage[9];

    private int left, right, top, bottom;

    public NinePatch(BufferedImage bi, int left, int right, int top, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;

        final int middleWidth  = bi.getWidth() - left - right;
        final int middleHeight = bi.getHeight() - top - bottom;

        tiles[TOP_LEFT] = bi.getSubimage(0, 0, left, top);
        tiles[TOP_CENTER] = bi.getSubimage(left, 0, middleWidth, top);
        tiles[TOP_RIGHT] = bi.getSubimage(left + middleWidth, 0, right, top);

        tiles[CENTER_LEFT] = bi.getSubimage(0, top, left, middleHeight);
        tiles[CENTER_CENTER] = bi.getSubimage(left, top, middleWidth, middleHeight);
        tiles[CENTER_RIGHT] = bi.getSubimage(left + middleWidth, top, right, middleHeight);

        tiles[BOTTOM_LEFT] = bi.getSubimage(0, top + middleHeight, left, bottom);
        tiles[BOTTOM_CENTER] = bi.getSubimage(left, top + middleHeight, middleWidth, bottom);
        tiles[BOTTOM_RIGHT] = bi.getSubimage(left + middleWidth, top + middleHeight, right, bottom);

    }

    @Override
    public void draw(Graphics2D g, float x, float y, float width, float height) {
        g.drawImage(tiles[TOP_LEFT], (int) x, (int) y, null);
        g.drawImage(tiles[TOP_RIGHT], (int) x + (int) width - right, (int) y, null);
        g.drawImage(tiles[BOTTOM_LEFT], (int) x, (int) y + (int) height - bottom, null);
        g.drawImage(tiles[BOTTOM_RIGHT], (int) x + (int) width - right, (int) y + (int) height - bottom, null);

        g.drawImage(tiles[TOP_CENTER], (int) x + left, (int) y, (int) width - left - right, top, null);
        g.drawImage(tiles[CENTER_LEFT], (int) x, (int) y + top, left, (int) height - top - bottom, null);
        g.drawImage(tiles[CENTER_RIGHT], (int) x + (int) width - right, (int) y + top, right, (int) height - top - bottom, null);
        g.drawImage(tiles[BOTTOM_CENTER], (int) x + left, (int) y + (int) height - bottom, (int) width - left - right, bottom, null);

        g.drawImage(tiles[CENTER_CENTER], (int) x + left, (int) y + top, (int) width - left - right, (int) height - top - bottom, null);
    }

}
