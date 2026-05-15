package com.engmig.animations;

import com.engmig.Drawable;
import com.engmig.animations.EasingFunctions.Function;
import javafx.scene.canvas.GraphicsContext;

public abstract class Animation {

    public abstract void update(GraphicsContext gc, int frameNum);

    public abstract Drawable getObject();

    public abstract void setFunction(Function function);

    public abstract int getEndFrame();
}
