package com.jaiz.study.lesson015;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 控件的Managed、Visible、Opacity
 * Managed控制控件是否由父容器控制，
 * 当Managed设为false时，
 * 控件从父容器移除，容器中的控件布局可以随之发生变化。
 *
 * Visible控制控件是否可见，
 * 当Visible设为false时，控件消失，但不会影响当前布局。
 *
 * Opacity控制空键的透明度
 */
@StartableMeta(title = "lesson015",category = CategoryType.LESSON,
subtitle = "Managed、Visible、Opacity")
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root=new AnchorPane();
        VBox vbox=new VBox();
        root.getChildren().add(vbox);
        var list=vbox.getChildren();
        list.addAll(
            new Label("控件的Managed、Visible、Opacity"),
            new Label("Managed控制控件是否由父容器控制，"),
            new Label("当Managed设为false时，"),
            new Label("控件从父容器移除，容器中的控件布局可以随之发生变化。"),
            new Separator(),
            new Label("Visible控制控件是否可见，"),
            new Label("当Visible设为false时，控件消失，但不会影响当前布局。"),
            new Separator(),
            new Label("Opacity控制空键的透明度")
        );

        AppUtils.quickInit(primaryStage,"lesson015",root);
        primaryStage.show();
    }
}
