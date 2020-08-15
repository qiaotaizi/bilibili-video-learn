package com.jaiz.study.lesson013;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * AnchorPane
 * 可根据边界进行控件的布局
 */
@StartableMeta(title = "lesson013", category = CategoryType.LESSON,
        subtitle = "AnchorPane")
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-color: #FF3E6D");
        Button b1 = new Button("b1");
        Button b2 = new Button("b2");
        root.getChildren().addAll(b1, b2);
        //使用锚点进行定位
        AnchorPane.setTopAnchor(b1, 10.0);
        AnchorPane.setLeftAnchor(b1, 10.0);
        AnchorPane.setTopAnchor(b2, 10.0);
        AnchorPane.setLeftAnchor(b2, 70.0);


        Scene scene = new Scene(root);
        primaryStage.setTitle("lesson013");
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
