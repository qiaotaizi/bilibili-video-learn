package com.jaiz.study.lesson010;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * button鼠标事件监听
 */
@Slf4j
@StartableMeta(title = "lesson010", category = CategoryType.LESSON,
        subtitle = "事件监听"
        , digest = {"鼠标事件", "键盘事件"})
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {


        Button b = new Button("click me");

        //单击事件
        b.setOnAction(e -> log.info("单击事件触发"));

        //点击事件（同单击事件）和双击事件
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            log.info("点击事件触发");
            if (event.getClickCount() == 2) {
                log.info("双击事件触发");
            }

            //左右中键都会触发点击事件，获取点击类型
            log.info(event.getButton().name());
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                log.info("触发了左键点击");
            }

        });

        //监听键盘事件
        b.setOnKeyPressed(e -> {
            log.info(e.getCode() + "pressed");
        });
        b.setOnKeyTyped(e -> {
            log.info(e.getCharacter() + "typed");
        });
        b.setOnKeyReleased(e -> {
            log.info(e.getCode() + "released");
        });


        Group g = new Group();
        g.getChildren().add(b);

        Scene scene = new Scene(g);

        primaryStage.setTitle("lesson010");

        primaryStage.setHeight(200);
        primaryStage.setWidth(200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
