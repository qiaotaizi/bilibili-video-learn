package com.jaiz.study.lesson045;

import com.jaiz.study.utils.AppDesc;
import com.jaiz.study.utils.AppUtils;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

@AppDesc({"Slider的使用"})
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        Rectangle rect=new Rectangle();

        ColorPicker cp=new ColorPicker();
        cp.setOnAction(event -> {
           Color c=cp.getValue();
           rect.setFill(c);
        });

        Slider s=new Slider(0,200,0);
        s.setShowTickLabels(true);//显示刻度值
        s.setShowTickMarks(true);//显示刻度
        s.setMajorTickUnit(20);//主要刻度单位
        s.setPrefWidth(300);
        s.setOrientation(Orientation.HORIZONTAL);//设置水平或垂直显示

        s.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double d=newValue.doubleValue();
                rect.setWidth(d);
                rect.setHeight(d);
            }
        });

        VBox vBox=new VBox();
        vBox.getChildren().addAll(s,cp,rect);

        root.getChildren().add(vBox);

        AppUtils.quickInitMenuBar(primaryStage,  this.getClass(), root);
    }

}
