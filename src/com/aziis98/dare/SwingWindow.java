package com.aziis98.dare;

import com.aziis98.dare.inputs.*;
import com.aziis98.dare.math.*;
import com.aziis98.dare.system.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

public class SwingWindow extends Thread {

    private boolean isRunning = true;

    private BufferStrategy strategy;
    private BufferedImage  background;
    private Graphics2D     graphicsBackground;
    private Graphics2D     graphics;
    private JFrame         frame;
    private Canvas         canvas;
    private GraphicsConfiguration config =
            GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getDefaultScreenDevice()
                    .getDefaultConfiguration();

    private ApplicationCore core;

    private int resizeCooldown = -1;

    // create a hardware accelerated image
    public final BufferedImage create(final int width, final int height,
                                      final boolean alpha) {
        return config.createCompatibleImage(width, height, alpha
                ? Transparency.TRANSLUCENT : Transparency.OPAQUE);
    }

    public SwingWindow(ApplicationCore core) {
        this.core = core;

        // JFrame
        frame = new JFrame();

        frame.addWindowListener(new FrameClose());

        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        core.init();

        frame.setSize(WindowData.width, WindowData.height);
        frame.setTitle(WindowData.title);
        frame.setResizable(WindowData.resizable);
        frame.setAlwaysOnTop(WindowData.topMost);
        frame.setLocationRelativeTo(null);

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                WindowData.width = frame.getWidth();
                WindowData.height = frame.getHeight();

                resizeCooldown = 10;
            }
        });

        // Canvas
        canvas = new Canvas(config);
        canvas.setSize(WindowData.width, WindowData.height);

        canvas.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Mouse.setXY((int) (e.getX() / WindowData.pixelScale), (int) (e.getY() / WindowData.pixelScale));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Mouse.setXY((int) (e.getX() / WindowData.pixelScale), (int) (e.getY() / WindowData.pixelScale));
            }
        });

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Mouse.callClickListeners(e);
            }
        });

        frame.addKeyListener(Keyboard.getHandler());
        canvas.addKeyListener(Keyboard.getHandler());
        // canvas.requestFocusInWindow();

        frame.add(canvas, 0);

        frame.setVisible(true);

        // Background & Buffer
        background = create((int) (WindowData.width / WindowData.pixelScale), (int) (WindowData.height / WindowData.pixelScale), false);

        canvas.createBufferStrategy(2);
        do {
            strategy = canvas.getBufferStrategy();
        } while (strategy == null);

        start();
    }

    private class FrameClose extends WindowAdapter {
        @Override
        public void windowClosing(final WindowEvent e) {
            isRunning = false;
        }
    }

    public static class WindowData {
        public static String  title      = "<Untitled>";
        public static int     width      = 1000;
        public static int     height     = 750;
        public static float   pixelScale = 1F; // non integer values may give bad results
        public static boolean resizable  = false;
        public static boolean topMost    = false;

        public static Vector2f getCenter() {
            return getDimensions().scale(0.5F);
        }

        public static Vector2f getDimensions() {
            return new Vector2f(getWidth(), getHeight());
        }

        public static float getWidth() {
            return width / pixelScale;
        }

        public static float getHeight() {
            return height / pixelScale;
        }
    }


    // Screen and buffer stuff
    private Graphics2D getBuffer() {
        if (graphics == null)
        {
            try
            {
                graphics = (Graphics2D) strategy.getDrawGraphics();
            }
            catch (IllegalStateException e)
            {
                return null;
            }
        }
        return graphics;
    }

    private boolean updateScreen() {
        graphics.dispose();
        graphics = null;
        try
        {
            strategy.show();
            Toolkit.getDefaultToolkit().sync();
            return (!strategy.contentsLost());

        }
        catch (NullPointerException e)
        {
            return true;

        }
        catch (IllegalStateException e)
        {
            return true;
        }
    }

    public void run() {
        graphicsBackground = (Graphics2D) background.getGraphics();

        long fpsWait = (long) (1.0 / 120 * 1000); //long fpsWait = (long) (1.0 / 30 * 1000);
        main:
        while (isRunning)
        {
            long renderStart = System.nanoTime();

            resizeCheck();
            this.core.update();
            Mouse.updatePrevious();

            if (resizeCooldown > 0)
            {
                Time.sleep(10);
                continue;
            }

            // Update Graphics
            do
            {

                Graphics2D bg = getBuffer();
                assert bg != null;

                if (!isRunning)
                {
                    break main;
                }

                this.core.render(graphicsBackground);

                bg.drawImage(background, 0, 0, WindowData.width, WindowData.height, null);
                bg.dispose();

            }
            while (!updateScreen());

            // Better do some FPS limiting here
            long renderTime = (System.nanoTime() - renderStart) / 1000000;
            try
            {
                Thread.sleep(Math.max(0, fpsWait - renderTime));
            }
            catch (InterruptedException e)
            {
                Thread.interrupted();
                break;
            }

            // renderTime = (System.nanoTime() - renderStart) / 1000000;
            // System.out.println("renderTime = " + renderTime);
        }

        frame.dispose();
        Console.stop();
    }

    private void resizeCheck() {
        // System.out.println("resizeCooldown = " + resizeCooldown);

        if (resizeCooldown == 0)
        {
            canvas.setSize(WindowData.width, WindowData.height);
            background = create((int) (WindowData.width / WindowData.pixelScale), (int) (WindowData.height / WindowData.pixelScale), false);
            graphicsBackground = (Graphics2D) background.getGraphics();
        }

        if (resizeCooldown >= 0) resizeCooldown--;
    }

}