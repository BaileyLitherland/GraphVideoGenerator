package com.engmig;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import org.jcodec.api.awt.AWTSequenceEncoder;
import org.jcodec.common.model.Rational;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.io.FileDescriptor.out;

public class GraphicsRecorder {
    boolean recording = true;
    int imageCount = 0;
    List<BufferedImage> images = new ArrayList<BufferedImage>();


    public void start(){

    }
    public void pause(){

    }
    public void stop(){
        imageCount = 0;
        recording = false;
        finishRecording();
    }
    public void record(Canvas canvas){

        // TODO: give each image a different ID.
        imageCount += 1;
        if (recording == true){
            // Do recording
            // Take Snapshot of screen
            WritableImage writableImage = canvas.snapshot(null,null);
            // Write Snapshot to file
            File outFile = new File("image.png");

            images.add(SwingFXUtils.fromFXImage(writableImage, null));

        }
    }

    public void finishRecording(){
        System.out.println("start Finishing up the video");
        File outputFile = new File("video.mp4");


        AWTSequenceEncoder encoder;
        try {
            encoder = AWTSequenceEncoder.create30Fps(outputFile);
            for (BufferedImage image: images) {
                encoder.encodeImage(image);
            }
            encoder.finish();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Video Finalised");
    }

    public void createMP4(){
        // This method will take the files saved in record and turn them into an MP4
        // Using JCodec to encode the pngs to MP4 aznd save as unique file.
        // Consider using an array of buffered images rather than save them
    }

}
