package com.engmig.animations;

import com.engmig.Drawable;
import javafx.scene.canvas.GraphicsContext;

import javax.vecmath.Vector3d;

public abstract class Animation {
    // has a drawable object
    // start location // if empty start location and end location are the same location
    // end location
    // animation length // if empty animation length is 0
    Drawable object;
    Vector3d startPosition;
    Vector3d endPosition;
    int numFrames;
    int startFrame;

    public Animation (Drawable object, Vector3d startPosition, Vector3d endPosition, int numFrames, int startFrame){
        this.object = object;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.numFrames = numFrames;
        this.startFrame = startFrame;

    }

    public abstract void draw(GraphicsContext gc, int frameNum);

}
