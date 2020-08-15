package com.jaiz.study.lesson022;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;

/**
 * DialogPane
 * ScheduledService
 */
@Slf4j
@StartableMeta(title = "lesson022", category = CategoryType.LESSON,
        subtitle = "DialogPane和ScheduledService")
public class App extends Startable {

    private ScheduledService<Integer> ss;

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();
        Button b = new Button("点击显示对话框");
        root.getChildren().add(b);
        b.setOnAction(event -> {
            DialogPane dialogPane = new DialogPane();
            dialogPane.setHeaderText("header");
            dialogPane.setContentText("content");
            dialogPane.getButtonTypes().addAll(ButtonType.CLOSE, ButtonType.OK);
            ImageView iv = new ImageView("/com/jaiz/study/lesson022/icon.png");
            iv.setFitHeight(30);
            iv.setFitWidth(30);
            iv.setLayoutX(50);
            dialogPane.setGraphic(iv);
            dialogPane.setExpandableContent(new Label("扩展内容"));
            dialogPane.setExpanded(true);// 控制扩展内容的默认展示
            Button close = (Button) dialogPane.lookupButton(ButtonType.CLOSE);
            close.setOnAction(event1 -> {
                log.info("close");
            });
            Button ok = (Button) dialogPane.lookupButton(ButtonType.OK);
            ok.setOnAction(event1 -> {
                log.info("ok");
            });

            Stage stage = new Stage();
            stage.setTitle("对话框");
            stage.setScene(new Scene(dialogPane));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);
            stage.initStyle(StageStyle.UTILITY);
            // 控制后台任务的关闭
            stage.setOnCloseRequest(event1 -> {
                cancelSchedule();
            });
            stage.show();

            // 运行后台任务
            ss = new ScheduledService<Integer>() {
                @Override
                protected Task<Integer> createTask() {
                    return new Task<Integer>() {

                        @Override
                        protected Integer call() throws Exception {
                            // 该方法运行于其他线程
                            log.info(
                                    "ScheduledService.Task.call Thread Name=" + Thread.currentThread().getName());
                            return 1;
                        }

                        @Override
                        protected void updateValue(Integer value) {
                            //该方法运行于JavaFX Application Thread
                            //value是call方法的返回值
                            //如果需要后台任务更新UI，在这个方法中进行
                            log.info("ScheduledService.Task.updateValue Thread Name=" + Thread.currentThread().getName());
                            log.info("value=" + value);
                        }
                    };
                }

                //调用ScheduledService对象的cancel关闭
            };

            //延迟3秒后开启
            ss.setDelay(Duration.seconds(3));
            //每一秒执行一次，每执行一次都会new一个Task
            ss.setPeriod(Duration.seconds(1));
            ss.start();

        });

        AppUtils.quickInit(primaryStage, "lesson022", root);
        primaryStage.show();
    }

    private void cancelSchedule() {
        if (ss != null) {
            ss.cancel();
        }
    }
}
