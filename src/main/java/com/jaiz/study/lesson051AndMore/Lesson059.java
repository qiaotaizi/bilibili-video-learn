package com.jaiz.study.lesson051AndMore;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.beans.binding.StringBinding;
import javafx.beans.binding.When;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StartableMeta(title = "lesson059",
        category = CategoryType.LESSON,
        subtitle = "when api",
        digest = {""})
public class Lesson059 extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        StackPane root=new StackPane();
        Label l=new Label("when的使用，见Lesson059.");
        root.getChildren().add(l);

        StackPane.setAlignment(l, Pos.CENTER);

        AppUtils.quickInit(primaryStage,"lesson059",root);
        primaryStage.show();
    }

    public static void whenTest(){
        SimpleIntegerProperty x=new SimpleIntegerProperty(1);
        SimpleIntegerProperty y=new SimpleIntegerProperty(2);

        When when=new When(x.greaterThan(y));
        var sb=when.then("x").otherwise("y");
        log.info(sb.get());
    }
}
