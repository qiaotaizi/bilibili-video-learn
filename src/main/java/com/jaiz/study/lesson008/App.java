package com.jaiz.study.lesson008;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Group的应用
 * 将一些组件分组
 * 可以为它们设定某些共用的属性
 */
@StartableMeta(title = "lesson008", category = CategoryType.LESSON,
        subtitle = "Group"
        , digest = {"组件分组", "共用属性"})
public class App extends Startable {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Group g = new Group();

        Button b1 = new Button("b1");
        Button b2 = new Button("b2");
        Button b3 = new Button("b3");

        //LayoutXY是相对于父容器的位置
        b1.setLayoutX(20);
        b1.setLayoutY(20);
        b2.setLayoutX(40);
        b2.setLayoutY(40);
        b3.setLayoutX(60);
        b3.setLayoutY(60);

        //控制组件是否被父容器管理，默认为true
        b1.setManaged(true);


        g.getChildren().addAll(b1, b2, b3);

        //为组中元件设置统一的透明度
        g.setOpacity(0.5);

        Scene scene = new Scene(g);

        primaryStage.setTitle("lesson008");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
