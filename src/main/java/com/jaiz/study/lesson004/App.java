package com.jaiz.study.lesson004;

import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.CategoryType;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;

/**
 * 继续学习stage api
 *
 * 透明度 置顶
 *
 */
@Slf4j
@StartableMeta(title = "lesson004",category = CategoryType.LESSON,
subtitle = "多窗口",digest = {"透明度","置顶","多窗口","窗口风格"})
public class App extends Startable {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("lesson004");
        primaryStage.show();

        // 控制窗口透明度 0~1浮点数
        primaryStage.setOpacity(0.8);

        // 始终置顶
        primaryStage.setAlwaysOnTop(true);

        // 设置窗口左顶点初始坐标
        primaryStage.setX(50);
        primaryStage.setY(50);

        // 监听窗口变化
        primaryStage.xProperty().addListener((observable, oldValue, newValue) -> {
            log.info("新的X={}" , newValue.doubleValue());
        });
        primaryStage.yProperty().addListener((observable, oldValue, newValue) -> {
            log.info("新的Y={}" , newValue.doubleValue());
        });

        // 多窗口类型
        openMultiStage();

    }

    private void openMultiStage() {

        Stage s1 = new Stage();
        s1.setTitle("s1-DECORATED(关闭此窗口将关闭其他sn窗口)");
        s1.initStyle(StageStyle.DECORATED);// 默认类型
        //设置一下初始位置避免覆盖
        s1.setX(50);
        s1.setY(80);
        s1.show();

        Stage s2 = new Stage();
        s2.setTitle("s2-UNDECORATED");
        s2.initStyle(StageStyle.UNDECORATED);// 无标题栏，无边框
        showS2Sth(s2);
        s2.show();

        Stage s3 = new Stage();
        s3.setTitle("s3-TRANSPARENT");
        s3.initStyle(StageStyle.TRANSPARENT);// 完全透明的窗口
        s3.show();

        Stage s4 = new Stage();
        s4.setTitle("s4-UNIFIED");
        s4.initStyle(StageStyle.UNIFIED);// 标题栏与应用一体
        s4.show();

        Stage s5 = new Stage();
        s5.setTitle("s5-UTILITY");
        s5.initStyle(StageStyle.UTILITY);// 始终置顶，只有关闭按钮
        s5.show();

        // 应用退出，直接关闭全部窗口
        // Platform.exit();
        s1.setOnCloseRequest(event->{
            s2.close();
            s3.close();
            s4.close();
            s5.close();
        });

    }

    /**
     * s2窗口是UNDECORATED风格，在上面展示一些内容
     * @param s2
     */
    private void showS2Sth(Stage s2) {
        GridPane gp=new GridPane();
        gp.add(new Label("这是一个UNDECORATED风格的窗口"), 0, 0);
        Scene scene=new Scene(gp);
        s2.setScene(scene);
    }
}
