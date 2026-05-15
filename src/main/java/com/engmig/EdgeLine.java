package com.engmig;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import javax.vecmath.Vector3d;

public class EdgeLine implements Drawable{
    double x1;
    double y1;
    double x2;
    double y2;
    Vertex v1;
    Vertex v2;
    Vector3d endOfLine;
    double width = 5;
    public EdgeLine(Vertex v1, Vertex v2){
        this.v1 = v1;
        this.v2 = v2;
        this.endOfLine = v2.getPos();
        //System.out.println("v1/v2 in the edgeline class: " + v1 + v2);
    }

    public void draw(GraphicsContext gc, int frameNum) {
        //System.out.println("v1/v2 in the edgeline draw: " + v1 + v2);
        if (v1.getPos().x != endOfLine.x || v1.getPos().y != endOfLine.y) {

            gc.setStroke(Paint.valueOf("#4E4D80"));
            gc.setLineWidth(width);
            gc.strokeLine(v1.getPos().x, v1.getPos().y, endOfLine.x, endOfLine.y);
            //gc.strokeLine(v1.getPos().x, v1.getPos().y, v2.getPos().x, v2.getPos().y);

//            System.out.println("v1 / v2: " + v1 + " / " + v2);
        }
    }

    public void move(Vector3d moveVector) {
        //v2.add(moveVector);
    }

    public void setPos(Vector3d pos) {
        endOfLine = pos;
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

    public void setEndOfLine(Vector3d pos){
        endOfLine = pos;
    }
}
