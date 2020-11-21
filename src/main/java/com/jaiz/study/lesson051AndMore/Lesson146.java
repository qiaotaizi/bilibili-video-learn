package com.jaiz.study.lesson051AndMore;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@StartableMeta(title = "lesson146",
        category = CategoryType.LESSON,
        subtitle = "Effect DropShadow",
        digest = {"阴影"})
public class Lesson146 extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);

        ScrollPane container=new ScrollPane();
        VBox vBox=new VBox();
        //当父组件完全透明时，阴影会直接应用到子组件上
        container.setStyle("-fx-background-color: #ffffffee");
        container.setPrefWidth(200);
        container.setPrefHeight(200);
        container.setMaxWidth(200);
        container.setMaxHeight(200);
        vBox.setAlignment(Pos.CENTER);
        DropShadow shadow=buildShadow();
        container.setEffect(shadow);
        container.setContent(vBox);

        //阴影颜色控制
        Label colorLabel = new Label("color:");
        ColorPicker colorPicker=new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        shadow.colorProperty().bind(colorPicker.valueProperty());
        //阴影大小控制
        //Min:   0.0
        //Max: 255.0
        //Default:  21.0
        Label sizeLabel=new Label("height and width:");
        Slider sizeControl=new Slider(0,255,0);
        sizeControl.valueProperty().bindBidirectional(shadow.widthProperty());
        shadow.heightProperty().bindBidirectional(shadow.widthProperty());
        //阴影偏移量控制
        Label offsetXLabel=new Label("offset x:");
        Slider offsetXControl=new Slider(-100,100,0);
        offsetXControl.valueProperty().bindBidirectional(shadow.offsetXProperty());
        Label offsetYLabel=new Label("offset y:");
        Slider offsetYControl=new Slider(-100,100,0);
        offsetYControl.valueProperty().bindBidirectional(shadow.offsetYProperty());
        //阴影模糊半径控制
        //Min:   0.0
        //Max: 127.0
        //Default:  10.0
        Label radiusLabel=new Label("radius:");
        Slider radiusControl=new Slider(0,127,0);
        radiusControl.valueProperty().bindBidirectional(shadow.radiusProperty());
        //阴影模糊类型控制
        Label blurTypeLabel=new Label("blur type:");
        ChoiceBox<BlurType> blurTypeControl=new ChoiceBox<>();
        blurTypeControl.getItems().addAll(Arrays.asList(BlurType.values()));
        blurTypeControl.getSelectionModel().selectFirst();
        shadow.blurTypeProperty().bind(blurTypeControl.valueProperty());
        //阴影延展控制
        //Min: 0.0
        //Max: 1.0
        //Default: 0.0
        Label spreadLabel=new Label("spread:");
        Slider spreadControl = new Slider(0,1,0);
        spreadControl.valueProperty().bindBidirectional(shadow.spreadProperty());

        vBox.getChildren().addAll(colorLabel,colorPicker,
                sizeLabel,sizeControl,
                offsetXLabel,offsetXControl,
                offsetYLabel,offsetYControl,
                radiusLabel,radiusControl,
                blurTypeLabel,blurTypeControl,
                spreadLabel,spreadControl);

        root.getChildren().add(container);

        AppUtils.quickInit(primaryStage, "lesson146", root);
        primaryStage.show();
    }

    private DropShadow buildShadow() {
        DropShadow effect=new DropShadow();
        //阴影大小
        effect.setWidth(100);
        effect.setHeight(100);
        //阴影偏移量
        effect.setOffsetX(20);
        effect.setOffsetY(20);
        //阴影模糊半径
        effect.setRadius(50);
        return effect;
    }
}
