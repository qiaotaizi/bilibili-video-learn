package com.jaiz.study.examples.css;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.net.URL;

@Slf4j
@StartableMeta(title = "CSS",digest = {"css文件引用","id、class的添加"},category = CategoryType.EXAMPLE)
public class CssExample extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();
        AppUtils.quickInit(primaryStage,"css",root);
        primaryStage.show();

        Scene scene=primaryStage.getScene();

        //引用当前目录的css
        URL url=CssExample.class.getClassLoader().getResource("com/jaiz/study/examples/css/test.css");
        scene.getStylesheets().add(url.toExternalForm());
        //引用resource目录下的css
        scene.getStylesheets().add("css/my.css");

        //动态阴影：鼠标移入rectangle时添加，移出时删除
        EventHandler<InputEvent> onMouseEntersARect= event -> {
            Rectangle r= (Rectangle) event.getSource();
            r.getStyleClass().add("drop-shadow");
        };
        EventHandler<InputEvent> onMouseExitsARect = event -> {
            Rectangle r= (Rectangle) event.getSource();
            r.getStyleClass().remove("drop-shadow");
        };

        Rectangle myRect=new Rectangle(100,100);
        myRect.setId("my-rect");
        AnchorPane.setTopAnchor(myRect,50.0);
        AnchorPane.setLeftAnchor(myRect,50.0);
        myRect.getStyleClass().add("my-rect");
        myRect.setOnMouseEntered(onMouseEntersARect);
        myRect.setOnMouseExited(onMouseExitsARect);

        Rectangle yourRect=new Rectangle(100,100);
        AnchorPane.setTopAnchor(yourRect,50.0);
        AnchorPane.setLeftAnchor(yourRect,250.0);
        yourRect.setId("your-rect");
        yourRect.setOnMouseEntered(onMouseEntersARect);
        yourRect.setOnMouseExited(onMouseExitsARect);

        Rectangle linearGradientRect=new Rectangle(100,100);
        linearGradientRect.setId("linear-gradient-rect");
        AnchorPane.setTopAnchor(linearGradientRect,50.0);
        AnchorPane.setLeftAnchor(linearGradientRect,450.0);
        linearGradientRect.setId("linear-gradient-rect");
        linearGradientRect.setOnMouseEntered(onMouseEntersARect);
        linearGradientRect.setOnMouseExited(onMouseExitsARect);

        String exampleText="JavaFX CSS supports the ability to specify fonts using separate family, size, style, and weight properties, as well as the ability to specify a font using a single shorthand property.";

        Label labelNormal=new Label(exampleText);
        labelNormal.getStyleClass().add("label-normal");
        AnchorPane.setTopAnchor(labelNormal,250.0);
        AnchorPane.setLeftAnchor(labelNormal,10.0);

        Label labelItalic=new Label(exampleText);
        labelItalic.getStyleClass().add("label-italic");
        AnchorPane.setTopAnchor(labelItalic,280.0);
        AnchorPane.setLeftAnchor(labelItalic,10.0);

        Label labelOblique=new Label(exampleText);
        labelOblique.getStyleClass().add("label-oblique");
        AnchorPane.setTopAnchor(labelOblique,310.0);
        AnchorPane.setLeftAnchor(labelOblique,10.0);



        root.getChildren().addAll(myRect,yourRect,linearGradientRect,labelNormal,labelItalic,labelOblique);
    }
}
