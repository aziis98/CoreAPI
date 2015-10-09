package com.aziis98.dare.math;

import com.aziis98.dare.annotations.*;
import com.aziis98.dare.interfaces.*;

public class Vector3f implements IPos3f {

    public float x, y, z;

    public Vector3f() {
        this(0, 0, 0);
    }

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
    public float getZ() {
        return z;
    }

    @Override
    public Vector3f getPosition() {
        return this;
    }


    @Pure
    public Vector3f plus(Vector3f v) {
        return new Vector3f(x + v.x, y + v.y, z + v.z);
    }

    public Vector3f scale(float value) {
        return new Vector3f(x * value, y * value, z * value);
    }
}
