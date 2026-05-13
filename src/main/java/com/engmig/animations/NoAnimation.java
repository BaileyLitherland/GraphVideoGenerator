package com.engmig.animations;

import com.engmig.Drawable;
import javafx.scene.canvas.GraphicsContext;

public class NoAnimation extends Animation {
    Drawable object;

    public NoAnimation(Drawable object){
        this.object = object;
    }
    @Override
    public void update(GraphicsContext gc, int frameNum) {
        object.draw(gc,frameNum);
    }

    @Override
    public Drawable getObject() {
        return object;
    }

    @Override
    public void setFunction() {

    }

    @Override
    public int getEndFrame() {
        return 0;
    }
}
