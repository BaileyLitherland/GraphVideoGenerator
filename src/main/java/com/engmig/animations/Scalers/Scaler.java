package com.engmig.animations.Scalers;

import com.engmig.Drawable;
import com.engmig.animations.Animation;
import com.engmig.animations.Function;
import javafx.scene.canvas.GraphicsContext;

import javax.vecmath.Vector3d;

public class Scaler extends Animation {
    Drawable object;
    int startFrame;
    int numFrames;
    double startSize;
    double endSize;
    Function function;

    public Scaler(Drawable object, int startFrame, int numFrames, double startSize, double endSize){
        this.object = object;
        this.startFrame = startFrame;
        this.numFrames = numFrames;
        this.startSize = startSize;
        this.endSize = endSize;
    }

    public void update(GraphicsContext gc, int frameNum){
        double sizeDifference = endSize - startSize;
        System.out.println(startSize + (function.function(((double)(frameNum-startFrame)/(double)numFrames)) * sizeDifference));
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
