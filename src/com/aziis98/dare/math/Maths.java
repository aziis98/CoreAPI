package com.aziis98.dare.math;

import com.aziis98.dare.interfaces.*;
import com.aziis98.dare.util.*;

import java.util.function.*;

public class Maths {

    public static final float PI = (float) Math.PI;

    public static int clamp(int min, int value, int max) {
        return value < min ? min : (value > max ? max : value);
    }

    public static float clamp(float min, float value, float max) {
        return value < min ? min : (value > max ? max : value);
    }

    public static double clamp(double min, double value, double max) {
        return value < min ? min : (value > max ? max : value);
    }

    public static float cos(float value) {
        return (float) Math.cos(value);
    }

    public static float sin(float value) {
        return (float) Math.sin(value);
    }

    public static float sqrt(float value) {
        return (float) Math.sqrt(value);
    }

    public static float pow2(float value) {
        return value * value;
    }

    public static float pow3(float value) {
        return value * value;
    }

    public static float pow(float a, float b) {
        return (float) Math.pow(a, b);
    }


    public static float toRadians(float degrees) {
        return (float) Math.toRadians(degrees);
    }

    public static float toDegree(float radians) {
        return (float) Math.toDegrees(radians);
    }

    public static int floor(float value) {
        return (int) Math.floor(value);
    }



    public static <T, V extends IPos2f> EList<T> sortNearest(EList<T> list, V origin, Function<T, V> wrapper, IDistanceFunction<V> distanceFunction) {
        return list.sort((o1, o2) -> Float.compare(
                distanceFunction.distance(origin, wrapper.apply(o1)),
                distanceFunction.distance(origin, wrapper.apply(o2))
        ));
    }

    public static <T extends IPos2f> EList<T> sortNearestEuclidean(EList<T> list, T origin) {
        return list.sort((o1, o2) -> Float.compare(
                Vector2f.dist(origin.getX(), origin.getY(), o1.getX(), o1.getY()),
                Vector2f.dist(origin.getX(), origin.getY(), o2.getX(), o2.getY())
        ));
    }

    public static <T extends IPos2f> EList<T> sortNearestManhattan(EList<T> list, T origin) {
        return list.sort((o1, o2) -> Float.compare(
                distManhattan(origin, o1),
                distManhattan(origin, o2)
        ));
    }

    public static float distManhattan(IPos2f a, IPos2f b) {
        return Math.abs(b.getX() - a.getX()) + Math.abs(b.getY() - a.getY());
    }

    public static boolean inRange(double min, double value, double max) {
        return value > min && value < max;
    }

}
