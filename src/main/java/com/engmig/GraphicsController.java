package com.engmig;

import com.engmig.animations.Scalers.EaseInOutQuinScaler;
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
            recorder.start(gc.getCanvas());
            System.out.println("Create vectors in graphics controller");
            Vertex v1 = new Vertex(gc.getCanvas().getWidth()/2,gc.getCanvas().getHeight()/2);
            Vertex v2 = new Vertex(gc.getCanvas().getWidth()/2,gc.getCanvas().getHeight()/2);

            Vector3d startPos1 = new Vector3d(gc.getCanvas().getWidth()/2,gc.getCanvas().getHeight()/2, 0 );
            Vector3d startPos2 = new Vector3d(gc.getCanvas().getWidth()/2,gc.getCanvas().getHeight()/2, 0 );

            Vector3d endPosV1 = new Vector3d(gc.getCanvas().getWidth()/2 + 500,gc.getCanvas().getHeight()/2, 0 );
            Vector3d endPosV2 = new Vector3d(gc.getCanvas().getWidth()/2 -500,gc.getCanvas().getHeight()/2+500, 0 );

            animationScheduler.addAnimation(new EaseInOutQuinScaler(v1,1,20,0,200));
            animationScheduler.addAnimation(new EaseInOutQuinScaler(v2,1,20,0,200));

            animationScheduler.createEaseOutAnimation(v1, startPos1, endPosV1, 60, 30);
            animationScheduler.createEaseOutAnimation(v2, startPos2, endPosV2, 60, 30);


        }

        // In here update the canvas based on the graph

        gc.setFill(Color.web("#2B2B2B"));

//        Vertex vertex = new Vertex(gc.getCanvas().getWidth()/2,gc.getCanvas().getHeight()/2);
        // Here is where we could also start to take the snapshots of the canvas
        // vertex.draw(gc, count);

        animationScheduler.update(gc);

        count += 1;
        System.out.println(count);
        if (count == 90){
           recorder.stop();
       }
        if (count < 90 && count >= 0){
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
        recorder.start(gc.getCanvas());
    }

    public void stopRecording(){
        recorder.stop();
    }
}
