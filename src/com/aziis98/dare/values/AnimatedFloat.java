package com.aziis98.dare.values;

import com.aziis98.dare.system.*;

public class AnimatedFloat {

    public float value;

    private float start;
    private float end;

    private long  startTime;
    private long  duration;

    private boolean animating = false;

    public AnimatedFloat(float value) {
        this.value = value;
    }

    public void animateTo(float target, long duration) {
        this.start = value;
        this.end = target;
        this.startTime = Time.milliTime();
        this.duration = duration;

        animating = true;
    }

    public void update() {
        if (animating)
        {
            float t = (float) (Time.milliTime() - startTime) / (float) duration;
            if (t >= 1F)
            {
                animating = false;
                t = 1F;
            }
            this.value = (end - start) * t + start;
        }
    }

    public boolean isAnimating() {
        return animating;
    }
}
