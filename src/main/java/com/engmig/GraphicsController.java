package com.engmig;

import com.engmig.animations.Animation;
import com.engmig.animations.Scalers.EaseInOutQuinScaler;
import com.engmig.animations.Transformations.LinearTransformation;
import com.engmig.graphs.AdjacencyList;
import com.engmig.graphs.Graph;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.vecmath.Vector3d;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicsController {

    private static GraphicsController graphicsController;
    private static GraphicsContext gc;
    private static AnimationScheduler animationScheduler;
    private static boolean recording;
    private AdjacencyList graph;

    private GraphicsRecorder recorder;

    public GraphicsController(GraphicsContext graphicContext){
         gc = graphicContext;
         recorder = new GraphicsRecorder();
         animationScheduler = new AnimationScheduler();
    }

    public GraphicsController(){
        recorder = new GraphicsRecorder();
        animationScheduler = new AnimationScheduler();
    }
    // Static Factory Method
    public static GraphicsController getInstance(){
        if (graphicsController != null){
            graphicsController = new GraphicsController();
        }
        return graphicsController;
    }

    public static GraphicsController newGraphicsController(GraphicsContext graphicsContext){
        graphicsController = new GraphicsController(graphicsContext);
        return graphicsController;
    }

    public static GraphicsContext getGc() {
        return gc;
    }
    int count = 0;
    public void update() throws IOException {


        if (count == 0){
            recorder.start();
            graph = new AdjacencyList();
            graph.makeRndGraph(10,20);
            ArrayList<Animation> edgesAnimations = new ArrayList<Animation>();
            ArrayList<Animation> verticesAnimations = new ArrayList<Animation>();
            ArrayList<Vertex> vertices = graph.getVertices();
            ArrayList<ArrayList<Integer>> edges = graph.getEdges();
            int vNum = 0;
            for(Vertex v: vertices) {
                Animation scaler = new EaseInOutQuinScaler(v,count+(vNum*10),15,0,50);


                ArrayList<Vertex> neighbours = graph.getNeighbours(vNum);
                for(Vertex n: neighbours){
                    if (graph.getVertexIndex(n) < vNum){
                        Animation linearTransform = new LinearTransformation(new EdgeLine(v.pos.x, v.pos.y, v.pos.x, v.pos.y),v.pos,n.pos,7, count+(vNum*10)+10);
                        System.out.println("npos:" + n.pos + "v pos: " + v.pos);
                        edgesAnimations.add(linearTransform);
                    }
                }
                verticesAnimations.add(scaler);
                //animationScheduler.addAnimation(scaler);

                vNum += 1;
            }

            for(Animation e: edgesAnimations){
                animationScheduler.addAnimation(e);
            }
            for(Animation v: verticesAnimations){
                animationScheduler.addAnimation(v);
            }

        }


        // In here update the canvas based on the graph

        gc.setFill(Color.web("#2B2B2B"));

//        Vertex vertex = new Vertex(gc.getCanvas().getWidth()/2,gc.getCanvas().getHeight()/2);
        // Here is where we could also start to take the snapshots of the canvas
        // vertex.draw(gc, count);

        animationScheduler.update(gc);

        count += 1;
        if (count == 120){
           recorder.stop();
       }
        if (count < 120 && count >= 0){
            recorder.record(gc.getCanvas());
        }

//        try {
//
//            recorder.screenShot(gc.getCanvas());
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }

    public void startRecording(){
        recorder.start();
    }

    public void stopRecording(){
        recorder.stop();
    }
}
