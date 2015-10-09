package com.aziis98.dare.rendering;

import com.aziis98.dare.SwingWindow.*;
import com.aziis98.dare.math.*;

import java.awt.*;

public class G {

    public static void drawCircle(Graphics2D g, Vector2f vector2f, float radius) {
        drawCircle(g, vector2f.x, vector2f.y, radius);
    }

    public static void drawCircle(Graphics2D g, float x, float y, float radius) {
        g.drawOval((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));
    }

    public static void drawHLine(Graphics2D g, float y) {
        g.drawLine(0, (int) y, WindowData.width, (int) y);
    }

    public static void drawVLine(Graphics2D g, float x) {
        g.drawLine((int) x, 0, (int) x, WindowData.height);
    }

    public static void drawBox(Graphics2D g, Vector2f vector2f, float radius) {
        drawBox(g, vector2f.x, vector2f.y, radius);
    }

    public static void drawBox(Graphics2D g, float x, float y, float radius) {
        g.drawRect((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2));
    }

    public static void drawLine(Graphics2D g, Vector2f a, Vector2f b) {
        g.drawLine((int) a.x, (int) a.y, (int) b.x, (int) b.y);
    }

    public static void drawRect(Graphics2D g, float x, float y, float width, float height) {
        g.drawRect((int) x, (int) y, (int) width, (int) height);
    }

    public static void drawRect(Graphics2D g, Vector2f pos, float width, float height) {
        drawRect(g, pos.x, pos.y, width, height);
    }

}
