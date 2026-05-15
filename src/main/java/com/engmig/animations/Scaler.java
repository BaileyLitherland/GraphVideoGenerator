package com.engmig.animations;

import com.engmig.Drawable;
import com.engmig.animations.EasingFunctions.Function;
import com.engmig.animations.EasingFunctions.Linear;
import javafx.scene.canvas.GraphicsContext;

public class Scaler extends Animation {
    Drawable object;
    int startFrame;
    int numFrames;
    double startSize;
    double endSize;
    Function function = new Linear();

    public Scaler(Drawable object, int startFrame, int numFrames, double startSize, double endSize){
        this.object = object;
        this.startFrame = startFrame;
        this.numFrames = numFrames;
        this.startSize = startSize;
        this.endSize = endSize;
    }

    public void update(GraphicsContext gc, int frameNum){
        double sizeDifference = endSize - startSize;
        object.setSize(startSize + (function.function(((double)(frameNum-startFrame)/(double)numFrames)) * sizeDifference)); // Refactor please oml
    }

    @Override
    public Drawable getObject() {
        return object;
    }

    @Override
    public void setFunction(Function function) {
        this.function = function;
    }

    @Override
    public int getEndFrame() {
        return startFrame + numFrames;
    }


}
