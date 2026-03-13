package com.engmig;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FFmpegLogCallback;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.Mat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.bytedeco.opencv.global.opencv_core.CV_8UC3;
import static org.bytedeco.opencv.global.opencv_core.flip;

public class GraphicsRecorder {

    private volatile boolean recording = false;

    private FFmpegFrameRecorder recorder;
    private final OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat();

    private ExecutorService encoderExecutor;

    private long startTime;
    private long endTime;
    private int width;
    private int height;
    private double frameRate = 30.0;
    private String outputFile = "VertexSeperates2.mp4";

    public GraphicsRecorder() {
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public void setFrameRate(double frameRate) {
        this.frameRate = frameRate;
    }

    public void start(Canvas canvas) {
        if (recording) {return;}
        System.out.println("Im here");

        this.width = (int) canvas.getWidth();
        this.height = (int) canvas.getHeight();

        recorder = new FFmpegFrameRecorder(outputFile, width, height);
        recorder.setVideoCodecName("h264_nvenc");
        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
        recorder.setFrameRate(frameRate);
        recorder.setVideoOption("vsync", "cfr");
        recorder.setVideoOption("preset", "p5");
        recorder.setVideoOption("rc", "vbr");
        recorder.setVideoOption("cq", "19");
        recorder.setVideoBitrate(20_000_000);

        FFmpegLogCallback.set();

        encoderExecutor = Executors.newSingleThreadExecutor();

        try {
            recorder.start();
            System.out.println("SET RECORDING TO TRUE");
            recording = true;
            startTime = System.currentTimeMillis();
            System.out.println("Recording started");
        } catch (Exception e) {
            e.printStackTrace();
            recording = false;
        }
    }

    public void record(Canvas canvas) {
        System.out.println("Recording is: " + recording);
        if (!recording) return;

        // MUST be called on JavaFX thread, but we’ll be defensive:
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> record(canvas));
            return;
        }

        WritableImage snapshot = canvas.snapshot(null, null);

        encoderExecutor.submit(() -> processFrame(snapshot));
    }

    private void processFrame(WritableImage snapshot) {
        try {
            BufferedImage fxImg = SwingFXUtils.fromFXImage(snapshot, null);

            BufferedImage bgr = new BufferedImage(
                    fxImg.getWidth(),
                    fxImg.getHeight(),
                    BufferedImage.TYPE_3BYTE_BGR
            );

            Graphics2D g = bgr.createGraphics();
            g.drawImage(fxImg, 0, 0, null);
            g.dispose();

            Mat mat = bufferedImageToMat(bgr);



            Frame frame = converter.convert(mat);
            recorder.record(frame);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Mat fxToMat(WritableImage img) {
        int w = (int) img.getWidth();
        int h = (int) img.getHeight();

        Mat mat = new Mat(h, w, CV_8UC3);

        byte[] buffer = new byte[w * h * 3];

        img.getPixelReader().getPixels(
                0, 0, w, h,
                javafx.scene.image.PixelFormat.getByteBgraInstance(),
                buffer, 0, w * 4
        );

        // Convert BGRA → BGR in-place
        for (int i = 0, j = 0; i < buffer.length; i += 4, j += 3) {
            byte b = buffer[i];
            byte g = buffer[i + 1];
            byte r = buffer[i + 2];
            buffer[j]     = b;
            buffer[j + 1] = g;
            buffer[j + 2] = r;
        }

        mat.data().put(buffer);
        return mat;
    }


    private Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.data().put(data);
        return mat;
    }

    public synchronized void stop() {
        if (!recording) {
            System.out.println("Already stopped");
            return;
        }

        recording = false;

        try {
            if (encoderExecutor != null) {
                encoderExecutor.shutdown();
                if (!encoderExecutor.awaitTermination(3, TimeUnit.SECONDS)) {
                    encoderExecutor.shutdownNow();
                }
            }

            if (recorder != null) {
                System.out.println("Stopping recorder...");
                recorder.stop();
                recorder.release();
            } else {
                System.out.println("Recorder was null!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            encoderExecutor = null;
            recorder = null;
        }
    }

    public void screenShot(Canvas canvas) throws IOException {
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> {
                try {
                    screenShot(canvas);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return;
        }

        WritableImage snapshot = canvas.snapshot(null, null);
        BufferedImage fxImg = SwingFXUtils.fromFXImage(snapshot, null);

        BufferedImage bgr = new BufferedImage(
                fxImg.getWidth(),
                fxImg.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR
        );

        Graphics2D g = bgr.createGraphics();
        g.drawImage(fxImg, 0, 0, null);
        g.dispose();

        File outputFile = new File("Image01.png");
        ImageIO.write(bgr, "png", outputFile);
        System.out.println("Screenshot taken");
    }
}