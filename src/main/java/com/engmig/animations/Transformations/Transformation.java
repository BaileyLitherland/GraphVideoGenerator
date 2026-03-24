package com.engmig.animations.Transformations;

import com.engmig.Drawable;
import javafx.scene.canvas.GraphicsContext;

import javax.vecmath.Vector3d;

public abstract class Transformation extends com.engmig.animations.Animation {
    // has a drawable object
    // start location // if empty start location and end location are the same location
    // end location
    // animation length // if empty animation length is 0


    public Transformation(Drawable object, Vector3d startPosition, Vector3d endPosition, int numFrames, int startFrame){


    }

    public abstract void update(GraphicsContext gc, int frameNum);

}
