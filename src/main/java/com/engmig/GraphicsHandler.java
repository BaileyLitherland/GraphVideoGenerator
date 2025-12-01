package com.engmig;

import javafx.scene.canvas.GraphicsContext;

public class GraphicsHandler {

    static GraphicsHandler ghInstance;
    static GraphicsContext gc;

    public GraphicsHandler(GraphicsContext graphicContext){
         gc = graphicContext;
    }

    public GraphicsHandler(){

    }
    // Static Factory Method
    public GraphicsHandler getInstance(){
        if (ghInstance != null){
            ghInstance = new GraphicsHandler();
        }
        return ghInstance;
    }

    public GraphicsHandler newGraphicsHandler(GraphicsContext graphicsContext){
        ghInstance = new GraphicsHandler(graphicsContext);
        return ghInstance;
    }

}
