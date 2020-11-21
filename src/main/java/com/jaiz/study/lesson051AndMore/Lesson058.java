package com.jaiz.study.lesson051AndMore;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StartableMeta(title = "lesson058",
        category = CategoryType.LESSON,
        subtitle = "续讲绑定运算api",
        digest = {""})
public class Lesson058 extends Startable {


    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root=new StackPane();
        Label label = new Label("这节课没啥好展示的");

        root.getChildren().add(label);
        StackPane.setAlignment(label, Pos.CENTER);
        AppUtils.quickInit(primaryStage,"lesson058", root);
        primaryStage.show();
    }
}
