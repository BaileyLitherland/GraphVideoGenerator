package com.engmig;

import javafx.scene.canvas.GraphicsContext;

import javax.vecmath.Vector3d;

public interface Drawable {

    void draw(GraphicsContext gc, int frameNum);

    void move(Vector3d moveVector);

    void setPos(Vector3d pos);

    Vector3d getPos();

    void setSize(double size);

    double getSize();

}
