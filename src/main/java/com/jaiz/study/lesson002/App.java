package com.jaiz.study.lesson002;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * javafx应用的生命周期：
 * init()：执行一些应用初始化操作
 * start()：应用执行中的所有逻辑在start中
 * stop()：应用被关闭后执行
 *
 * 声明周期中的线程：
 * main方法启动与主线程
 * init()方法在JavaFX-Launcher线程中执行
 * start()和stop()在JavaFX Application Thread线程中执行,这个线程就是UI线程
 */
@StartableMeta(title = "lesson002",category = CategoryType.LESSON,
subtitle = "生命周期",digest = {"init()","start()","stop()","JavaFX-Launcher线程","JavaFX Application Thread线程(UI线程)"})
public class App extends Startable {

    // @Override
    // public void init() throws Exception {
    //     super.init();
    //     System.out.println("init() = "+Thread.currentThread().getName());
    // }

    // @Override
    // public void stop() throws Exception {
    //     super.stop();
    //     System.out.println("stop() = "+Thread.currentThread().getName());

    // }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("start() = "+Thread.currentThread().getName());
        primaryStage.setTitle("lesson002");
        primaryStage.show();
    }
}
