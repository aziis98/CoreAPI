package com.aziis98.dare.math;

import com.aziis98.dare.annotations.*;
import com.aziis98.dare.interfaces.*;

public class Vector2f implements IPos2f {

    public float x, y;

    public Vector2f() {
        this(0, 0);
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Mutable
    public void Add(Vector2f v) {
        this.x += v.x;
        this.y += v.y;
    }

    @Mutable
    public void Set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Mutable
    public void Reset() {
        Set(0, 0);
    }


    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public Vector2f getPosition() {
        return this;
    }

    @Pure
    public Vector2f plus(Vector2f v) {
        return new Vector2f(x + v.x, y + v.y);
    }

    @Pure
    public Vector2f plus(float dx, float dy) {
        return new Vector2f(x + dx, y + dy);
    }

    @Pure
    public Vector2f minus(Vector2f v) {
        return new Vector2f(x - v.x, y - v.y);
    }

    @Pure
    public Vector2f scale(float value) {
        return new Vector2f(x * value, y * value);
    }

    @Pure
    public Vector2f rotate(float angleRad) {
        float ca = Maths.cos(angleRad);
        float sa = Maths.sin(angleRad);
        return new Vector2f(ca * x - sa * y, sa * x + ca * y);
    }

    @Pure
    public Vector2f rotate(float angleRad, Vector2f pivot) {
        return pivot.plus(
                (x - pivot.x) * Maths.cos(angleRad) - (y - pivot.y) * Maths.sin(angleRad),
                (x - pivot.x) * Maths.sin(angleRad) + (y - pivot.y) * Maths.cos(angleRad)
        );
    }

    @Pure
    public Vector2f lerp(Vector2f target, float t) {
        return target.minus(this).scale(t).plus(this);
    }

    @Override
    public String toString() {
        return "vector2f(x: " + x + ", y: " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2f vector2f = (Vector2f) o;

        return Float.compare(vector2f.x, x) == 0 && Float.compare(vector2f.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }

    @Pure
    public Vector2f normalise() {
        float l = length();
        if (l == 0F) {
            return new Vector2f();
        }
        return scale(1F / l);
    }

    @Pure
    public float dot(Vector2f v) {
        return x * v.x + y * v.y;
    }

    @Pure
    public Vector2f times(Vector2f vector2f) {
        return times(vector2f.x, vector2f.y);
    }

    @Pure
    public Vector2f times(float x, float y) {
        return new Vector2f(this.x * x, this.y * y);
    }

    @Pure
    public float lengthSq() {
        return dot(this);
    }

    @Pure
    public float length() {
        return Maths.sqrt(lengthSq());
    }

    public static float dist(float x1, float y1, float x2, float y2) {
        return Maths.sqrt(dist2(x1, y1, x2, y2));
    }

    public static float dist2(float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;

        return dx * dx + dy * dy;
    }

    public Vector2f scale(float value, float pivotX, float pivotY) {
        return scale(value, new Vector2f(pivotX, pivotY));
    }

    public Vector2f scale(float value, Vector2f pivot) {
        return minus(pivot).scale(value).plus(pivot);
    }
}
