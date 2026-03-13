package com.engmig.animations;

import com.engmig.Drawable;
import javafx.scene.canvas.GraphicsContext;

public abstract class Animation {
    public abstract void update(GraphicsContext gc, int frameNum);

    public abstract Drawable getObject();
}
