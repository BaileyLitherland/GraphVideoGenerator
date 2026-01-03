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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.io.FileDescriptor.out;

public class GraphicsRecorder {
    boolean recording = true; // Make true when you decide to record again

    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));

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
    public void record(Canvas canvas) throws IOException {
        if (imageCount == 0) {
            File newDirectory = new File(TEMP_DIRECTORY, "000new_directory");
            System.out.println(TEMP_DIRECTORY);

            if (!newDirectory.exists()){
                newDirectory.mkdir();
            }

        }
        // TODO: give each image a different ID.
        imageCount += 1;
        if (recording == true){
            System.out.println(imageCount);
            // Do recording
            // Take Snapshot of screen
            WritableImage writableImage = canvas.snapshot(null,null);
            // Write Snapshot to file

            // Make a temp file
            Path tempPath = null;


            File outFile = new File( "/image" + imageCount + ".png");
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", outFile);
            } catch(IOException e){
                System.err.println("Error in saving snapshot: " + e.getMessage());
            }

            //images.add(SwingFXUtils.fromFXImage(writableImage, null));

        }
    }

    public void finishRecording(){
        // TODO: Change this to a FFMPEG Implimentation to hopefully render faster
        try {
            Process process = new ProcessBuilder("ffmpeg", "-f", "image2", "-i","image%d.png", "-pix_fmt", "yuv420p", "a.mp4").start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // ffmpeg -f image2 -i image%d.png -pix_fmt yuv420p a.mp4
//        System.out.println("start Finishing up the video");
//        File outputFile = new File("video.mp4");
//
//
//        AWTSequenceEncoder encoder;
//        try {
//            encoder = AWTSequenceEncoder.create30Fps(outputFile);
//            for (BufferedImage image: images) {
//                encoder.encodeImage(image);
//            }
//            encoder.finish();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("Video Finalised");

    }

    public void createMP4(){
        // This method will take the files saved in record and turn them into an MP4
        // Using JCodec to encode the pngs to MP4 aznd save as unique file.
        // Consider using an array of buffered images rather than save them
    }

}
