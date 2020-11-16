package com.jaiz.study.examples.neon;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StartableMeta(title = "css流光",digest = {""},category = CategoryType.EXAMPLE)
public class NeonLight extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane root=new GridPane();

        root.getStylesheets().add("css/neon.css");

        root.setId("root");
        //root.setAlignment(Pos.CENTER);
        Label label=new Label("hello javaFX!");

        // TODO 鼠标移上 背景移动和模糊效果 javafx的css解释器暂不支持，可尝试使用代码实现
        HBox hBox=new HBox(label);
        hBox.setId("hBox");

        label.setId("label");
        root.add(hBox,0,0);

        AppUtils.quickInit(primaryStage,"流光效果",root);

        primaryStage.show();

    }
}
