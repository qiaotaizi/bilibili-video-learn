package com.jaiz.study.lesson014;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * HBox：水平布局
 * VBox：垂直布局
 */
@StartableMeta(title = "lesson014", category = CategoryType.LESSON,
        subtitle = "HBox和VBox")
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane anchorPane = new AnchorPane();
        HBox hBox = new HBox();
        anchorPane.getChildren().add(hBox);
        Button b1 = new Button("b1");
        Button b2 = new Button("b2");
        Button b3 = new Button("b3");
        hBox.getChildren().addAll(b1, b2, b3);

        VBox vBox = new VBox();
        anchorPane.getChildren().add(vBox);
        Button b4 = new Button("b4");
        Button b5 = new Button("b5");
        Button b6 = new Button("b6");
        vBox.getChildren().addAll(b4, b5, b6);
        AnchorPane.setTopAnchor(vBox, 40.0);

        AppUtils.quickInit(primaryStage, "lesson014", anchorPane);
        primaryStage.show();
    }
}
