package com.engmig;

import javafx.scene.canvas.GraphicsContext;

import javax.vecmath.Vector3d;

public class Vertex {
    Vector3d pos;

    public Vertex(double x, double y){
        pos = new Vector3d(x,y,0);
    }

    public void draw(GraphicsContext gc){
        gc.fillOval(pos.getX()-5, pos.getY()-5, 10,10);
    }

    //public void moveTo(double x, double y, AnimationTimer)
}
