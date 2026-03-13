package com.engmig.animations.Scalers;

import com.engmig.Drawable;
import com.engmig.animations.Animation;
import javafx.scene.canvas.GraphicsContext;

public abstract class Scaler extends Animation {
    Drawable object;
    int startFrame;
    int numFrames;
    double startSize;
    double endSize;

    public Scaler(Drawable object, int startFrame, int numFrames, double startSize, double endSize){
        this.object = object;
        this.startFrame = startFrame;
        this.numFrames = numFrames;
        this.startSize = startSize;
        this.endSize = endSize;
    }

    abstract public void update(GraphicsContext gc, int frameNum);


}
