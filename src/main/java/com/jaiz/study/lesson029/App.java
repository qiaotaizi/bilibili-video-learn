package com.jaiz.study.lesson029;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;

import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * TitledPane
 * Accordion
 */
@StartableMeta(title = "lesson029",category = CategoryType.LESSON,
subtitle = "TitledPane",digest = {"TitledPane","Accordion"})
public class App extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root=new AnchorPane();


        TitledPane tp=new TitledPane("titled pane",new Button("btn"));


        //控制是否以动画方式展开Node
        tp.setAnimated(false);
        //控制是否可以折叠
        tp.setCollapsible(true);

        //tp.setGraphic();设置图片


        TitledPane tp2=new TitledPane("titled pane 2",new Button("btn2"));
        tp2.setAnimated(false);
        TitledPane tp3=new TitledPane("titled pane 3",new Button("btn3"));
        tp3.setAnimated(false);

        //将TitledPane设置成一个组合，同一时间只有一个TitledPane可以展开
        Accordion accordion=new Accordion();
        accordion.getPanes().addAll(tp,tp2,tp3);

        root.getChildren().add(accordion);
        AppUtils.quickInit(primaryStage,"lesson029",root);
        primaryStage.show();
    }
}
