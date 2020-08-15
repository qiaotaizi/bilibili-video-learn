package com.jaiz.study.lesson011;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

/**
 * 添加快捷键监听
 */
@Slf4j
@StartableMeta(title = "lesson011", category = CategoryType.LESSON,
        subtitle = "快捷键监听"
        , digest = {"KeyCombination", "Mnemonic", "Accelerators"})
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        Button b = new Button("click me");
        //点击事件监听
        b.setOnAction(e -> log.info("button on action"));

        //在scene上添加cmd+s快捷键监听
        //定义按键组合
        KeyCombination kc_cmd_s = new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN);
        //定义快捷键助记符，首参视为事件接收对象，在这里当触发cmd+s时，将触发b的onAction事件
        Mnemonic mnemonic_cmd_s = new Mnemonic(b, kc_cmd_s);
        //在场景中绑定快捷键助记符
        scene.addMnemonic(mnemonic_cmd_s);

        //使用另一种方式添加cmd+d快捷键监听
        //这种方式不需要触发另一控件的Action事件
        //或者如果希望触发另一控件的时间，可以直接调用.fire()
        //定义按键组合
        KeyCombination kc_cmd_d = new KeyCodeCombination(KeyCode.D, KeyCombination.SHORTCUT_DOWN);
        scene.getAccelerators().put(kc_cmd_d, () -> log.info("快捷键cmd+d被触发"));

        root.getChildren().add(b);

        primaryStage.setTitle("lesson011");
        primaryStage.setScene(scene);
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.show();
    }
}
