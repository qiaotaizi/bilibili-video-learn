package com.jaiz.study.lesson001;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;

import javafx.stage.Stage;

@StartableMeta(
    category = CategoryType.LESSON,
    title = "lesson001",
    subtitle = "hello, javafx!",
    digest = "显示一个javafx窗口")
public class Lesson001 extends Startable{
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("lesson001");
        primaryStage.show();
    }
}