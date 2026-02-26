package com.engmig;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FFmpegLogCallback;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
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

    boolean recording = false; // Make true when you decide to record again

    // FFmpeg recorder classes
    private FFmpegFrameRecorder recorder;
    private Java2DFrameConverter converter;

    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));

    int imageCount = 0;
    List<BufferedImage> images = new ArrayList<BufferedImage>();

    // Variables for timing things
    long startTime;
    long endTime;

    public GraphicsRecorder(){

    }

    public void start(){

    }

    public void stop(){
        imageCount = 0;
        //recording = false;
        finishRecording();
    }
    public void record(Canvas canvas) throws IOException {

        if (imageCount == 0) {
            //System.out.println("image count = 0 in graphicsRecorder");
            //Set up JavaCV frame recorder
            recorder = new FFmpegFrameRecorder("Output.mp4",1920,1080);
            // recorder.setVideoCodec(173);
            // recorder.setPixelFormat();
            recorder.setFormat("mp4");
            recorder.setVideoOption("crf", "0");

            FFmpegLogCallback.set();

            converter = new Java2DFrameConverter();
            if(recording  == true){
                System.out.println("start recording");
                startTime = System.currentTimeMillis();
                recorder.start();
            }
        }

        WritableImage canvasSnapshot = canvas.snapshot(null,null);

        BufferedImage bimg = SwingFXUtils.fromFXImage(canvasSnapshot, null);
        //images.add(bimg);
        // Convert to the right RBG format
         BufferedImage bimgEdited = new BufferedImage(bimg.getWidth(),bimg.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
         bimgEdited.getGraphics().drawImage(bimg, 0, 0, null);
        Frame frame = converter.getFrame(bimgEdited);

        recorder.record(frame);

        imageCount += 1;

    }

    public void finishRecording(){
        endTime = System.currentTimeMillis();
        double recordingTime = (endTime - startTime)/1000.00;

        System.out.println("video took " + recordingTime +"seconds to finish");
        System.out.println("now saving video");
        startTime = System.currentTimeMillis();

        try {
//            for (BufferedImage bimg: images){
//                BufferedImage bimgEdited = new BufferedImage(bimg.getWidth(),bimg.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
//                bimgEdited.getGraphics().drawImage(bimg, 0, 0, null);
//                Frame frame = converter.getFrame(bimgEdited);
//            }

            recorder.stop();
            recorder.release();
            endTime = System.currentTimeMillis();
            System.out.println("finish video");
            recordingTime = (endTime - startTime)/1000.00;
            System.out.println("encoding video took " + recordingTime +"seconds to finish");
            //Process process = new ProcessBuilder("ffmpeg", "-f", "image2", "-i","image%d.png", "-pix_fmt", "yuv420p", "a.mp4").start();
        } catch (IOException e) {
            //throw new RuntimeException(e);
        }

    }


    public void screenShot(Canvas canvas) throws IOException{
        WritableImage canvasSnapshot = canvas.snapshot(null,null);
        BufferedImage bimg = SwingFXUtils.fromFXImage(canvasSnapshot, null);
        BufferedImage bimgEdited = new BufferedImage(bimg.getWidth(),bimg.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
        bimgEdited.getGraphics().drawImage(bimg, 0, 0, null);
        File outputFile = new File("Image01.png");
        ImageIO.write(bimgEdited, "png", outputFile);
        System.out.println("Screen shot taken");
    }



    public void createMP4(){
        // This method will take the files saved in record and turn them into an MP4
        // Using JCodec to encode the pngs to MP4 aznd save as unique file.
        // Consider using an array of buffered images rather than save them
    }

}
