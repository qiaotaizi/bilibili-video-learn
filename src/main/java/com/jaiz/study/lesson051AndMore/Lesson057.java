package com.jaiz.study.lesson051AndMore;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StartableMeta(title = "lesson057",
        category = CategoryType.LESSON,
        subtitle = "绑定判断",
        digest = {"可观察值的比较运算"})
public class Lesson057 extends Startable {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane root=new GridPane();
        root.setAlignment(Pos.CENTER);
        Label l=new Label("测试代码见Lesson057.bindingPredicateTest");
        root.add(l,0,0);
        AppUtils.quickInit(primaryStage,"lesson057",root);
        primaryStage.show();
    }

    public static void bindingPredicateTest(){
        SimpleIntegerProperty a=new SimpleIntegerProperty(1);
        SimpleIntegerProperty b=new SimpleIntegerProperty(2);
        log.info("a > b : {}",a.greaterThan(b).get());
        log.info("a = b : {}",a.isEqualTo(b).get());
        log.info("a < b : {}",a.lessThan(b).get());
        log.info("a >= b : {}",a.greaterThanOrEqualTo(b).get());
        log.info("a <= b : {}",a.lessThanOrEqualTo(b).get());
        log.info("a <> b : {}",a.isNotEqualTo(b).get());
        log.info("比较大小时允许给一个误差值");
    }
}
