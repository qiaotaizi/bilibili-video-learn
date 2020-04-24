package com.jaiz.study.lesson024;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.UUID;

/**
 * javaFX简单案例
 * 一个登录窗口
 *
 * 涉及到的一些知识点：
 * Node.UserData
 * Node.Properties
 */
public class App extends Application {

    public static void main(String[] args) {
        Application.launch(App.class,args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root=new GridPane();
        root.setStyle("-fx-background-color: antiquewhite");

        Label l_name=new Label("账号：");
        Label l_pwd=new Label("密码：");
        TextField t_name=new TextField();
        //设置鼠标悬停提示
        t_name.setTooltip(new Tooltip("请输入账号"));
        PasswordField p_pwd=new PasswordField();
        p_pwd.setTooltip(new Tooltip("请输入密码"));
        Button b_login=new Button("登录");
        Button b_clear=new Button("清除");

        b_login.setOnAction(event -> {
            System.out.println("用户名："+t_name.textProperty().get()+",密码："+p_pwd.textProperty().get());
            System.out.println("用户名标签上的用户数据："+l_name.getUserData());

            //动画效果简单示例
            FadeTransition ft=new FadeTransition();
            ft.setDuration(Duration.seconds(0.5));
            ft.setNode(root);
            ft.setFromValue(0);
            ft.setToValue(1);
            ft.play();

            //颜色
            l_name.setTextFill(Color.RED);
            l_pwd.setTextFill(Color.RED);

        });
        b_clear.setOnAction(event -> {
            t_name.textProperty().setValue("");
            p_pwd.textProperty().setValue("");
            //每个node都可以设置一个UserData，用于保存一个数据
            l_name.setUserData(UUID.randomUUID().toString());
            //更一般地，可以使用Node节点的Properties成员保存更多的数据
        });

        root.add(l_name,0,0);
        root.add(t_name,1,0);
        root.add(l_pwd,0,1);
        root.add(p_pwd,1,1);
        root.add(b_login,0,2);
        root.add(b_clear,1,2);

        GridPane.setMargin(b_clear,new Insets(0,0,0,120));

        root.setHgap(5);
        root.setVgap(5);

        //GridPane居于Scene的中央
        root.setAlignment(Pos.CENTER);

        Scene scene=new Scene(root);
        primaryStage.setTitle("lesson024");
        primaryStage.setHeight(300);
        primaryStage.setWidth(500);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
