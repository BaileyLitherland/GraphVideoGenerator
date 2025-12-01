package com.engmig;

import javafx.scene.canvas.GraphicsContext;

public class GraphicsHandler {

    private static GraphicsHandler ghInstance;
    private static GraphicsContext gc;

    public GraphicsHandler(GraphicsContext graphicContext){
         gc = graphicContext;
    }

    public GraphicsHandler(){

    }
    // Static Factory Method
    public static GraphicsHandler getInstance(){
        if (ghInstance != null){
            ghInstance = new GraphicsHandler();
        }
        return ghInstance;
    }

    public static GraphicsHandler newGraphicsHandler(GraphicsContext graphicsContext){
        ghInstance = new GraphicsHandler(graphicsContext);
        return ghInstance;
    }

    public static GraphicsContext getGc() {
        return gc;
    }

    public void update(Graph graph){
        // In here update the canvas based on the graph
    }
}
