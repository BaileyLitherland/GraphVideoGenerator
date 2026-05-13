package com.engmig.animations.Transformations;

import com.engmig.Drawable;
import javafx.scene.canvas.GraphicsContext;

import javax.vecmath.Vector3d;

public class LinearTranslateTo extends TranslateTo {

        // From the abstract animation class
        Drawable object;
        Vector3d startPosition;
        Vector3d endPosition;
        int numFrames;
        int startFrame;


        // Working out the next x position of the drawable
        double deltaX;
        double deltaY;

        public LinearTranslateTo(Drawable object, Vector3d startPosition, Vector3d endPosition, int numFrames, int startFrame){
            super(object,startPosition, endPosition, numFrames, startFrame);
            this.object = object;
            this.numFrames = numFrames;
            this.startFrame = startFrame;
            deltaX = (startPosition.getX() - endPosition.getX())/numFrames;
            deltaY = (startPosition.getY() - endPosition.getY())/numFrames;
            //System.out.println(object + " " +  this.object);
        }


    public void update(GraphicsContext gc, int frameNum){

            if (frameNum == startFrame) {
                object.setPos(startPosition);
            }
            if (frameNum >= startFrame && frameNum - startFrame <= numFrames){
                // System.out.println(object);
                // System.out.println("End Frame: " + numFrames + " Current Frame: " + frameNum);
                object.move(new Vector3d(deltaX, deltaY,0));
                //object.draw(gc,frameNum);
            }

//            if (frameNum > 0){
//                // TODO: Remove this part and replace it in animation scheduler
//                object.draw(gc,frameNum);
//            }

        }

    /**
     * @return
     */
    @Override
    public Drawable getObject() {
       return object;
    }
}
