package com.engmig.animations;

import com.engmig.Drawable;
import javafx.scene.canvas.GraphicsContext;
import org.jcodec.common.DictionaryCompressor;

import javax.vecmath.Vector3d;

public class LinearAnimation extends Animation {

        // From the abstract animation class
        Drawable object;
        Vector3d startPosition;
        Vector3d endPosition;
        int numFrames;
        int startFrame;


        // Working out the next x position of the drawable
        double deltaX;
        double deltaY;

        public LinearAnimation(Drawable object, Vector3d startPosition, Vector3d endPosition, int numFrames, int startFrame){
            super(object,startPosition, endPosition, numFrames, startFrame);
            this.object = object;
            this.numFrames = numFrames;
            this.startFrame = startFrame;
            deltaX = (startPosition.getX() - endPosition.getX())/numFrames;
            deltaY = (startPosition.getY() - endPosition.getY())/numFrames;
            //System.out.println(object + " " +  this.object);
        }


    public void draw(GraphicsContext gc, int frameNum){

            if (frameNum == startFrame) {
                object.setPos(startPosition);
            }
            if (frameNum > startFrame && frameNum - startFrame < numFrames){
                // System.out.println(object);
                // System.out.println("End Frame: " + numFrames + " Current Frame: " + frameNum);
                object.move(new Vector3d(deltaX, deltaY,0));
                object.draw(gc,frameNum);
            }

            if (frameNum > 0){
                object.draw(gc,frameNum);
            }

        }
}
