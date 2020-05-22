package com.jaiz.study.lesson038;

import com.jaiz.study.CategoryType;
import com.jaiz.study.Startable;
import com.jaiz.study.StartableMeta;
import com.jaiz.study.beans.Student;
import com.jaiz.study.utils.AppUtils;
import com.jaiz.study.utils.Repo;
import com.jaiz.study.utils.SimplifiedStringConverter;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * ComboBox
 */
@StartableMeta(title = "lesson038",category = CategoryType.LESSON,
subtitle = "ComboBox")
public class App extends Startable {


    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        ComboBox<Student> cb=new ComboBox<>();

        ObservableList<Student> data= Repo.STUDENTS;
        cb.setItems(data);

        cb.setFocusTraversable(false);

        cb.setConverter(new SimplifiedStringConverter<>() {
            @Override
            public String toString(Student student) {
                return student.getName();
            }
        });
        cb.getSelectionModel().selectFirst();

        //开启editable将是的ComboBox具备可编辑功能
        cb.setEditable(true);
        //当没有任何数据可以选择时，下拉将显示Placeholder
        cb.setPlaceholder(new Label("---暂无数据---"));

        root.getChildren().add(cb);


        AppUtils.quickInit(primaryStage, "lesson038", root);
        primaryStage.show();
    }

}
