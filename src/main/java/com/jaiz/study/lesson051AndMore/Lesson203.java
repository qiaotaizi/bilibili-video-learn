package com.jaiz.study.lesson051AndMore;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StartableMeta(title = "lesson203",
        category = CategoryType.LESSON,
        subtitle = "?",
        digest = {"?"})
public class Lesson203 extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();

        AppUtils.quickInit(primaryStage, "lesson203", root);
        primaryStage.show();
    }
}
