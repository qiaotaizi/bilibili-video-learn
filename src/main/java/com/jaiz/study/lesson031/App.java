package com.jaiz.study.lesson031;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;

import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * RadioButton
 * CheckBox
 */
@StartableMeta(title = "lesson031", category = CategoryType.LESSON,
        subtitle = "选择框", digest = {"RadioButton", "CheckBox"})
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FlowPane root = new FlowPane();

        RadioButton rb1 = new RadioButton("rb1");
        RadioButton rb2 = new RadioButton("rb2");
        RadioButton rb3 = new RadioButton("rb3");

        ToggleGroup tg = new ToggleGroup();

        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
        rb3.setToggleGroup(tg);


        CheckBox cb1 = new CheckBox("cb1");
        CheckBox cb2 = new CheckBox("cb2");
        CheckBox cb3 = new CheckBox("cb3");

        //允许设置为不确定状态
        cb2.setAllowIndeterminate(true);
        //设置为不确定状态
        cb2.setIndeterminate(true);

        root.getChildren().addAll(rb1, rb2, rb3, cb1, cb2, cb3);

        AppUtils.quickInit(primaryStage, "lesson031", root);
        primaryStage.show();
    }
}
