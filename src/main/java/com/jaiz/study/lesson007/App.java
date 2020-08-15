package com.jaiz.study.lesson007;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Index;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * 所有的节点类对象（Node）都必须放在Scene上
 * 而不能直接放在stage上。
 * 作为Scene的根节点的Node，会占据整个scene
 * 一般使用布局类对象作为根节点。
 */
@Slf4j
@StartableMeta(title = "lesson007", category = CategoryType.LESSON,
        subtitle = "javafx层级关系"
        , digest = {"更改鼠标图片", "hostServices"})
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();

        Button b = new Button("点我访问百度");
        root.getChildren().add(b);
        Scene scene = new Scene(root);
        //URL url=this.getClass().getResource("cursor.png");
        //String path=url.toExternalForm();
        //log.info(path);
        //Cursor c=Cursor.cursor(path);
        //scene.setCursor(c);
        //b.setCursor(c);
        primaryStage.setScene(scene);
        primaryStage.setTitle("lesson007");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        b.setOnMouseClicked(event -> {
            log.info("clicked");
            Index.GLOBAL_HS.showDocument("https://www.baidu.com");
        });

        primaryStage.show();
    }
}
