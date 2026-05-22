package com.engmig.animations.EasingFunctions;

public class EaseInBounce extends Function{

    @Override
    public Double function(Double x) {
        double x1 = 1-x;
        double n1 = 7.5625;
        double  d1 = 2.75;

        if (x1 < 1 / d1) {
            return 1- (n1 * x1 * x1);
        } else if (x1 < 2 / d1) {
            return 1- (n1 * (x1 -= 1.5 / d1) * x1 + 0.75);
        } else if (x1 < 2.5 / d1) {
            return 1-(n1 * (x1 -= 2.25 / d1) * x1 + 0.9375);
        } else {
            return 1-(n1 * (x1 -= 2.625 / d1) * x1 + 0.984375);
        }
    }
}
