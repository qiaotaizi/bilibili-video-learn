package com.jaiz.study.examples.css;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StartableMeta(title = "shadow",digest = {"区域阴影"},category = CategoryType.EXAMPLE)
public class Shadow extends Startable {


    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root=new AnchorPane();
        AppUtils.quickInit(primaryStage,"shadow",root);
        root.getStylesheets().add("css/shadow/style.css");
        root.getStyleClass().add("root");
        primaryStage.show();

        Label title=new Label("JavaFX 14");
        title.getStyleClass().addAll("title","max-width");

        Label p1=new Label("OpenJFX is an open source, next generation client application platform for desktop, mobile and embedded systems built on Java.");
        Label p2=new Label("It is a collaborative effort by many individuals and companies with the goal of producing a modern, efficient, and fully featured toolkit for developing rich client applications.");


        p1.getStyleClass().add("max-width");
        p2.getStyleClass().add("max-width");

        GridPane gp=new GridPane();
        gp.add(title,0,0);
        gp.add(p1,0,1);
        gp.add(p2,0,2);

        gp.setId("gp");
        gp.getStyleClass().addAll("bg-white","center-align");

        //Rectangle rectClip=new Rectangle(150,200);

        //gp.setClip(rectClip);

        AnchorPane.setTopAnchor(gp,30.0);
        AnchorPane.setLeftAnchor(gp,30.0);

        root.getChildren().add(gp);
    }
}
