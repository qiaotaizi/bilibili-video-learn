package com.jaiz.study.lesson033;

import com.jaiz.study.AppUtils;
import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 通过TextFormatter限制输入内容
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox root=new VBox();


        TextField textField=new TextField();
        TextArea textArea=new TextArea();
        root.getChildren().addAll(textField,textArea);


        textField.setTextFormatter(new TextFormatter<>(change -> {
            //当输入内容满足小写英文字母时，响应内容变化，否则什么都不做
            if (change.getText().matches("[a-z]*")){
                return change;
            }
            return null;
        }));

        textArea.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getText().matches("[0-9]*")){
                return change;
            }
            return null;
        }));

        AppUtils.quickInitMenuBar(primaryStage,"lesson033",root);
    }
}
