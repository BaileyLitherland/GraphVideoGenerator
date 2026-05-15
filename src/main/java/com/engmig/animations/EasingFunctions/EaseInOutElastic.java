package com.engmig.animations.EasingFunctions;

public class EaseInOutElastic extends Function{
    ///  This function is taken from https://easings.net/#easeInOutElastic
    @Override
    public Double function(Double x) {
        double c5 = (2 * Math.PI) / 4.5;

        if (x == 0){
            return 0.0;
        } else if (x == 1) {
            return 1.0;
        } else if (x < .5) {
            return -(Math.pow(2, 20 * x - 10) * Math.sin((20 * x - 11.125) * c5)) / 2;
        }else{
            return (Math.pow(2, -20 * x + 10) * Math.sin((20 * x - 11.125) * c5)) / 2 + 1;
        }
    }
}
