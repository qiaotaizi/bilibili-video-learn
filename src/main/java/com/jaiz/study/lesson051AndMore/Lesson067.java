package com.jaiz.study.lesson051AndMore;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

@Slf4j
@StartableMeta(title = "lesson067",
        category = CategoryType.LESSON,
        subtitle = "Image",
        digest = {"Image", "ImageView"})
public class Lesson067 extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();
        File f = new File("/Users/jaiz/Downloads/每周壁纸精选第151期/62.jpg");
        ToggleGroup tg = new ToggleGroup();
        ImageView iv = new ImageView();
        HBox radioButtons = new HBox();


        //最简构造
        InputStream in1 = new FileInputStream(f);
        Image img1 = new Image(in1);
        RadioButton r1 = new RadioButton("最简构造");
        r1.setToggleGroup(tg);
        r1.setOnAction(event -> {
            iv.setImage(img1);
        });

        //宽高比构造
        InputStream in2 = new FileInputStream(f);
        Image img2 = new Image(in2, 500, 600, true, true);
        RadioButton r2 = new RadioButton("宽高比构造");
        r2.setToggleGroup(tg);
        r2.setOnAction(event -> {
            iv.setImage(img2);
        });

        //url构造,url也可以写网络地址
        Image img3 = new Image("file:/Users/jaiz/Downloads/每周壁纸精选第151期/63.jpg");
        RadioButton r3 = new RadioButton("url构造");
        r3.setToggleGroup(tg);
        r3.setOnAction(event -> {
            iv.setImage(img3);
        });

        //异步加载
        Image img4=new Image("http://himg.bdimg.com/sys/portrait/item/99b5e4ba94e8b0b7e7b297e7b2aee7899be88289e591b3db2d.jpg",500,500,true,true,true);
        RadioButton r4=new RadioButton("异步加载");
        r4.setToggleGroup(tg);
        r4.setOnAction(event -> {
            iv.setImage(img4);
        });
        img4.errorProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                log.error("异步加载异常了。。。");
            }
        });
        img4.exceptionProperty().addListener(new ChangeListener<Exception>() {
            @Override
            public void changed(ObservableValue<? extends Exception> observable, Exception oldValue, Exception newValue) {
                log.error(newValue.getMessage(),newValue);
            }
        });
        img4.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                log.info("图片异步加载进度：{}",newValue);
            }
        });


        Button print=new Button("打印图片宽高");

        print.setOnAction(event -> {
            Image i=iv.getImage();
            if (Objects.nonNull(i)){
                log.info("图片宽：{}，高：{}。",i.getWidth(),i.getHeight());
            }
        });

        radioButtons.getChildren().addAll(r1, r2,r3,r4,print);
        AnchorPane.setTopAnchor(iv, 20.0);
        root.getChildren().addAll(radioButtons, iv);

        AppUtils.quickInit(primaryStage, "lesson067", root);
        primaryStage.show();
    }
}
