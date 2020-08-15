package com.jaiz.study.lesson030;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;

import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * TitledPane
 * Accordion
 */
@StartableMeta(title = "lesson030", category = CategoryType.LESSON,
        subtitle = "TabPane", digest = {"TitledPane", "Accordion"})
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();


        TabPane tp = new TabPane();

        tp.setPrefWidth(200);

        Tab tab1 = new Tab("tab1tab1tab1tab1");
        Tab tab2 = new Tab("tab2tab1tab1tab1");
        Tab tab3 = new Tab("tab3tab1tab1tab1");

        FlowPane tab1Content = new FlowPane();
        tab1Content.getChildren().addAll(new Button("btn1"), new Label("lbl1"));

        FlowPane tab2Content = new FlowPane();
        tab2Content.getChildren().addAll(new Button("btn2"), new Label("lbl2"));

        FlowPane tab3Content = new FlowPane();
        tab3Content.getChildren().addAll(new Button("btn3"), new Label("lbl3"));

        tab1.setContent(tab1Content);
        tab2.setContent(tab2Content);
        tab3.setContent(tab3Content);

        tp.getTabs().addAll(tab1, tab2, tab3);

        //控制选择状态
        //tp.setSelectionModel();

        //设置tab方向
        tp.setSide(Side.LEFT);

        root.getChildren().add(tp);

        AppUtils.quickInit(primaryStage, "lesson030", root);
        primaryStage.show();
    }
}
