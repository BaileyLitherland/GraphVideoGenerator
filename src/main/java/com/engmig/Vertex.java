package com.engmig;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import javax.vecmath.Vector3d;
public class Vertex implements Drawable {
    Vector3d pos;
    double radius = 10;
    double textSize = 0;

    Color colour = Color.DARKKHAKI;
    String dispText = "-";
    Vector3d disp;

    public Vertex(double x, double y){
        pos = new Vector3d(x,y,0);
    }



    public Vertex(){
        pos = new Vector3d();
    }

    public void draw(GraphicsContext gc, int frameNum){
        gc.setFill(colour);
        gc.fillOval(pos.getX()- (radius/2), pos.getY()-(radius/2), radius,radius);

        //Align text to center
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);

        if (textSize > 0 ) {
            gc.setFont(new Font("Arial", textSize));

            gc.setFill(Paint.valueOf("#4E4D80"));
            gc.fillText(dispText, pos.getX(), pos.getY()-10);
        }
    }

    public void setSize(double size) {
        radius = size;
        textSize = size;
    }

    public void move(Vector3d moveVector) {
        this.pos.add(moveVector);
    }

    @Override
    public void setPos(Vector3d pos) {
        this.pos = pos;
    }

    public Vector3d getPos(){
        return pos;
    }


    public double getSize(){
        return radius;
    }

    public void setDisp(Vector3d d){
        disp = d;
    }

    public Vector3d getDisp(){
        return disp;
    }

    public void addDisp(Vector3d v){
        disp.add(v);
    }





}
