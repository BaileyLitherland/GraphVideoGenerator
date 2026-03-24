package com.engmig.animations.Scalers;

import com.engmig.Drawable;
import com.engmig.animations.Transformations.Transformation;
import javafx.scene.canvas.GraphicsContext;

import javax.vecmath.Vector3d;

public class EaseInOutQuinScaler extends Scaler {
    double sizeDiff;
    Drawable object;
    int startFrame;
    int numFrames;
    double startSize;
    double endSize;

    public EaseInOutQuinScaler(Drawable object, int startFrame, int numFrames, double startSize, double endSize) {
        super(object, startFrame, numFrames, startSize, endSize);
        sizeDiff = startSize - endSize;
        this.object = object;
        this.startFrame = startFrame;
        this.numFrames = numFrames;
        this.startSize = startSize;
        this.endSize = endSize;
    }

    @Override
    public void update(GraphicsContext gc, int frameNum) {
        if (frameNum == 0){
            object.setSize(startSize);
        }
        if (frameNum > startFrame && frameNum < startFrame + numFrames) {
            double oldSize = object.getSize();
            double deltaSize = sizeDiff * easeInOutQuint((double) (frameNum - startFrame) / numFrames) - sizeDiff * easeInOutQuint((double) (frameNum - startFrame - 1) / numFrames);
            object.setSize(oldSize - deltaSize);
        }
    }

    public Drawable getObject() {
        return object;
    }

    private double easeInOutQuint(double x){
        return x < 0.5 ? 16 * x * x * x * x * x : 1 - Math.pow(-2 * x + 2, 5) / 2;
    }


}
