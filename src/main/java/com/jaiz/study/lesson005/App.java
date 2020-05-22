package com.jaiz.study.lesson005;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;

import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * Platform api
 *
 * runLater，在ui线程执行，是一个待执行任务的队列，查下文档描述。  TODO
 * setImplicitExit
 * exit
 *
 *
 */
@Slf4j
@StartableMeta(title = "lesson005",category = CategoryType.LESSON,
subtitle = "Platform api")
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        //控制关闭窗口后是否终止该进程。默认为true，即关闭窗口后终止进程。
        //若设为false，需要通过调用Platform.exit()来关闭进程。
        //Platform.setImplicitExit(false);

        Platform.runLater(()->
                //JavaFX Application Thread
                log.info(
                        "run later ... = "+
                                Thread.currentThread().getName()));

        primaryStage.setTitle("lesson005");
        primaryStage.show();
        log.info("start() = "+Thread.currentThread().getName());


        showSystemProperties();

    }

    private void showSystemProperties() {

        log.info("ConditionalFeature.SCENE3D supported = {}",Platform.isSupported(ConditionalFeature.SCENE3D));
        log.info("ConditionalFeature.FXML supported = {}",Platform.isSupported(ConditionalFeature.FXML));

    }
}
