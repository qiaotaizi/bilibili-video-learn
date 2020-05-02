package com.jaiz.study.lesson020;

import com.jaiz.study.utils.AppUtils;
import javafx.application.Application;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * TextFlow
 * 文本流布局
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        TextFlow root=new TextFlow();

        Text t1=new Text("hello world~");
        t1.setFont(Font.font(20));
        Text t2=new Text("hello world!");
        t2.setFont(Font.font(15));
        t2.setUnderline(true);
        Text t3=new Text("hello world?");
        t3.setBoundsType(TextBoundsType.VISUAL);
        t3.setFill(Paint.valueOf("#FF82AB"));

        root.getChildren().addAll(t1,t2,t3);

        AppUtils.quickInit(primaryStage,"lesson020",root);
        primaryStage.show();
    }
}
