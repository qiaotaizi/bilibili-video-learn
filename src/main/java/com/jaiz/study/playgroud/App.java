package com.jaiz.study.playgroud;

import com.jaiz.study.AppUtils;
import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root=new AnchorPane();
        root.getChildren().add(new HTMLEditor());

        AppUtils.quickInit(primaryStage,"playgroud",root);
        primaryStage.show();
    }
}
