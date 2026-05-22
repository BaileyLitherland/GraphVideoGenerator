package com.engmig;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FFmpegLogCallback;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GraphicsRecorder {

    boolean recording = true;

    // FFmpeg recorder classes
    private FFmpegFrameRecorder recorder;
    private Java2DFrameConverter converter;

    int imageCount = 0;

    // Variables for timing things
    long startTime;
    long endTime;

    private ExecutorService encoder;

    public GraphicsRecorder() {

    }

    public void start() {

    }

    public void stop() {
        imageCount = 0;
        finishRecording();
    }

    public void record(Canvas canvas) throws IOException {

        if (imageCount == 0) {
            recorder = new FFmpegFrameRecorder("output.mp4", 3840, 2160);
            recorder.setFormat("mp4");
            recorder.setFrameRate(30);
            recorder.setVideoBitrate(20000000);
            recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
            recorder.setGopSize(15);
            recorder.setVideoOption("bf", "0");
            recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);

            FFmpegLogCallback.set();
            converter = new Java2DFrameConverter();

            if (recording) {
                System.out.println("start recording");
                startTime = System.currentTimeMillis();
                recorder.start();
            }

            // Create the encoder thread pool once, not every frame
            encoder = Executors.newSingleThreadExecutor();
        }

        if (!recording) return;

        // Snapshot stays on FX thread (required)
        WritableImage snap = canvas.snapshot(null, null);

        // Capture timestamp HERE on FX thread before submitting,
        // so frames are always monotonically increasing
        final long timestamp = (long) ((imageCount * 1_000_000L) / 30); // microseconds, based on frame number

        BufferedImage bimg = SwingFXUtils.fromFXImage(snap, null);
        BufferedImage bgr = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        bgr.getGraphics().drawImage(bimg, 0, 0, null);
        try {
            recorder.setTimestamp(timestamp);
            recorder.record(converter.getFrame(bgr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Everything else moves to background thread
//        encoder.submit(() -> {
//            BufferedImage bimg = SwingFXUtils.fromFXImage(snap, null);
//            BufferedImage bgr = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
//            bgr.getGraphics().drawImage(bimg, 0, 0, null);
//            try {
//                recorder.setTimestamp(timestamp);
//                recorder.record(converter.getFrame(bgr));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });

        imageCount++;
    }

    public void recordSound() {
        try {
            recorder.recordSamples();
        } catch (FFmpegFrameRecorder.Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void finishRecording() {
        endTime = System.currentTimeMillis();
        double recordingTime = (endTime - startTime) / 1000.00;

        System.out.println("video took " + recordingTime + " seconds to finish");
        System.out.println("now saving video");
        startTime = System.currentTimeMillis();

        try {
            recording = false;
            encoder.shutdown();
            try {
                encoder.awaitTermination(10, TimeUnit.SECONDS); // wait for last frames to flush
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            recorder.stop();
            recorder.release();

            endTime = System.currentTimeMillis();
            recordingTime = (endTime - startTime) / 1000.00;
            System.out.println("finish video");
            System.out.println("encoding video took " + recordingTime + " seconds to finish");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void screenShot(Canvas canvas) throws IOException {
        WritableImage canvasSnapshot = canvas.snapshot(null, null);
        BufferedImage bimg = SwingFXUtils.fromFXImage(canvasSnapshot, null);
        BufferedImage bimgEdited = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        bimgEdited.getGraphics().drawImage(bimg, 0, 0, null);
        File outputFile = new File("Image01.png");
        ImageIO.write(bimgEdited, "png", outputFile);
        System.out.println("Screen shot taken");
    }

    public void createMP4() {
        // This method will take the files saved in record and turn them into an MP4
        // Using JCodec to encode the pngs to MP4 and save as unique file.
        // Consider using an array of buffered images rather than save them
    }
}