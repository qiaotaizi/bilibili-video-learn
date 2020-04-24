package com.jaiz.study.lesson018;

import com.jaiz.study.AppUtils;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * GridPane
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane root=new GridPane();

        Button b1=new Button("b1");
        Button b2=new Button("b2");
        Button b3=new Button("b3");
        Button b4=new Button("b4");

        //设置单元格间距
        root.setHgap(10);
        root.setVgap(10);

        GridPane.setConstraints(b1,0,0);
        GridPane.setConstraints(b2,1,0);
        GridPane.setConstraints(b3,0,1);
        GridPane.setConstraints(b4,1,1);

        root.getChildren().addAll(b1,b2,b3,b4);

        AppUtils.quickInit(primaryStage,"lesson018",root);
        primaryStage.show();
    }
}
