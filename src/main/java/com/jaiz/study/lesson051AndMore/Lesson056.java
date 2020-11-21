package com.jaiz.study.lesson051AndMore;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StartableMeta(title = "lesson056",
        category = CategoryType.LESSON,
        subtitle = "绑定计算",
        digest = {"可观察值的加减乘除等运算"})
public class Lesson056 extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root=new AnchorPane();

        Button button=new Button("button");

        //通过绑定计算，让button的宽度总是等于root宽度的一半

        button.prefWidthProperty().bind(root.widthProperty().divide(2));

        root.getChildren().add(button);

        AppUtils.quickInit(primaryStage,"lesson056",root);
        primaryStage.show();
    }
}
