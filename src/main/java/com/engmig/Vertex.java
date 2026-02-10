package com.engmig;

import javafx.scene.canvas.GraphicsContext;

import javax.vecmath.Vector3d;


public class Vertex {
    Vector3d pos;
    double radius = 20;

    public Vertex(double x, double y){
        pos = new Vector3d(x,y,0);
    }

    public Vertex(){
        pos = new Vector3d();
    }

    public void draw(GraphicsContext gc){
        gc.fillOval(pos.getX()-radius/2, pos.getY()-radius/2, radius,radius);
    }

}
