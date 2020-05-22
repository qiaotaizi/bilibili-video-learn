package com.jaiz.study.lesson037;

import java.util.Objects;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.beans.Student;
import com.jaiz.study.utils.AppUtils;
import com.jaiz.study.utils.Repo;
import com.jaiz.study.utils.SimplifiedStringConverter;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * ChoiceBox另外一些用法
 * 修改ChoiceBox对象列表的内容
 */
@StartableMeta(title = "lesson037",category = CategoryType.LESSON,
subtitle = "ChoiceBox.Converter")
public class App extends Startable {


    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        ChoiceBox<Student> cb = new ChoiceBox<>();
        TextField tf = new TextField();
        Button button = new Button("修改");

        cb.getItems().addAll(Repo.STUDENTS);

        cb.setConverter(new SimplifiedStringConverter<>() {
            @Override
            public String toString(Student student) {
                //Converter逻辑在ObservableList发生变化时将会触发
                return student.getName();
            }
        });
        cb.setOnAction(
                event -> {

                    Student selected = cb.getSelectionModel().getSelectedItem();
                    if (Objects.isNull(selected)) {
                        tf.setText("");
                        return;
                    }
                    tf.setText(
                            selected.getName()
                    );
                }

        );
        button.setOnAction(event -> {
            String name = tf.getText();
            SingleSelectionModel<Student> selectionModel = cb.getSelectionModel();
            Student selectedStudent = selectionModel.getSelectedItem();
            selectedStudent.setName(name);
            int index = selectionModel.getSelectedIndex();
            cb.getItems().set(index, selectedStudent);
            selectionModel.select(index);
        });

        HBox hBox = new HBox();
        hBox.getChildren().addAll(cb, tf, button);

        cb.getSelectionModel().selectFirst();


        root.getChildren().add(hBox);

        AppUtils.quickInit(primaryStage,  "lesson037", root);
        primaryStage.show();
    }
}
