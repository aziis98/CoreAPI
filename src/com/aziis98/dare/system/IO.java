package com.aziis98.dare.system;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class IO {

    public static class Fonts {

        public static Font getFont(String path) {
            try {
                return Font.createFont(Font.TRUETYPE_FONT, new File(path));
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new APIException("Error while loading font at: \"" + path + "\"");
            }
        }

    }

    public static class ObjectSerializers {

        public static <T extends Serializable> void save(String path, T object) {
            try
            {
                FileOutputStream   fileOut = new FileOutputStream(new File(path));
                ObjectOutputStream out     = new ObjectOutputStream(fileOut);
                out.writeObject(object);
                out.close();
                fileOut.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                throw new APIException("Unable to save object to path '" + path + "'");
            }
        }

        @SuppressWarnings("unchecked")
        public static <T extends Serializable> T load(String path) {
            try
            {
                T object;
                FileInputStream fileIn = new FileInputStream(new File(path));
                ObjectInputStream in = new ObjectInputStream(fileIn);
                object = (T) in.readObject();
                in.close();
                fileIn.close();
                return object;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                throw new APIException("Unable to load object from path '" + path + "'");
            }
        }

        @SuppressWarnings({"unchecked", "UnusedParameters"})
        public static <T extends Serializable> T load(String path, Class<T> typeOfObject) {
            try
            {
                T object;
                FileInputStream fileIn = new FileInputStream(new File(path));
                ObjectInputStream in = new ObjectInputStream(fileIn);
                object = (T) in.readObject();
                in.close();
                fileIn.close();
                return object;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                throw new APIException("Unable to load object from path '" + path + "'");
            }
        }

    }

    public static class Images {

        public static BufferedImage loadImage(String path) {
            try
            {
                return ImageIO.read(new File(path));
            }
            catch (IOException e)
            {
                e.printStackTrace();
                throw new APIException("Unable to load image at path: '" + path + "'");
            }
        }

        public static void saveImage(String path, BufferedImage image) {
            try
            {
                ImageIO.write(image, "png", new File(path));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

}
