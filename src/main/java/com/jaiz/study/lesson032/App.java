package com.jaiz.study.lesson032;

import com.jaiz.study.AppUtils;
import javafx.application.Application;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FlowPane root=new FlowPane();

        AppUtils.quickInitMenuBar(primaryStage,"lesson032",root);
    }
}
