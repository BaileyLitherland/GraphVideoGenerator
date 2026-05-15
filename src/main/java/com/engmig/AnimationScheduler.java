package com.engmig;

import com.engmig.animations.Animation;
import com.engmig.animations.EasingFunctions.*;
import com.engmig.animations.Scaler;

import com.engmig.animations.TranslateTo;
import com.engmig.graphs.Graph;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.vecmath.Vector3d;
import java.util.ArrayList;

public class AnimationScheduler {
    // The intent of this class is to store a set of object
    // that will be looped through by the graphics controller to have the draw method called.
    // Each object in this list must be drawable.
    // A whole graph could be in the list or just a single vertex/edge.
    // Each object will be stored as an object, animation tuple.
    // an animation will be a start location, an end location and how it gets there
    // If no animation is given then it'll just draw the current position.
    // Functionally this is mostly just for the video parts of the graphics, I don't believe that animations will be used
    // in any final product outside of video animations.


    ArrayList<com.engmig.animations.Animation> animations = new ArrayList<Animation>();
    int frameCount = 0;
    ArrayList<Drawable> objects = new ArrayList<Drawable>();

    public void update(GraphicsContext gc){
        //System.out.println("animations: " + animations);
        //clear the canvas
        gc.setFill(Color.web("#2B2B2B"));

        gc.fillRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        // Current Vertex colour TODO: Set in the drawable object
        gc.setFill(Color.DARKKHAKI);
        frameCount += 1;

        if (!animations.isEmpty()) {
            for (int i = animations.size()-1; i >= 0; i--) {
                // remove animations that are finished
                if (animations.get(i).getEndFrame() < frameCount){
                    animations.remove(i);
                }else{
                    animations.get(i).update(gc, frameCount);
                }
            }
        }
        if (!objects.isEmpty()){
            for (Drawable drawable: objects){
                drawable.draw(gc,frameCount);
            }
        }
    }

    public void makeGraphAppear(Graph graph, int numFrames,int startFrame, int vertexSize){
        int currentFrameNum = 0;
        int numV = graph.size();
        int vertexScaleTime = numFrames/(numV + 1);
        for (int i = 0; i < numV; i++){
            Vertex v = graph.getVertices().get(i);
            Scaler s = new Scaler(v, startFrame + currentFrameNum * (vertexScaleTime/2)
                    , vertexScaleTime, 0, vertexSize );
            s.setFunction(new EaseOutCubic());
            animations.add(s);
            currentFrameNum += 1;
            objects.add(v);
            for (int j: graph.getNeighbours(v)){
                if (i > j){
                    Vertex u = graph.getVertices().get(j);
                    Vector3d newStartingEndVector  = new Vector3d(v.getPos().getX(), v.getPos().getY(),0 );
                    EdgeLine edge = new EdgeLine(v, u);
                    edge.setEndOfLine(v.getPos());
                    //System.out.println("end pos in animation make graph appear method"  + u.getPos());
                    TranslateTo t = new TranslateTo(edge, u.getPos(), vertexScaleTime*2,startFrame + currentFrameNum * (vertexScaleTime/2)+ vertexScaleTime);
                    t.setFunction(new EaseOutCubic());
                    animations.add(t);

                    objects.add(0, edge);
                }
            }
        }
    }

    public void moveToCircles(Graph graph, int numFrames, int startFrame){
        System.out.println("Move to Circles");

        for (Vertex v : graph.getVertices()) {
            int Id = graph.getVertices().indexOf(v);
            if (Id % 2 == 0) {
                double theta = (((double) Id / 2) * 2 * Math.PI / (graph.size()/2));
                Vector3d endVector = new Vector3d(((double) 3840 / 2 - 750) + 500 * Math.cos(theta), ((double) 2160 / 2) + 500 * Math.sin(theta), 0);
                TranslateTo t = new TranslateTo(v, v.getPos(), endVector, numFrames, startFrame);
                t.setFunction(new EaseOutCubic());
//                    System.out.println("current pos:" + v.getPos());
//                    System.out.println("end pos:" + endVector);
                animations.add(t);


                    //v.setPos(endVector);
            }
            if (Id % 2 == 1) {
                double theta = ((Id / 2) * 2 * Math.PI / (graph.size()/2));
                Vector3d endVector = new Vector3d((3840 / 2 + 750) + 500 * Math.cos(theta), (2160 / 2) + 500 * Math.sin(theta), 0);
                TranslateTo t = new TranslateTo(v, v.getPos(), endVector, numFrames, startFrame);
                t.setFunction(new EaseOutCubic());
                animations.add(t);

            }
        }

    }

//    public void makeEdgesAppear(Graph graph){
//        ArrayList<Animation> edgesAnimations = new ArrayList<Animation>();
//        ArrayList<ArrayList<Integer>> edges = graph.getEdges();
//        ArrayList<Animation> verticesAnimations = new ArrayList<Animation>();
//        ArrayList<Vertex> vertices = graph.getVertices();
//        int vNum = 0;
//        int count = 100;
//        for(Vertex v: vertices) {
//            Animation noAni = new NoAnimation(v);
//            ArrayList<Integer> neighbours = graph.getNeighbours(vertices.get(vNum));
//            for(Integer n: neighbours){
//                if (n < vNum){
//                    Animation linearTransform = new LinearTranslateTo(new EdgeLine(v.pos.x, v.pos.y, v.pos.x, v.pos.y),v.pos,vertices.get(n).pos,30, count+(vNum*1)+1);
//                    edgesAnimations.add(linearTransform);
//                }
//            }
//            vNum += 1;
//            verticesAnimations.add(noAni);
//        }
//
//        for(Animation e: edgesAnimations){
//            addAnimationToStart(e);
//        }
//
//        for(Animation v: verticesAnimations){
//            addAnimation(v);
//        }
//    }
    public void drawGraph(Graph graph){
        objects.clear();
        int numV = graph.size();
        for (int i = 0; i < numV; i++){
            Vertex v = graph.getVertex(i);
            objects.add(v);
            for (int j: graph.getNeighbours(v)){
                if (i > j){
                    Vertex u = graph.getVertices().get(j);
                    NonAnimateableEdge edge = new NonAnimateableEdge(v, u);
                    objects.add(0, edge);
                }
            }
        }
    }

//        for(Animation e: edgesAnimations){
//            //System.out.println("animation added");
//            addAnimation(e);
//        }
////        for(Animation v: verticesAnimations){
////            addAnimation(v);
////        }
//        //System.out.println(animations.size());
//    }
//
//    public void scaleAllVertices(Graph graph,double endSize, int numFrames, int startFrame){
//        objects.clear();
//        for (Vertex v: graph.getVertices()) {
//            Animation scaler = new EaseInOutQuinScaler(v, startFrame, numFrames, v.getSize(), endSize);
//            addAnimation(scaler);
//        }
//
//    }

    public TranslateTo translateTo(Drawable object, Vector3d endPos, int numFrames, int startFrame ){
        TranslateTo animation = new TranslateTo(object, endPos, numFrames, startFrame);
        animations.add(animation);
        objects.add(object);
        return animation;
    }

    public Scaler scale(Drawable object, int startFrame, int numFrames, double startSize, double endSize){
        Scaler animation = new Scaler(object,  startFrame,  numFrames, startSize,endSize);
        animations.add(animation);
        objects.add(object);
        return animation;
    }

}
