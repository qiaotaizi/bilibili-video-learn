package com.jaiz.study.lesson021;

import com.jaiz.study.utils.AppUtils;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * TilePane
 * 瓷砖布局
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        TilePane root=new TilePane();

        Button b1=new Button("b1");
        Button b2=new Button("b2");
        Button b3=new Button("b3");
        Button b4=new Button("b4");
        Button b5=new Button("b5");
        Button b6=new Button("b6");
        Button b7=new Button("b7");
        Button b8=new Button("b8");
        ProgressIndicator pi=new ProgressIndicator(0.5);

        ProgressBar pb=new ProgressBar(0.5);

        b1.setOnAction(event -> {
            DoubleProperty dp=pb.progressProperty();
            dp.setValue(0);
            DoubleProperty dp1=pi.progressProperty();
            dp1.setValue(0);
            new Thread(()->{
                try {
                    while(dp.getValue()<1.0){
                        Thread.sleep(50);
                        dp.setValue(dp.getValue()+0.01);
                        dp1.setValue(dp1.getValue()+0.01);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("progress end");
            }).start();
        });

        b2.setOnAction(event ->{
            DoubleProperty dp=pb.progressProperty();
            System.out.println(dp.getValue());
        });

        //布局上每个控件占据的空间等于布局上占用空间最大的控件的尺寸
        TilePane.setMargin(b1,new Insets(10));

        root.getChildren().addAll(b1,b2,b3,b4,b5,b6,b7,b8,pi,pb);

        root.setStyle("-fx-background-color: antiquewhite");

        AppUtils.quickInit(primaryStage,"lesson021",root);
        primaryStage.show();
    }
}
