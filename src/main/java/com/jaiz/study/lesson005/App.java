package com.jaiz.study.lesson005;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Platform api
 *
 * runLater，在ui线程执行，是一个待执行任务的队列，查下文档描述。  TODO
 * setImplicitExit
 * exit
 *
 *
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //控制关闭窗口后是否终止该进程。默认为true，即关闭窗口后终止进程。
        //若设为false，需要通过调用Platform.exit()来关闭进程。
        //Platform.setImplicitExit(false);

        Platform.runLater(()->
                //JavaFX Application Thread
                System.out.println(
                        "run later ... = "+
                                Thread.currentThread().getName()));

        primaryStage.setTitle("lesson005");
        primaryStage.show();
        System.out.println("start() = "+Thread.currentThread().getName());


        showSystemProperties();

    }

    private void showSystemProperties() {

        System.out.println(Platform.isSupported(ConditionalFeature.SCENE3D));
        System.out.println(Platform.isSupported(ConditionalFeature.FXML));

    }
}
