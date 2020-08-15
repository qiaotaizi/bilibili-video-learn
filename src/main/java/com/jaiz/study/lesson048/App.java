package com.jaiz.study.lesson048;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.beans.Student;
import com.jaiz.study.utils.AppUtils;
import com.jaiz.study.utils.Repo;
import com.jaiz.study.utils.SimplifiedStringConverter;

import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@StartableMeta(title = "lesson048", category = CategoryType.LESSON,
        subtitle = "Spinner")
public class App extends Startable {


    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        VBox vBox = new VBox();

        Spinner<Integer> spinner = new Spinner<>(0, 10, 0, 1);
        spinner.setEditable(false);
        spinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);//有一些可选的样式
        vBox.getChildren().add(spinner);

        var data = Repo.STUDENTS;

        //如果spinner中绑定对象的话，推荐使用这种初始化方式。
        var valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<>(data);
        valueFactory.setConverter(new SimplifiedStringConverter<>() {
            @Override
            public String toString(Student object) {
                return object.getName();
            }
        });
        Spinner<Student> spinner1 = new Spinner<>(valueFactory);
        spinner1.setEditable(false);
        spinner1.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);

        Button btn = new Button("打印当前学生信息");
        btn.setOnAction(event -> {
            log.info("spinner.value = {}", spinner1.getValue());
        });

        vBox.getChildren().add(spinner1);
        vBox.getChildren().add(btn);

        root.getChildren().add(vBox);

        AppUtils.quickInit(primaryStage, "lesson048", root);
        primaryStage.show();
    }

}
