package com.jaiz.study.lesson034;

import com.jaiz.study.utils.AppUtils;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * 简单实现文本框的关键字查询和文本排序
 * 文本排序没有实用意义
 * 这里只实现关键字查找
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        Button find_btn = new Button("查找");
        Button sort_btn = new Button("排序");
        TextField find_tf = new TextField();

        HBox find_hbox = new HBox();
        find_hbox.getChildren().addAll(find_btn, find_tf, sort_btn);

        TextArea find_ta = new TextArea();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(find_hbox, find_ta);

        root.getChildren().add(vBox);


        find_btn.setOnAction(event -> {
            //getParagraphs以换行符对文本域的内容进行分割
            String pattern = find_tf.getText();
            if (Objects.isNull(pattern) || "".equals(pattern)) {
                return;
            }
            String areaText = find_ta.getText();
            if (Objects.isNull(areaText) || "".equals(areaText)) {
                return;
            }
//            从光标位置开始查找模式串
            int cursorPos = find_ta.getSelection().getEnd();
            String sub = areaText.substring(cursorPos);
            int patternIndex = sub.indexOf(pattern);
            if (patternIndex == -1) {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("patter not found");
                alert.showAndWait();
                return;
            }

            int anchor=cursorPos+sub.indexOf(pattern);
            int caretPosition=pattern.length()+anchor;
            System.out.println(anchor+","+caretPosition);

            find_ta.selectRange(anchor,caretPosition);

            find_ta.requestFocus();
        });

        AppUtils.quickInitMenuBar(primaryStage, "lesson034", root);
    }
}
