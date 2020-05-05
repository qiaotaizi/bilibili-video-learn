package com.jaiz.study.lesson047;

import com.jaiz.study.utils.AppDesc;
import com.jaiz.study.utils.AppUtils;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@AppDesc({"SplitPane",
        "注意splitPane不是Pane的子类",
        "而是Control的子类",
        "SplitPane的item如果是没有伸缩能力的Node时，SplitPane的分隔线将无法被拖动"})
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        SplitPane splitPane = new SplitPane();

        //控制分割方向
        splitPane.setOrientation(Orientation.HORIZONTAL);

        splitPane.setPrefWidth(300);
        splitPane.setPrefHeight(200);

        StackPane sp1=new StackPane();
        sp1.getChildren().add(new Button("btn1"));

        StackPane sp2=new StackPane();
        sp2.getChildren().add(new Button("btn2"));

        StackPane sp3=new StackPane();
        sp3.getChildren().add(new Button("btn3"));

        StackPane sp4=new StackPane();
        sp4.getChildren().add(new Button("btn4"));

        splitPane.getItems().addAll(sp1,sp2,sp3,sp4);

        root.getChildren().add(splitPane);


        AppUtils.quickInitMenuBar(primaryStage, this.getClass(), root);
        splitPane.setPrefSize(primaryStage.getWidth(),primaryStage.getHeight());
    }

}
