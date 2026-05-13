package com.engmig.animations;

public class EaseOutCubic extends Function{

    @Override
    public Double function(Double x) {
        System.out.println(" in easeOutCubic x:"+  x);
        System.out.println("Within ease Out cubic" + (1 - Math.pow(1 - x, 3)));
        return 1 - Math.pow(1 - x, 3);
    }
}
