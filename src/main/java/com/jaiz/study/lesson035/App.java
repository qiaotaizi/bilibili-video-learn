package com.jaiz.study.lesson035;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * ButtonBar
 */
@StartableMeta(title = "lesson035", category = CategoryType.LESSON,
        subtitle = "ButtonBar")
public class App extends Startable {

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        ButtonBar bar = new ButtonBar();

        Button b1 = new Button("APPLY");
        Button b2 = new Button("BACK_PREVIOUS");
        Button b3 = new Button("HELP");
        Button b4 = new Button("CANCEL_CLOSE");

        //button预设作用类型，会影响button在bar上的排序
        ButtonBar.setButtonData(b1, ButtonBar.ButtonData.APPLY);
        ButtonBar.setButtonData(b2, ButtonBar.ButtonData.BACK_PREVIOUS);
        ButtonBar.setButtonData(b3, ButtonBar.ButtonData.HELP);
        ButtonBar.setButtonData(b4, ButtonBar.ButtonData.CANCEL_CLOSE);

        //控制button排序风格
        bar.setButtonOrder(ButtonBar.BUTTON_ORDER_LINUX);

        //控制某个button在bar上按照统一的size来进行展示
        ButtonBar.setButtonUniformSize(b1, true);

        bar.getButtons().addAll(b1, b2, b3, b4);

        root.getChildren().add(bar);


        AppUtils.quickInit(primaryStage, "lesson035", root);
        primaryStage.show();
    }
}
