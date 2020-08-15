package com.jaiz.study.lesson004;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;

import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 模态化窗口
 */
@StartableMeta(title = "lesson004", category = CategoryType.LESSON,
        subtitle = "模态化窗口", digest = {"模态化窗口"})
public class App2 extends Startable {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("窗口模态测试");
        primaryStage.show();
        //默认的窗口模态是NONE

        //application模态窗口展示时，会阻塞本应用其他窗口的任何控制
        Stage applicationModalityStage = new Stage();
        applicationModalityStage.setTitle("applicationModalityStage");
        applicationModalityStage.initModality(Modality.APPLICATION_MODAL);
        applicationModalityStage.show();

        //window模态窗口展示时，会阻塞其owner窗口的任何控制，但不会阻塞本应用其他窗口的控制
        Stage windowModalityStage = new Stage();
        windowModalityStage.setTitle("windowModalityStage");
        windowModalityStage.initOwner(primaryStage);
        windowModalityStage.initModality(Modality.WINDOW_MODAL);
        windowModalityStage.show();

    }
}
