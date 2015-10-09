package com.aziis98.dare.inputs;

import com.aziis98.dare.interfaces.*;
import com.aziis98.dare.math.*;

import java.awt.event.*;
import java.util.*;
import java.util.function.*;

public class Mouse {

    private static int x, y;
    private static int prevX, prevY;
    private static LinkedList<Consumer<MouseEvent>> clickListeners = new LinkedList<>();
    private static LinkedList<Consumer<MouseEvent>> buffer = new LinkedList<>();

    public static void setXY(int x, int y) {
        // updatePrevious();

        Mouse.x = x;
        Mouse.y = y;
    }

    public static void updatePrevious() {
        prevX = x;
        prevY = y;
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static int getDeltaX() {
        return x - prevX;
    }

    public static int getDeltaY() {
        return y - prevY;
    }

    public static void addClickListener(Consumer<MouseEvent> mouseEventConsumer) {
        buffer.add(mouseEventConsumer);
    }

    public static void addClickListener(Action action) {
        addClickListener(mouseEvent -> action.perform());
    }

    public static void callClickListeners(MouseEvent e) {
        if (!buffer.isEmpty()) {
            clickListeners.addAll(buffer);
            buffer.clear();
        }

        clickListeners.forEach(listener -> listener.accept(e));
    }

    public static Vector2f getPosition() {
        return new Vector2f(x, y);
    }
}
