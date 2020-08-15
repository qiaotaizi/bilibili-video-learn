package com.jaiz.study.lesson032;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;

import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * TextArea
 */
@StartableMeta(title = "lesson032", category = CategoryType.LESSON,
        subtitle = "TextArea")
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FlowPane root = new FlowPane();

        TextArea ta = new TextArea();

        //设置自动换行
        ta.setWrapText(true);

        //设置初始行列数
        ta.setPrefRowCount(10);
        ta.setPrefColumnCount(20);


        root.getChildren().add(ta);


        AppUtils.quickInit(primaryStage, "lesson032", root);
        primaryStage.show();
    }
}
