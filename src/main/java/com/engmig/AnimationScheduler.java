package com.engmig;

import com.engmig.animations.Animation;
import com.engmig.animations.Scaler;

import com.engmig.animations.TranslateTo;
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

    public void addAnimation(Animation animation){
        animations.add(animation);
        //objects.add(animation.getObject());
    }

    public void addAnimationToStart(Animation animation){
        animations.add(0,animation);
        objects.add(0,animation.getObject());
    }

    public void update(GraphicsContext gc){
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

//    public void makeGraphAppear(Graph graph){
//        ArrayList<Animation> edgesAnimations = new ArrayList<Animation>();
//        ArrayList<Animation> verticesAnimations = new ArrayList<Animation>();
//        ArrayList<Vertex> vertices = graph.getVertices();
//        ArrayList<ArrayList<Integer>> edges = graph.getEdges();
//        int vNum = 0;
//        int count = 0;
//        for(Vertex v: vertices) {
//            //Animation scaler = new EaseInOutQuinScaler(v,2,10,0,25);
//            Animation scaler = new EaseInOutQuinScaler(v,count,1,0,30);
//
//
//            ArrayList<Integer> neighbours = graph.getNeighbours(vertices.get(vNum));
//            for(Integer n: neighbours){
//                if (n < vNum){
//                    EdgeLine el = new EdgeLine(v.pos.x, v.pos.y, v.pos.x, v.pos.y);
//                    Animation linearTransform = new LinearTranslateTo(el,v.pos,vertices.get(n).pos,20, count);
//                    //System.out.println("npos:" + n.pos + "v pos: " + v.pos);
//                    edgesAnimations.add(linearTransform);
//
//                }
//            }
//            verticesAnimations.add(scaler);
//            //animationScheduler.addAnimation(scaler);
//
//            vNum += 1;
//        }
//
//        for(Animation e: edgesAnimations){
//            addAnimation(e);
//            objects.add(e.getObject());
//        }
//        for(Animation v: verticesAnimations){
//            addAnimation(v);
//            objects.add(v.getObject());
//        }
//
//    }
//
//    public void moveToCircles(Graph graph, int numFrames, int startFrame){
//        animations.clear();
//        for (Vertex v: graph.getVertices()){
//            int Id = graph.getVertices().indexOf(v);
//            if (Id % 2 == 0 ){
//                double theta = ((Id/2) * 2 * Math.PI / 20);
//                System.out.println(Id/2);
//                Vector3d endVector = new Vector3d((3840/2-750)+110*Math.cos(theta),(2160/2)+110*Math.sin(theta),0);
//                //Vector3d endVector = new Vector3d(3840/2, 2160/2+ 100*Id,0);
//                createLinearAnimation(v,v.getPos(),endVector,numFrames, startFrame);
//                //createEaseOutAnimation(v,v.getPos(),endVector,numFrames, startFrame);
//
//                //v.setPos(endVector);
//            }
//            if (Id % 2 == 1 ){
//                double theta = ((Id/2) * 2 * Math.PI / 20);
//                System.out.println(Id/2);
//                //System.out.println(theta);
//                Vector3d endVector = new Vector3d((3840/2+750)+110*Math.cos(theta),(2160/2)+110*Math.sin(theta),0);
//                //createEaseOutAnimation(v,v.getPos(),endVector,numFrames, startFrame);
//                //v.setPos(endVector);
//                System.out.println(v.getPos());
//                createLinearAnimation(v,v.getPos(),endVector,numFrames, startFrame);
//                if (v.getPos() == null){
//                    System.out.println("We got him: "+ Id);
//                }
//                //System.out.println(endVector);
//            }
//        }
//    }
//
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
//    public void drawGraph(Graph graph){
//        //animations.clear();
//        objects.clear();
//        //System.out.println(animations);
//        ArrayList<Animation> edgesAnimations = new ArrayList<Animation>();
//        ArrayList<Animation> verticesAnimations = new ArrayList<Animation>();
//        ArrayList<Vertex> vertices = graph.getVertices();
//        int vNum = 0;
//        for(Vertex v: vertices) {
//            Animation noAniV = new NoAnimation(v);
//
//            ArrayList<Integer> neighbours = graph.getNeighbours(vertices.get(vNum));
//            for(Integer n: neighbours){
//                //System.out.println(v.getPos() + " " + n.getPos());
//                Animation noAniE = new NoAnimation(new EdgeLine(v.pos.x, v.pos.y, vertices.get(n).pos.x, vertices.get(n).pos.y));
//                edgesAnimations.add(noAniE);
//            }
//            //verticesAnimations.add(noAniV);
//            objects.add(v);
//
//            vNum += 1;
//        }
//
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
