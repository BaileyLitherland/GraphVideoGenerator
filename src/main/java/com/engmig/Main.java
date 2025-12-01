package com.engmig;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    GraphicsHandler gh;
    @Override
    public void start(Stage primaryStage){

        ScreenBuilder screenBuilder = new ScreenBuilder();
        Region screenRoot = screenBuilder.build();
        Scene scene = new Scene(screenRoot);

        gh = GraphicsHandler.newGraphicsHandler(screenBuilder.getCanvas().getGraphicsContext2D());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startTimer(){
        Timer timer = new Timer();

        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                // This part runs on background thread

                Platform.runLater(() ->{
                    // This runs on Application Thread. Use to update UI

                });
            }
        };
    }

    @Override
    public void stop(){
        System.exit(0);
    }
}
