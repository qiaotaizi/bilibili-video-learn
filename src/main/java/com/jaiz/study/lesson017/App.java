package com.jaiz.study.lesson017;

import com.jaiz.study.AppUtils;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * FlowPane
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FlowPane root=new FlowPane();

        Button b1=new Button("b1");
        Button b2=new Button("b2");
        Button b3=new Button("b3");
        Button b4=new Button("b4");
        Button b5=new Button("b5");
        Button b6=new Button("b6");
        Button b7=new Button("b7");
        Button b8=new Button("b8");
        Button b9=new Button("b9");
        Button b10=new Button("b10");

        root.getChildren().addAll(b1,b2,b3,b4,b5,b6,b7,b8,b9,b10);

        //设置容器内元素横纵间距
        root.setHgap(10);
        root.setVgap(10);

        //设置排列方式，默认横向
        root.setOrientation(Orientation.VERTICAL);

        AppUtils.quickInit(primaryStage,"lesson017",root);
        primaryStage.show();
    }
}
