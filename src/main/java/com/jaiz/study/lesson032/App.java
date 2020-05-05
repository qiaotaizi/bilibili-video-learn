package com.jaiz.study.lesson032;

import com.jaiz.study.utils.AppUtils;
import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * TextArea
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FlowPane root=new FlowPane();

        TextArea ta=new TextArea();

        //设置自动换行
        ta.setWrapText(true);

        //设置初始行列数
        ta.setPrefRowCount(10);
        ta.setPrefColumnCount(20);



        root.getChildren().add(ta);


        AppUtils.quickInitMenuBar(primaryStage, this.getClass(),root);
    }
}
