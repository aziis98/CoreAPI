package com.aziis98.dare.interfaces;

import com.aziis98.dare.math.*;

public interface IPos3f {

    float getX();

    float getY();

    float getZ();

    Vector3f getPosition();

    default Vector2f getXY() {
        return new Vector2f(getX(), getY());
    }

    default Vector2f getYZ() {
        return new Vector2f(getY(), getZ());
    }

    default Vector2f getXZ() {
        return new Vector2f(getX(), getZ());
    }

}
