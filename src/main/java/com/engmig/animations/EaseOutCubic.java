package com.engmig.animations;

import com.engmig.Drawable;
import javafx.scene.canvas.GraphicsContext;

import javax.vecmath.Vector3d;

public class EaseOutCubic extends Animation {
    // From the abstract animation class
    Drawable object;
    Vector3d startPosition;
    Vector3d endPosition;
    int numFrames;
    int startFrame;


    // Working out the next x position of the drawable
    double deltaX;
    double deltaY;

    public EaseOutCubic(Drawable object, Vector3d startPosition, Vector3d endPosition, int numFrames, int startFrame) {
        super(object, startPosition, endPosition, numFrames, startFrame);
        this.object = object;
        this.numFrames = numFrames;
        this.startFrame = startFrame;
        deltaX = (startPosition.getX() - endPosition.getX());
        deltaY = (startPosition.getY() - endPosition.getY());
        //System.out.println(object + " " +  this.object);
    }

    private double easeOutCubic(double x){
        return 1 - Math.pow(1 - x, 3);
    }
    public void draw(GraphicsContext gc, int frameNum){

        if (frameNum == startFrame) {
            object.setPos(startPosition);
        }
        if (frameNum > startFrame && frameNum - startFrame < numFrames){

            object.move(new Vector3d(
                    deltaX * easeOutCubic((double) (frameNum-startFrame)/numFrames) - deltaX * easeOutCubic((double) (frameNum-startFrame - 1)/numFrames) ,
                    deltaY * easeOutCubic((double) (frameNum-startFrame)/numFrames) - deltaY * easeOutCubic((double) (frameNum-startFrame - 1)/numFrames) ,
                    0));
            object.draw(gc,frameNum);
        }

        if (frameNum > 0){
            object.draw(gc,frameNum);
        }

    }
}
