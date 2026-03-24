package com.engmig;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    private Timer timer;
    GraphicsController graphicsController;

    @Override
    public void start(Stage primaryStage){

        ScreenBuilder screenBuilder = new ScreenBuilder();
        Region screenRoot = screenBuilder.build();
        Scene scene = new Scene(screenRoot);

        System.out.println("We have built the scene");

        graphicsController = GraphicsController.newGraphicsController(screenBuilder.getCanvas().getGraphicsContext2D());

        System.out.println("graphics Controller Initalised");

        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("We get to the show part");
        startTimer();
    }

    private void startTimer(){
        timer = new Timer();

        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                // This part runs on background thread

                Platform.runLater(() ->{
                    // This runs on Application Thread. Use to update UI
                    try {
                        graphicsController.update();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });
            }
        };
        timer.scheduleAtFixedRate(task, 0 ,33);
    }

    @Override
    public void stop(){
        if (timer != null){
            timer.cancel();
        }
        System.exit(0);
    }
}
