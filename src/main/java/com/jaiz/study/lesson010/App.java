package com.jaiz.study.lesson010;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * button鼠标事件监听
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        Button b=new Button("click me");

        //单击事件
        b.setOnAction(e-> System.out.println("单击事件触发"));

        //点击事件（同单击事件）和双击事件
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("点击事件触发");
            if (event.getClickCount()==2){
                System.out.println("双击事件触发");
            }

            //左右中键都会触发点击事件，获取点击类型
            System.out.println(event.getButton().name());
            if (event.getButton().equals(MouseButton.PRIMARY)){
                System.out.println("触发了左键点击");
            }

        });

        //监听键盘事件
        b.setOnKeyPressed(e->{
            System.out.println(e.getCode()+"pressed");
        });
        b.setOnKeyTyped(e->{
            System.out.println(e.getCharacter()+"typed");
        });
        b.setOnKeyReleased(e->{
            System.out.println(e.getCode()+"released");
        });


        Group g=new Group();
        g.getChildren().add(b);

        Scene scene=new Scene(g);

        primaryStage.setTitle("lesson010");

        primaryStage.setHeight(200);
        primaryStage.setWidth(200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
