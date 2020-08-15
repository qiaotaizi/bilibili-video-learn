package com.jaiz.study.lesson016;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * BorderPane
 */
@StartableMeta(title = "lesson016", category = CategoryType.LESSON,
        subtitle = "BorderPane")
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #AAAAAA");

        AppUtils.quickInit(primaryStage, "lesson016", borderPane);


        AnchorPane top = new AnchorPane();
        top.setStyle("-fx-background-color: #FFF68F;" +
                "-fx-pref-height: 20;" +
                "-fx-pref-width: 50");
        borderPane.setTop(top);

        AnchorPane left = new AnchorPane();
        left.setStyle("-fx-background-color: #6A5ACD;" +
                "-fx-pref-width: 20");
        borderPane.setLeft(left);

        AnchorPane bottom = new AnchorPane();
        bottom.setStyle("-fx-background-color: #4EEE94;" +
                "-fx-pref-height: 20");
        borderPane.setBottom(bottom);

        AnchorPane right = new AnchorPane();
        right.setStyle("-fx-background-color: #C6E2FF;" +
                "-fx-pref-width: 20");
        borderPane.setRight(right);

        AnchorPane center = new AnchorPane();
        center.setStyle("-fx-background-color: #CD6600");
        borderPane.setCenter(center);

        borderPane.setPadding(new Insets(10));

        //设置内部元素（上下左右中）外边距
        BorderPane.setMargin(center, new Insets(8));
        //设置内部元素（上下左右中）的对齐方式
        BorderPane.setAlignment(top, Pos.CENTER_RIGHT);
        primaryStage.show();
    }
}
