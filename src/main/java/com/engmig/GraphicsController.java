package com.engmig;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javax.vecmath.Vector3d;
import java.io.IOException;

public class GraphicsController {

    private static GraphicsController graphicsController;
    private static GraphicsContext gc;
    private static AnimationScheduler animationScheduler;
    private static boolean recording;

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

            System.out.println("Create vectors in graphics controller");
            Vertex v1 = new Vertex(gc.getCanvas().getWidth()/2,gc.getCanvas().getHeight()/2);
            Vertex v2 = new Vertex(gc.getCanvas().getWidth()/2,gc.getCanvas().getHeight()/2);
            Vector3d startPos = new Vector3d(gc.getCanvas().getWidth()/2,gc.getCanvas().getHeight()/2, 0 );
            Vector3d endPosV1 = new Vector3d(gc.getCanvas().getWidth()/2 + 200,gc.getCanvas().getHeight()/2, 0 );
            Vector3d endPosV2 = new Vector3d(gc.getCanvas().getWidth()/2 -200,gc.getCanvas().getHeight()/2, 0 );
            animationScheduler.createLinearAnimation(v1, startPos, endPosV1, 40, 0);
            animationScheduler.createLinearAnimation(v2, startPos, endPosV2, 40, 0);

        }

        // In here update the canvas based on the graph

        gc.setFill(Color.web("#2B2B2B"));

        Vertex vertex = new Vertex(gc.getCanvas().getWidth()/2,gc.getCanvas().getHeight()/2);
        // Here is where we could also start to take the snapshots of the canvas
        // vertex.draw(gc, count);

        animationScheduler.update(gc);

        count += 1;
//        if (count == 90){
//           recorder.stop();
//       }
//        if (count < 90){
//            recorder.record(gc.getCanvas());
//        }

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
