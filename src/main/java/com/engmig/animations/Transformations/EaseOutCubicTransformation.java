package com.engmig.animations.Transformations;

import com.engmig.Drawable;
import javafx.scene.canvas.GraphicsContext;

import javax.vecmath.Vector3d;

public class EaseOutCubicTransformation extends Transformation {
    // From the abstract animation class
    Drawable object;
    Vector3d startPosition;
    Vector3d endPosition;
    int numFrames;
    int startFrame;


    // Working out the next x position of the drawable
    double deltaX;
    double deltaY;

    public EaseOutCubicTransformation(Drawable object, Vector3d startPosition, Vector3d endPosition, int numFrames, int startFrame) {
        super(object, startPosition, endPosition, numFrames, startFrame);
        this.object = object;
        this.numFrames = numFrames;
        this.startFrame = startFrame;
        this.startPosition = startPosition;
        deltaX = (startPosition.getX() - endPosition.getX());
        deltaY = (startPosition.getY() - endPosition.getY());
        //System.out.println(object + " " +  this.object);
    }

    private double easeOutCubic(double x){
        return 1 - Math.pow(1 - x, 3);
    }
    public void update(GraphicsContext gc, int frameNum){

        if (frameNum == startFrame) {
            //System.out.println("set pos");
            //System.out.println("ease out cubic update, frame num, startFrame:" + frameNum + " " +startFrame);
            object.setPos(startPosition);
        }
        if (frameNum > startFrame && frameNum - startFrame < numFrames){
            //System.out.println("ease out cubic update, frame num, startFrame:" + frameNum + " " +startFrame);
            object.move(new Vector3d(
                    deltaX * easeOutCubic((double) (frameNum-startFrame)/numFrames) - deltaX * easeOutCubic((double) (frameNum-startFrame - 1)/numFrames) ,
                    deltaY * easeOutCubic((double) (frameNum-startFrame)/numFrames) - deltaY * easeOutCubic((double) (frameNum-startFrame - 1)/numFrames) ,
                    0));
        }

        if (frameNum > startFrame){
            object.draw(gc,frameNum);
        }

    }

    /**
     * @return
     */
    @Override
    public Drawable getObject() {
       return object;
    }
}
