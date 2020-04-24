package com.jaiz.study.lesson015;

import com.jaiz.study.AppUtils;
import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
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
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root=new AnchorPane();

        AppUtils.quickInit(primaryStage,"lesson015",root);
        primaryStage.show();
    }
}
