package com.aziis98.dare.system;

import com.aziis98.dare.interfaces.*;

public class Time {

    public static long milliTime() {
        return System.nanoTime() / 1000000;
    }

    public static long nanoTime() {
        return System.nanoTime();
    }

    public static void timer(long delay, Action action) {
        new Thread(() -> {
            long startTime = milliTime();

            while (true) {
                if (milliTime() - startTime > delay) {
                    startTime = milliTime();
                    action.perform();
                }
                else {
                    Time.sleep(10);
                }
            }
        }).start();
    }

    public static void post(Action action) {
        new Thread(action::perform).start();
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
