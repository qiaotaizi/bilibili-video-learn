package com.jaiz.study.examples.css;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;

@Slf4j
@StartableMeta(title = "image flow",digest = {"罗列一些图片"},category = CategoryType.EXAMPLE)
public class ImagesFlow extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {

        ScrollPane scrollPane=new ScrollPane();
        FlowPane root=new FlowPane();
        scrollPane.setContent(root);

        AppUtils.quickInit(primaryStage,"image flow",scrollPane);
        primaryStage.show();


        root.getStylesheets().add("css/imgFlow/style.css");
        root.getStyleClass().add("root");


        fillImgs(root);
    }

    /**
     * 填充元素
     * @param root
     */
    private void fillImgs(FlowPane root) throws URISyntaxException {
        for (int i=0;i<100;i++) {
            VBox g= buildVBox(i);
            root.getChildren().add(g);
        }
    }

    /**
     * 创建group
     * @return
     */
    private VBox buildVBox(int i) {
        Rectangle rect = new Rectangle(250,200);
        Color color=randomColor();
        rect.setFill(color);
        Label label=new Label(color.toString());
        label.getStyleClass().add("color-name-label");
        VBox vBox=new VBox();
        vBox.getChildren().addAll(rect,label);
        vBox.getStyleClass().add("ele-v-box");

        Region r=new Region();

        return vBox;
    }

    /**
     * 生成随机颜色
     * @return
     */
    private Color randomColor() {
        int bound=255;
        return Color.rgb(r.nextInt(bound),r.nextInt(bound),r.nextInt(bound));
    }

    private Random r=new Random();

}
