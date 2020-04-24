package com.jaiz.study.lesson012;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        Group root=new Group();

        TextField tf=new TextField();
//        tf.setLayoutX();
//        tf.setLayoutY();
//        tf.setPrefHeight();
//        tf.setPrefWidth();
//        tf.setFont();
//        tf.setStyle();
        //鼠标悬停提示
        Tooltip tt=new Tooltip();
        tt.setText("请输入xxx");
        tf.setTooltip(tt);
        tf.setPromptText("不超过7字符");
        //是否获取焦点
        tf.setFocusTraversable(false);
        //监听输入事件
        tf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length()>7){
                tf.setText(oldValue);
            }
        });
        //监听文本选择
        tf.selectedTextProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("selected="+newValue);
        });




        //密码框
        PasswordField pf=new PasswordField();
        pf.setLayoutY(40);
        pf.setFocusTraversable(false);
        pf.setPromptText("请输入密码");

        //标签
        Label l=new Label("一个标签");
        l.setLayoutY(80);

        root.getChildren().addAll(tf,pf,l);

        Scene scene=new Scene(root);
        primaryStage.setTitle("lesson012");
        primaryStage.setHeight(400);
        primaryStage.setWidth(600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
