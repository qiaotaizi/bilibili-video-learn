package com.jaiz.study.lesson003;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Stage的一些主要api
 *
 * 标题、Icon
 * 最小化、最大化
 * 关闭、展示
 * 初始宽高、最大最小宽高
 * 窗口尺寸变化监听
 * 全屏显示
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //设置标题
        primaryStage.setTitle("lesson003");

        //设置Icon
        //注意new Image中的url是从classes目录作为根目录出发的绝对路径
        primaryStage.getIcons().add(new Image("/com/jaiz/study/lesson003/icon.png"));


        //设置最小化，默认false
        //primaryStage.setIconified(true);

        //设置最大化，默认false
        //primaryStage.setMaximized(true);

        //关闭窗口
        //primaryStage.close();

        //是否可以更改窗口大小，默认true
        //primaryStage.setResizable(false);

        //设置窗口宽高
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        //设置最大最小宽高
//        primaryStage.setMaxHeight(800);
//        primaryStage.setMaxWidth(1000);
//
//        primaryStage.setMinHeight(400);
//        primaryStage.setMinWidth(600);

        //监听属性变化，以宽高为例
        //可以以此做自适应布局
        primaryStage.heightProperty().addListener((observable,oldValue,newValue)->{
            System.out.println("新的高度="+newValue.intValue());
        });

        primaryStage.widthProperty().addListener((observable,oldValue,newValue)->{
            System.out.println("新的宽度="+newValue.intValue());
        });

        //设为全屏显示，默认false
        //需要在show方法之前调用，并且配和Scene使用
        //primaryStage.setFullScreen(true);
        //primaryStage.setScene(new Scene(new Group()));

        primaryStage.show();

    }
}
