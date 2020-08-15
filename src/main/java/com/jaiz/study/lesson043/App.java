package com.jaiz.study.lesson043;

import java.time.LocalDate;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.utils.AppUtils;
import com.jaiz.study.utils.Formatters;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;

/**
 * ColorPicker和DatePicker
 */
@Slf4j
@StartableMeta(title = "lesson043", category = CategoryType.LESSON,
        subtitle = "ColorPicker和DatePicker")
public class App extends Startable {


    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        HBox hBox = new HBox();
        ColorPicker cp = new ColorPicker();
        DatePicker dp = new DatePicker();
        Label label = new Label("我的颜色受ColorPicker控制");


        cp.setOnAction(event -> {
            Color c = cp.getValue();
            label.setTextFill(c);
        });

        dp.setOnAction(event -> {
            LocalDate ld = dp.getValue();
            log.info(Formatters.DEFAULT.format(ld));
        });

        //可以通过自定义CellFactory控制格式
        dp.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        this.setText(item.getDayOfMonth() + ",农历XX");
                    }
                };
            }
        });


        dp.setEditable(false);

        hBox.getChildren().addAll(cp, dp, label);

        root.getChildren().add(hBox);

        AppUtils.quickInit(primaryStage, "lesson043", root);
        primaryStage.show();
    }

}
