package com.jaiz.study.lesson050;

import com.jaiz.study.utils.AppDesc;
import com.jaiz.study.utils.AppUtils;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;
import java.text.MessageFormat;

@AppDesc({"组件宽高","坐标转换"})
public class App extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        VBox vb=new VBox();
        vb.setPrefWidth(300);
        vb.setPrefHeight(300);
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-background-color: beige");
        AnchorPane.setTopAnchor(vb,30.0);
        AnchorPane.setLeftAnchor(vb,30.0);


        Button btn=new Button("Click Me!");
        Rectangle rec=new Rectangle();
        rec.setHeight(40);
        rec.setWidth(40);
        vb.getChildren().addAll(btn,rec);
        System.out.println(String.format("btn.isResizable=%b",btn.isResizable()));
        System.out.println(String.format("rec.isResizable=%b",rec.isResizable()));
        //打印发现button的resizable=true；
        //rectangle的resizable=false；
        //父组件依据这个属性来判断是否主动为组件初始化默认尺寸。

        Button btn2=new Button("button2");
        vb.getChildren().add(btn2);
        //AnchorPane.setTopAnchor(btn2,20.0);
        //AnchorPane.setLeftAnchor(btn2,20.0);
        btn2.setEffect(new DropShadow());//添加阴影

        root.getChildren().add(vb);
        AppUtils.quickInitMenuBar(primaryStage,  this.getClass(), root);
        System.out.println(
                String.format("btn.width=%.2f,btn.prefWidth=%.2f"
                        ,btn.getWidth()
                        ,btn.getPrefWidth()));
        //打印发现，width被父容器设置了值，prefWidth则为-1(见Button.USE_COMPUTED_SIZE)
        //父容器可以为有prefWidth/prefHeight属性的组件初始化宽高
        //只有width/Height属性的组件必须手动设置宽高

        //组件边界计算
        Bounds bds=btn.getLayoutBounds();
        System.out.println(String.format(
                "btn本地坐标下，左上角坐标(%.2f,%.2f)，右下角坐标(%.2f,%.2f)",
                bds.getMinX(),
                bds.getMinY(),
                bds.getMaxX(),
                bds.getMaxY()));

        Bounds bdsParent=btn.localToParent(bds);
        System.out.println(String.format(
                "btn父容器坐标下，左上角坐标(%.2f,%.2f)，右下角坐标(%.2f,%.2f)",
                bdsParent.getMinX(),
                bdsParent.getMinY(),
                bdsParent.getMaxX(),
                bdsParent.getMaxY()));

        Bounds bdsScene=btn.localToScene(bds);
        System.out.println(String.format(
                "btn场景坐标下，左上角坐标(%.2f,%.2f)，右下角坐标(%.2f,%.2f)",
                bdsScene.getMinX(),
                bdsScene.getMinY(),
                bdsScene.getMaxX(),
                bdsScene.getMaxY()));

        Bounds bdsScreen=btn.localToScreen(bds);
        System.out.println(String.format(
                "btn屏幕坐标下，左上角坐标(%.2f,%.2f)，右下角坐标(%.2f,%.2f)",
                bdsScreen.getMinX(),
                bdsScreen.getMinY(),
                bdsScreen.getMaxX(),
                bdsScreen.getMaxY()));


        //layoutBounds的计算有些问题
        //如果是在窗口渲染之后添加的组件
        //组件的layoutBounds得到的结果并不是实际的边界位置，应用的时候需要注意。
        System.out.println(String.format("layoutBounds不计算效果: %s",btn2.getLayoutBounds()));
        System.out.println(String.format("layoutBounds不计算效果: %.2f, %.2f",btn2.getLayoutBounds().getMaxX(),btn2.getLayoutBounds().getMaxY()));
        System.out.println(String.format("boundsInLocal计算效果: %s",btn2.getBoundsInLocal()));
        System.out.println(String.format("boundsInParent计算效果: %s",btn2.getBoundsInParent()));

    }

}
