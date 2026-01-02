package com.engmig;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Builder;



public class ScreenBuilder implements Builder<Region>{

    Canvas canvas;
    @Override
    public Region build(){

        BorderPane results = new BorderPane();
        Node canvas = createCanvas();
        results.setCenter(canvas);

        return results;
    }


    private Node createCanvas(){
        Canvas canvas = new Canvas();
        canvas.setWidth(2560);
        canvas.setHeight(1440);
//        canvas.setWidth(1280);
//        canvas.setHeight(720);

        canvas.getGraphicsContext2D().setFill(Color.web("#43434cff"));
        canvas.getGraphicsContext2D().fillRect(0,0, canvas.getWidth(), canvas.getHeight());
        canvas.getGraphicsContext2D().setFill(Color.web("#cede85ff"));
        this.canvas = canvas;
        return canvas;
    }

    private Node createHeadingBox(){
        HBox results = new HBox();
        Text headingText = new Text("This is the title screen");
        results.getChildren().add(headingText);
        return results;
    }

    public Canvas getCanvas() {
        return canvas;
    }
}