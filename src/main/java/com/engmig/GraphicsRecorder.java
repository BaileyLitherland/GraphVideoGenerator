package com.engmig;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class GraphicsRecorder {
    boolean recording = true;
    public void start(){

    }
    public void pause(){

    }
    public void stop(){
        recording = false;
        // Compile video
    }
    public void record(Canvas canvas){
        if (recording == true){
            // Do recording
            // Take Snapshot of screen
            WritableImage writableImage = canvas.snapshot(null,null);
            // Write Snapshot to file
            File outFile = new File("image.png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null),
                        "png", outFile);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

}
