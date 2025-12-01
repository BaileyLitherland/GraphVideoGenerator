package com.engmig;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){

        ScreenBuilder screenBuilder = new ScreenBuilder();
        Region screenRoot = screenBuilder.build();
        Scene scene = new Scene(screenRoot);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop(){
        System.exit(0);
    }
}
