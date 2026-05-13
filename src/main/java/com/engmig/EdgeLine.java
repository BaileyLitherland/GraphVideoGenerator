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

    public EdgeLine(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void draw(GraphicsContext gc, int frameNum) {

        if (x1 != x2 || y1 != y2) {

            gc.setStroke(Paint.valueOf("#4E4D80"));
            gc.setLineWidth(5);
            gc.strokeLine(x1, y1, x2, y2);
        }
    }

    public void move(Vector3d moveVector) {
        this.x2 -= moveVector.x;
        this.y2 -= moveVector.y;
    }

    public void setPos(Vector3d pos) {

    }

    @Override
    public Vector3d getPos() {
        return null;
    }

    public void setSize(double size) {

    }

    public double getSize() {
        return 0;
    }
}
