package com.jaiz.study.lesson051AndMore;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StartableMeta(title = "lesson153",
        category = CategoryType.LESSON,
        subtitle = "?",
        digest = {"?"})
public class Lesson153 extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();

        AppUtils.quickInit(primaryStage, "lesson153", root);
        primaryStage.show();
    }
}
