package com.engmig;

import javafx.scene.canvas.GraphicsContext;

import javax.vecmath.Vector3d;

public interface Drawable {

    public void draw(GraphicsContext gc, int frameNum);

    public void move(Vector3d moveVector);

    public void setPos(Vector3d pos);

}
