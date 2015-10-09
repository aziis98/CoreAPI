package com.aziis98.dare.interfaces;

import com.aziis98.dare.math.*;

public interface IPos2f {

    float getX();

    float getY();

    default Vector2f getPosition() {
        return new Vector2f(getX(), getY());
    }

}
