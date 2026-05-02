package com.engmig;

import com.engmig.FDGSolvers.FruchReinFDG;
import com.engmig.graphs.AdjacencyList;
import com.engmig.graphs.Graph;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.IOException;

public class GraphicsController {

    private static GraphicsController graphicsController;
    private static GraphicsContext gc;
    private static AnimationScheduler animationScheduler;
    private static FruchReinFDG FDG;
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
            //recorder.start();
            graph = new AdjacencyList();
            //graph.makeNVerticesOnCircle(4,3840/2,2160/2,750);
            //graph.addRandomEdges(4);
            //animationScheduler.makeGraphAppear(graph);
            //graph.addRandomEdges(1000);
            Vertex v1 = new Vertex(3840/2 + 500 ,2160/2+500);
            //Vertex v2 = new Vertex(3840/2 - 500 ,2160/2-500);
            //Vertex v3 = new Vertex(3840/2 - 500 ,2160/2+500);
            Vertex v4 = new Vertex(3840/2 + 500 ,2160/2-500);
//
//            graph = new AdjacencyList();
            graph.addVertex(v1);
            graph.addVertex(v4);
//            graph.addVertex(v3);
//            graph.addVertex(v4);
//
            graph.addEdge(0,1);
            //graph.addEdge(2,1);
            //graph.addEdge(2,3);
            //graph.addEdge(0,3);

            graph.addEdge(v1,v4);
            animationScheduler.makeEdgesAppear(graph);
            graph.size();
            //animationScheduler.makeGraphAppear(graph);
            //EaseOutCubicTransformation trans1 = new EaseOutCubicTransformation(v1, new Vector3d(3840/2 + 500 ,2160/2-500,0),new Vector3d(3840/2 +1500 ,2160/2-500,0),50,3);
            //EaseOutCubicTransformation trans4 = new EaseOutCubicTransformation(v4, new Vector3d(3840/2 + 500 ,2160/2-500,0),new Vector3d(3840/2 + 500 ,2160/2-500,0),1,3);
            //EaseOutCubicTransformation trans2 = new EaseOutCubicTransformation(v2, new Vector3d(3840/2 + 500 ,2160/2+500,0),new Vector3d(3840/2+1500,2160/2+500,0),50,3);
            //EaseOutCubicTransformation trans3 = new EaseOutCubicTransformation(v3, new Vector3d(3840/2 + 500 ,2160/2+500,0),new Vector3d(3840/2 + 500 ,2160/2+500,0),1,3);

            //FDG = new FruchReinFDG(graph);
//            animationScheduler.addAnimation(trans1);
//            animationScheduler.addAnimation(trans2);
//            animationScheduler.addAnimation(trans3);
//            animationScheduler.addAnimation(trans4);

        }

        // In here update the canvas based on the graph

        gc.setFill(Color.web("#2B2B2B"));



        count += 1;
        if (count == 50){
           recorder.stop();
       }
        if (count < 50 && count >= 0){

            if (count > 40){
                //FDG.update();
                animationScheduler.drawGraph(graph);
                //animationScheduler.drawGraph(graph);
            }
            animationScheduler.update(gc);
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
