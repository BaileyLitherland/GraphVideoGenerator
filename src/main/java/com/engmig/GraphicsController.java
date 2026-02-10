package com.engmig;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.IOException;

public class GraphicsController {

    private static GraphicsController graphicsController;
    private static GraphicsContext gc;
    private static boolean recording;

    private GraphicsRecorder recorder;

    public GraphicsController(GraphicsContext graphicContext){
         gc = graphicContext;
         recorder = new GraphicsRecorder();
    }

    public GraphicsController(){
        recorder = new GraphicsRecorder();
    }
    // Static Factory Method
    public static GraphicsController getInstance(){
        if (graphicsController != null){
            graphicsController = new GraphicsController();
        }
        return graphicsController;
    }

    public static GraphicsController newGraphicsHandler(GraphicsContext graphicsContext){
        graphicsController = new GraphicsController(graphicsContext);
        return graphicsController;
    }

    public static GraphicsContext getGc() {
        return gc;
    }
    int count = 0;
    public void update(){
        // In here update the canvas based on the graph
        gc.setFill(Color.web("#43434cff"));
        gc.fillRect(0,0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        gc.setFill(Color.web("#43434cff"));
        Vertex vertex = new Vertex(3840/2,2160/2);
        // Here is where we could also start to take the snapshots of the canvas
        gc.setFill(Color.DARKKHAKI);
        vertex.draw(gc);
//        try {
//            if (count < 1000){
//                recorder.record(gc.getCanvas());
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        count += 1;
//        if (count == 1000){
//            recorder.stop();
//        }
        try {
            if (count == 1) {
                recorder.screenShot(gc.getCanvas());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void startRecording(){
        recorder.start();
    }

    public void stopRecording(){
        recorder.stop();
    }
}
