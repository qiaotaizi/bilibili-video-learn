package com.jaiz.study.lesson007;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * 所有的节点类对象（Node）都必须放在Scene上
 * 而不能直接放在stage上。
 * 作为Scene的根节点的Node，会占据整个scene
 * 一般使用布局类对象作为根节点。
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root=new Group();

        Button b=new Button("点我访问百度");
        root.getChildren().add(b);
        Scene scene=new Scene(root);
        //URL url=this.getClass().getResource("cursor.png");
        //String path=url.toExternalForm();
        //System.out.println(path);
        //Cursor c=Cursor.cursor(path);
        //scene.setCursor(c);
        //b.setCursor(c);
        primaryStage.setScene(scene);
        primaryStage.setTitle("lesson007");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        b.setOnMouseClicked(event -> {
            System.out.println("clicked");
            HostServices hostServices=getHostServices();
            hostServices.showDocument("https://www.baidu.com");
        });

        primaryStage.show();
    }
}
