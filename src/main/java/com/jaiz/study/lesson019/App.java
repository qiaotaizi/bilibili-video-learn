package com.jaiz.study.lesson019;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * StackPane
 *
 * 图层布局
 */
@StartableMeta(title = "lesson019",category = CategoryType.LESSON,
subtitle = "StackPane")
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        StackPane root=new StackPane();


        //后加入的控件会覆盖在之前的控件之上

        AppUtils.quickInit(primaryStage,"lesson109",root);
        primaryStage.show();
    }
}
