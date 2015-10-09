package com.aziis98.dare.math;

public class AABB {

    public float x, y, width, height;

    public AABB(float x, float y, float width, float height) {
        set(x, y, width, height);
    }

    public void set(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getCenterX() {
        return x + width / 2;
    }

    public float getCenterY() {
        return y + height / 2;
    }

    public boolean intersects(AABB aabb) {
        return contains(aabb.x, aabb.y) || contains(aabb.x + aabb.width, aabb.y) ||
                contains(aabb.x, aabb.y + aabb.height) || contains(aabb.x + aabb.width, aabb.y + aabb.height);
        // return aabb.contains(x, y) || aabb.contains(x + width, y) || aabb.contains(x, y + height) || aabb.contains(x + width, y + height);
    }

    public boolean contains(float x, float y) {
        return x >= this.x && y >= this.y && x <= this.x + this.width && y <= this.y + this.height;
    }

}
