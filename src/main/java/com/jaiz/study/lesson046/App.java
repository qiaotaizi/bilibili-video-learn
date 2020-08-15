package com.jaiz.study.lesson046;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

@StartableMeta(title = "lesson046", category = CategoryType.LESSON,
        subtitle = "ProgressBar与ProgressIndicator")
public class App extends Startable {

    ScheduledService<Double> ss;

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        Button btn = new Button("点我观察进度条变化");
        Button reset = new Button("重置");

        //ProgressBar未知进度时会有一个左右摇摆的效果
        ProgressBar pb = new ProgressBar();
        //显式控制左右摇摆的效果
        pb.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        ProgressIndicator pi = new ProgressIndicator();

        HBox hBox = new HBox();
        hBox.getChildren().addAll(btn, pb, pi, reset);

        root.getChildren().addAll(hBox);

        btn.setOnAction(event -> {
            btn.setDisable(true);
            reset.setDisable(true);
            double initValue = 0;
            pb.setProgress(initValue);
            pi.setProgress(initValue);
            ss = new ScheduledService<Double>() {
                @Override
                protected Task<Double> createTask() {
                    return new Task<Double>() {
                        //call每次方法总是新建线程,
                        // 所以实际上这里建立大量的小任务来推进进度条进度是不合适的
                        @Override
                        protected Double call() throws Exception {
                            return pi.getProgress();
                        }

                        //JavaFX Application Thread
                        @Override
                        protected void updateValue(Double value) {
                            if (value >= 1.0) {
                                ss.cancel();
                                btn.setDisable(false);
                                reset.setDisable(false);
                                return;
                            }
                            super.updateValue(value);
                            double newValue = value + 0.01;
                            pi.setProgress(newValue);
                            pb.setProgress(newValue);

                        }
                    };
                }
            };
            ss.setDelay(Duration.seconds(0));
            ss.setPeriod(Duration.millis(30));
            ss.start();
        });

        reset.setOnAction(event -> {
            pb.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
            pi.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        });

        AppUtils.quickInit(primaryStage, "lesson046", root);
        primaryStage.show();
    }

}
