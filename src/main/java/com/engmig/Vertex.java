package com.engmig;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.jcodec.containers.mp4.SampleOffsetUtils;
import org.jcodec.scale.ColorUtil;

import javax.vecmath.Vector3d;
public class Vertex implements Drawable {
    Vector3d pos;
    double radius = 200;
    Color colour = Color.DARKKHAKI;
    Vector3d disp;
    public Vertex(double x, double y){
        pos = new Vector3d(x,y,0);
    }

    public Vertex(){
        pos = new Vector3d();
    }

    public void draw(GraphicsContext gc, int frameNum){
        gc.setFill(colour);
        gc.fillOval(pos.getX()-radius/2, pos.getY()-radius/2, radius,radius);

    }

    public void setSize(double size) {
        radius = size;
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
