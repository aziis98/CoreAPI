package com.aziis98.dare.inputs;

import com.aziis98.dare.values.*;

import java.awt.event.*;

public class Keyboard {

    private static final int                 KEY_COUNT = 1024;
    @SuppressWarnings("unchecked")
    private static final Property<Boolean>[] keys      = new Property[KEY_COUNT];

    static
    {
        for (int i = 0; i < keys.length; i++)
        {
            keys[i] = new Property<>(false);
        }
    }

    private static char lastChar = 0;

    public static char getLastChar() {
        return lastChar;
    }


    public static Property<Boolean> key(int keyCode) {
        return keys[keyCode];
    }


    private static void callKeyPressed(int keyCode) {
        keys[keyCode].set(true);
    }

    private static void callKeyReleased(int keyCode) {
        keys[keyCode].set(false);
    }


    public static KeyListener getHandler() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                lastChar = e.getKeyChar();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                callKeyPressed(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                callKeyReleased(e.getKeyCode());
            }
        };
    }

}
