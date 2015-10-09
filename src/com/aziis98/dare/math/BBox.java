package com.aziis98.dare.math;

import com.aziis98.dare.interfaces.*;

public class BBox {

    private float cx, cy;
    private float halfWidth, halfHeight;

    public BBox(Vector2f center, float width, float height) {
        this(center.x, center.y, width / 2, height / 2);
    }

    public BBox(float cx, float cy, float halfWidth, float halfHeight) {
        this.cx = cx;
        this.cy = cy;
        this.halfWidth = halfWidth;
        this.halfHeight = halfHeight;
    }

    public static BBox create(float x, float y, float width, float height) {
        return new BBox(x - width / 2, y - height / 2, width / 2, height / 2);
    }


    public BBox set(Vector2f center, float width, float height) {
        return set(center.x, center.y, width / 2, height / 2);
    }

    public BBox set(float cx, float cy, float halfWidth, float halfHeight) {
        this.cx = cx;
        this.cy = cy;
        this.halfWidth = halfWidth;
        this.halfHeight = halfHeight;
        return this;
    }


    public BBox expandAllSides(float amount) {
        return new BBox(getCenterX(), getCenterY(), getHalfWidth() + amount, getHalfHeight() + amount);
    }


    public BBox setCenterX(float cx) {
        this.cx = cx;
        return this;
    }

    public BBox setCenterY(float cy) {
        this.cy = cy;
        return this;
    }

    public BBox setCenter(float x, float y) {
        return setCenterX(x).setCenterY(y);
    }

    public BBox setCenter(Vector2f center) {
        return setCenter(center.x, center.y);
    }


    public float getCenterX() {
        return cx;
    }

    public float getCenterY() {
        return cy;
    }

    public Vector2f getCenter() {
        return new Vector2f(getCenterX(), getCenterY());
    }

    public float getHalfWidth() {
        return halfWidth;
    }

    public float getHalfHeight() {
        return halfHeight;
    }

    public float getWidth() {
        return halfWidth * 2;
    }

    public float getHeight() {
        return halfHeight * 2;
    }


    public Vector2f get00() {
        return new Vector2f(cx - halfWidth, cy - halfHeight);
    }

    public Vector2f get10() {
        return new Vector2f(cx + halfWidth, cy - halfHeight);
    }

    public Vector2f get01() {
        return new Vector2f(cx - halfWidth, cy + halfHeight);
    }

    public Vector2f get11() {
        return new Vector2f(cx + halfWidth, cy + halfHeight);
    }


    public float getX1() {
        return cx - halfWidth;
    }

    public float getY1() {
        return cy - halfHeight;
    }

    public float getX2() {
        return cx + halfWidth;
    }

    public float getY2() {
        return cy + halfHeight;
    }


    public boolean contains(float x, float y) {
        return Maths.inRange(getX1(), x, getX2()) &&
                Maths.inRange(getY1(), y, getY2());
    }

    public boolean contains(IPos2f v) {
        return contains(v.getX(), v.getY());
    }

    public boolean intersects(BBox b) {
        return (Math.abs(cx - b.cx) <= halfWidth + b.halfWidth) || (Math.abs(cy - b.cy) <= halfHeight + b.halfHeight);
    }


    @Override
    public String toString() {
        return String.format("{cx: %s, cy: %s, hw: %s, hy: %s}", cx, cy, halfWidth, halfHeight);
    }
}



























