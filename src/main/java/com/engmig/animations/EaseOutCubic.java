package com.engmig.animations;

public class EaseOutCubic extends Function{

    @Override
    public Double function(Double x) {

        return 1 - Math.pow(1 - x, 3);
    }
}
