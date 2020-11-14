package com.jaiz.study.examples.css;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.collections.ObservableList;
import javafx.css.Stylesheet;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StartableMeta(title = "the ladder function",digest = {"ladder","颜色函数"},category = CategoryType.EXAMPLE)
public class ColorFunctionLadder extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root=new GridPane();

        AppUtils.quickInit(primaryStage,"ladder function",root);
        primaryStage.show();

        root.getStylesheets().addAll(
                "css/palette-calculated.css",
                "css/ladder.css",
                "css/palette-light.css");

        root.getStyleClass().add("root");

        Label l=new Label("ladder函数允许前景色根据背景色变化");
        l.getStyleClass().add("label");
        root.setAlignment(Pos.CENTER);

        root.add(l,0,0);

        l.setOnMouseClicked(event -> {
            switchPalette(root.getStylesheets());
        });
    }

    private boolean usingLight=true;

    /**
     * 切换调色板css文件
     * @param stylesheets
     */
    private void switchPalette(ObservableList<String> stylesheets) {
        if (usingLight){
            stylesheets.remove("css/palette-light.css");
            stylesheets.add("css/palette-dark.css");
        }else{
            stylesheets.remove("css/palette-dark.css");
            stylesheets.add("css/palette-light.css");
        }
        usingLight=!usingLight;
    }
}
