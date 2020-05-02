package com.jaiz.study.lesson019;

import com.jaiz.study.utils.AppUtils;
import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * StackPane
 *
 * 图层布局
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        StackPane root=new StackPane();


        //后加入的控件会覆盖在之前的控件之上

        AppUtils.quickInit(primaryStage,"lesson109",root);
        primaryStage.show();
    }
}
