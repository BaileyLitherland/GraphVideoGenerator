package com.engmig;

import com.engmig.animations.Animation;
import com.engmig.animations.Transformations.EaseOutCubicTransformation;
import com.engmig.animations.Transformations.LinearTransformation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.vecmath.Vector3d;
import java.util.ArrayList;

public class AnimationScheduler {
    // The intent of this class is to store a set of object
    // that will be looped through by the graphics controller to have the draw method called.
    // Each object in this list must be drawable.
    // A whole graph could be in the list or just a single vertex/edge.
    // Each object will be stored as an object, animation tuple.
    // an animation will be a start location, an end location and how it gets there
    // If no animation is given then it'll just draw the current position.
    // Functionally this is mostly just for the video parts of the graphics, I don't believe that animations will be used
    // in any final product outside of video animations.


    ArrayList<com.engmig.animations.Animation> animations = new ArrayList<Animation>();
    int frameCount = 0;
    ArrayList<Drawable> objects = new ArrayList<Drawable>();

    public void addAnimation(Animation animation){
        animations.add(animation);
        objects.add(animation.getObject());
    }

    public void createLinearAnimation(Drawable object, Vector3d startPosition, Vector3d endPosition, int numFrames, int startFrame){
        LinearTransformation animation = new LinearTransformation(object,startPosition, endPosition, numFrames, startFrame);
        addAnimation(animation);
    }

    public void createEaseOutAnimation(Drawable object, Vector3d startPosition, Vector3d endPosition, int numFrames, int startFrame){
        EaseOutCubicTransformation animation = new EaseOutCubicTransformation(object,startPosition, endPosition, numFrames, startFrame);
        addAnimation(animation);
    }

    public void update(GraphicsContext gc){
        //clear the canvas
        gc.setFill(Color.web("#2B2B2B"));

        gc.fillRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // Current Vertex colour TODO: Set in the drawable object
        gc.setFill(Color.DARKKHAKI);
        frameCount += 1;

        if (!animations.isEmpty()) {
            for (Animation animations : animations) {
                //System.out.println("drawing object in animation scheduler");
                animations.update(gc, frameCount);
            }
            for (Drawable drawable: objects){
                drawable.draw(gc,frameCount);
            }
        }
    }


}
