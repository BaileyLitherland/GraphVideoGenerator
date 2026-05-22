package com.engmig;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import javax.vecmath.Vector3d;

public class NonAnimateableEdge implements Drawable{

    Vertex v1;
    Vertex v2;
    double width = 5;

    public NonAnimateableEdge(Vertex v, Vertex u){
        this.v1 = v;
        this.v2 = u;
    }

    @Override
    public void draw(GraphicsContext gc, int frameNum) {
        if (v1.getPos().x != v2.getPos().x || v1.getPos().y != v2.getPos().y) {

            gc.setStroke(Paint.valueOf("#4E4D80"));
            gc.setLineWidth(width);
            //gc.strokeLine(v1.getPos().x, v1.getPos().y, endOfLine.x, endOfLine.y);
            gc.strokeLine(v1.getPos().x, v1.getPos().y, v2.getPos().x, v2.getPos().y);
        }
    }
    public void move(Vector3d moveVector) {
        //v2.add(moveVector);
    }

    public void setPos(Vector3d pos) {

    }

    @Override
    public Vector3d getPos() {
        return v1.getPos();
    }

    public void setSize(double size) {
        width = size;
    }

    public double getSize() {
        return width;
    }
}
