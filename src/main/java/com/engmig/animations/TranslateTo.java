package com.engmig.animations;

import com.engmig.Drawable;
import com.engmig.animations.EasingFunctions.Function;
import com.engmig.animations.EasingFunctions.Linear;
import javafx.scene.canvas.GraphicsContext;
import org.jcodec.containers.mp4.SampleOffsetUtils;

import javax.vecmath.Vector3d;

public class TranslateTo extends com.engmig.animations.Animation {
    final private Drawable object;
    private Function function = new Linear();
    private Vector3d startPosition;
    private Vector3d endPosition;
    private int numFrames;
    private int startFrame;
    private Vector3d SE = new Vector3d();

    public TranslateTo(Drawable object, Vector3d startPosition, Vector3d endPosition, int numFrames, int startFrame){
        this.object = object;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.numFrames = numFrames;
        this.startFrame = startFrame;
        //SE.sub(endPosition, startPosition);

    }

    public TranslateTo(Drawable object, Vector3d endPosition, int numFrames, int startFrame){
        this.object = object;
        this.startPosition = object.getPos();
        this.endPosition = endPosition;
        this.numFrames = numFrames;
        this.startFrame = startFrame;
        //SE.sub(this.endPosition, this.startPosition);
    }

    public void update(GraphicsContext gc, int frameNum){
        SE.sub(this.endPosition, this.startPosition);
        if (frameNum > startFrame) {

            Vector3d moveVector = new Vector3d(0, 0, 0);
            moveVector.scale(function.function(((double) (frameNum - startFrame) / (double) numFrames)), SE);
            moveVector.add(startPosition);

            object.setPos(moveVector);
        }
    }

    @Override
    public Drawable getObject() {
        return object;
    }

    @Override
    public int getEndFrame() {
        //System.out.println("endFrame: " + (startFrame + numFrames));
        return startFrame + numFrames;
    }

    public void setFunction(Function function) {
        this.function = function;
    }


}
