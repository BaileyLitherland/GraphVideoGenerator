package com.engmig;

import javafx.scene.canvas.GraphicsContext;

import javax.vecmath.Vector3d;


public class Vertex {
    Vector3d pos;
    boolean ignoreVertex;
    public Vertex(double x, double y){
        pos = new Vector3d(x,y,0);
    }

    public Vertex(){
        pos = new Vector3d();
    }

    public Vertex(Boolean ignore){
        ignoreVertex = ignore;
        pos = new Vector3d();
    }

    public void draw(GraphicsContext gc){
        gc.fillOval(pos.getX()-5, pos.getY()-5, 10,10);
    }

    public void moveTo(double x, double y){
        pos.set(x,y,0);
    }

}
