//package com.engmig;
//
//import com.engmig.FDGSolvers.FruchReinFDG;
//import com.engmig.graphs.AdjacencyList;
//import com.engmig.graphs.Graph;
//import javafx.animation.AnimationTimer;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.paint.Color;
//
//import java.io.IOException;
//
//public class GraphicsController {
//
//    private static GraphicsController graphicsController;
//    private static GraphicsContext gc;
//    private static AnimationScheduler animationScheduler;
//    private static FruchReinFDG FDG;
//    private static boolean recording;
//    private AdjacencyList graph;
//
//    private AnimationTimer recordingTimer;
//    private static final long RECORD_INTERVAL_NS = 1_000_000_000L / 30; // 30fps in nanoseconds
//    private long lastRecordTime = 0;
//
//    private GraphicsRecorder recorder;
//
//    public GraphicsController(GraphicsContext graphicContext){
//         gc = graphicContext;
//         recorder = new GraphicsRecorder();
//         animationScheduler = new AnimationScheduler();
//    }
//
//    public GraphicsController(){
//        recorder = new GraphicsRecorder();
//        animationScheduler = new AnimationScheduler();
//    }
//    // Static Factory Method
//    public static GraphicsController getInstance(){
//        if (graphicsController != null){
//            graphicsController = new GraphicsController();
//        }
//        return graphicsController;
//    }
//
//    public static GraphicsController newGraphicsController(GraphicsContext graphicsContext){
//        graphicsController = new GraphicsController(graphicsContext);
//        return graphicsController;
//    }
//
//    public static GraphicsContext getGc() {
//        return gc;
//    }
//    int count = 0;
//    public void update() throws IOException {
//
//        if (count == 0){
//            //recorder.start();
//            graph = new AdjacencyList();
//            graph.makeNVerticesOnCircle(5,3840/2,2160/2,750);
//            //graph.addRandomEdges(4);
//            //animationScheduler.makeGraphAppear(graph);
//            //graph.addRandomEdges(1000);
////            Vertex v1 = new Vertex(3840/2 + 500 ,2160/2+500);
////            Vertex v = new Vertex(3840/2 + 500 ,2160/2+500);
////            Vertex v2 = new Vertex(3840/2 - 500 ,2160/2-500);
////            Vertex v3 = new Vertex(3840/2 - 500 ,2160/2+500);
////            Vertex v4 = new Vertex(3840/2 + 500 ,2160/2-500);
////
////            graph = new AdjacencyList();
////            graph.addVertex(v0);
////            graph.addVertex(v1);
////            graph.addVertex(v2);
////            graph.addVertex(v3);
////            graph.addVertex(v4);
//////
//            //graph.addEdge(0,1);
//            //graph.addEdge(2,1);
//            //graph.addEdge(2,3);
//            //graph.addEdge(0,3);
//
//            //graph.addEdge(v1,v4);
//            //animationScheduler.makeEdgesAppear(graph);
//            //graph.size();
//
//            //graph.getVertex(0);
//
////            graph.removeVertex(0);
//            graph.addEdge(0,2);
//            graph.addEdge(0,3);
//            graph.addEdge(1,2);
//            graph.addEdge(2,3);
//            animationScheduler.makeGraphAppear(graph);
//            //EaseOutCubicTransformation trans1 = new EaseOutCubicTransformation(v1, new Vector3d(3840/2 + 500 ,2160/2-500,0),new Vector3d(3840/2 +1500 ,2160/2-500,0),50,3);
//            //EaseOutCubicTransformation trans4 = new EaseOutCubicTransformation(v4, new Vector3d(3840/2 + 500 ,2160/2-500,0),new Vector3d(3840/2 + 500 ,2160/2-500,0),1,3);
//            //EaseOutCubicTransformation trans2 = new EaseOutCubicTransformation(v2, new Vector3d(3840/2 + 500 ,2160/2+500,0),new Vector3d(3840/2+1500,2160/2+500,0),50,3);
//            //EaseOutCubicTransformation trans3 = new EaseOutCubicTransformation(v3, new Vector3d(3840/2 + 500 ,2160/2+500,0),new Vector3d(3840/2 + 500 ,2160/2+500,0),1,3);
//
//            //FDG = new FruchReinFDG(graph);
////            animationScheduler.addAnimation(trans1);
////            animationScheduler.addAnimation(trans2);
////            animationScheduler.addAnimation(trans3);
////            animationScheduler.addAnimation(trans4);
//            // In whatever class runs your animation — add a separate recording timer
//
//// Start this alongside your main animation
//            recordingTimer = new AnimationTimer() {
//                @Override
//                public void handle(long now) {
//                    if (now - lastRecordTime >= RECORD_INTERVAL_NS) {
//                        try {
//                            recorder.record(gc);
//                            lastRecordTime = now;
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            };
//            recordingTimer.start();
//        }
//
//        // In here update the canvas based on the graph
//
//        gc.setFill(Color.web("#2B2B2B"));
//
//
//
//        count += 1;
//        if (count == 100){
//           //recorder.stop();
//       }
//        if (count < 100 && count >= 0){
//
//            animationScheduler.update(gc);
//            //recorder.record(gc.getCanvas());
//
//        }
//
////        try {
////
////            recorder.screenShot(gc.getCanvas());
////
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
//
//    }
//
//    public void startRecording(){
//        recorder.start();
//    }
//
//    public void stopRecording(){
//        recorder.stop();
//    }
//}

package com.engmig;

import com.engmig.FDGSolvers.FruchReinFDG;
import com.engmig.animations.EasingFunctions.EaseInOutElastic;
import com.engmig.animations.EasingFunctions.EaseOutCubic;
import com.engmig.animations.Scaler;
import com.engmig.animations.TranslateTo;
import com.engmig.graphs.AdjacencyList;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

import javax.vecmath.Vector3d;
import java.io.IOException;

public class GraphicsController {

    private static GraphicsController graphicsController;
    private static GraphicsContext gc;
    private static AnimationScheduler animationScheduler;
    private static FruchReinFDG FDG;
    private static boolean recording;
    private AdjacencyList graph;
    private AdjacencyList graph2;

    private AnimationTimer recordingTimer;
    private static final long RECORD_INTERVAL_NS = 1_000_000_000L / 30; // 30fps
    private long lastRecordTime = 0;

    private GraphicsRecorder recorder;
    private boolean isRecording = false;

    public GraphicsController(GraphicsContext graphicContext) {
        gc = graphicContext;
        recorder = new GraphicsRecorder();
        animationScheduler = new AnimationScheduler();
    }

    public GraphicsController() {
        recorder = new GraphicsRecorder();
        animationScheduler = new AnimationScheduler();
    }

    public static GraphicsController getInstance() {
        if (graphicsController != null) {
            graphicsController = new GraphicsController();
        }
        return graphicsController;
    }

    public static GraphicsController newGraphicsController(GraphicsContext graphicsContext) {
        graphicsController = new GraphicsController(graphicsContext);
        return graphicsController;
    }

    public static GraphicsContext getGc() {
        return gc;
    }

    int count = 0;
    int animationStart = 0;
    int VIDEO_LENGTH = 150; // in Frames

    public void update() throws IOException {
        if (count == 0) {
//            graph = new AdjacencyList();
//            graph.makeNVerticesOnCircle(40, 3840 / 2, 2160 / 2, 750);
//            graph.addRandomEdgesOdd(20);
//            graph.addRandomEdgesEven(20);
            Vector3d endPos1 = new Vector3d(1000,1000,0);
            Vertex v1 = new Vertex(100,100);
//            TranslateTo t = animationScheduler.translateTo(v1,endPos1,100,10);
//            t.setFunction(new EaseOutCubic());
            Scaler s = animationScheduler.scale(v1,10,100,10,100 );
            s.setFunction(new EaseInOutElastic());
            startRecording();
        }

        if (count < VIDEO_LENGTH + animationStart && count > animationStart && isRecording) {
            animationScheduler.update(gc);
            recorder.record(gc.getCanvas());
        }

        count += 1;

        if (count == VIDEO_LENGTH+animationStart && isRecording) {
            recorder.stop();
        }
    }

    public void startRecording() {
        if (isRecording) return; // prevent double-start
        isRecording = true;
        recorder.start();

    }

    public void stopRecording() {
        if (!isRecording) return;
        isRecording = false;
        if (recordingTimer != null) {
            recordingTimer.stop();
        }
        recorder.stop();
    }
}