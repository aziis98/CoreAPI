package com.aziis98.dare.math;

import java.awt.geom.*;

public class Line {

    private Vector2f a, b;

    public Line(Vector2f a, Vector2f b) {
        this.a = a;
        this.b = b;
    }

    public boolean intersects(Line other) {
        return Line2D.linesIntersect(a.x, a.y, b.x, b.y,
                other.a.x, other.a.y, other.b.x, other.b.y);
    }

}
