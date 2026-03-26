package com.engmig;

import com.engmig.FDGSolvers.FruchReinFDG;
import com.engmig.FDGSolvers.NaiveFDG;
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
            graph.makeRndGraph(25,40);
            animationScheduler.makeGraphAppear(graph);
            FDG = new FruchReinFDG(graph);

        }

        // In here update the canvas based on the graph

        gc.setFill(Color.web("#2B2B2B"));

        animationScheduler.update(gc);

        count += 1;
        if (count == 400){
           recorder.stop();
       }
        if (count < 400 && count >= 0){
            //animationScheduler.drawGraph(graph);
            if (count > 100){
                animationScheduler.drawGraph(graph);
                FDG.update();
            }
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
