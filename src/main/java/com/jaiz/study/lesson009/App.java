package com.jaiz.study.lesson009;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用java代码控制Button样式
 * 使用css代码控制Button样式
 * 添加Button点击监听
 */
@Slf4j
@StartableMeta(title = "lesson009", category = CategoryType.LESSON,
        subtitle = "样式"
        , digest = {"代码控制样式", "css控制样式"})
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button b1 = new Button("字体");

        b1.setLayoutX(100);
        b1.setLayoutY(100);
        b1.setPrefWidth(200);
        b1.setPrefHeight(200);

        //控制字体字号
        b1.setFont(Font.font("sans-serif", 40));
        //控制背景、形状
        BackgroundFill fill = new BackgroundFill(
                Paint.valueOf("#8F8B8C"),
                new CornerRadii(20),
                new Insets(10));
        Background bg = new Background(fill);
        b1.setBackground(bg);

        //设置边框
        BorderStroke bs = new BorderStroke(
                Paint.valueOf("#6F8B8C"),
                BorderStrokeStyle.DASHED,
                new CornerRadii(20),
                new BorderWidths(10));
        Border border = new Border(bs);
        b1.setBorder(border);

        //设置字体颜色
        b1.setTextFill(Paint.valueOf("#FF8B8C"));

        Button b2 = new Button("B2");
        b2.setLayoutX(0);
        b2.setLayoutY(0);
        b2.setPrefWidth(200);
        b2.setPrefHeight(200);

        //使用css文档控制样式-推荐
        b2.setStyle("-fx-background-color: #7CCD7C;-fx-background-radius: 20");


        b2.setOnAction(e -> {
            log.info("b2 clicked");
            //从事件获取事件源
            Button source = (Button) e.getSource();
            log.info(source.getText());
        });

        Group root = new Group();
        root.getChildren().addAll(b1, b2);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("lesson009");
        primaryStage.setHeight(500);
        primaryStage.setWidth(500);
        primaryStage.show();
    }
}
