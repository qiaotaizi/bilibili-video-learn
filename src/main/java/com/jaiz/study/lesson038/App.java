package com.jaiz.study.lesson038;

import com.jaiz.study.beans.Student;
import com.jaiz.study.utils.AppUtils;
import com.jaiz.study.utils.SimplifiedStringConverter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * ComboBox
 */
public class App extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();

        ComboBox<Student> cb=new ComboBox<>();

        ObservableList<Student> data=initData();
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


        AppUtils.quickInitMenuBar(primaryStage,  this.getClass(), root);
    }

    /**
     * 数据初始化
     * @return
     */
    private ObservableList<Student> initData() {
        ObservableList<Student> result= FXCollections.observableArrayList(
                Student.builder().id(1).name("小明").age(18).build(),
                Student.builder().id(2).name("小强").age(19).build(),
                Student.builder().id(3).name("小华").age(17).build()
        );
        return result;
    }
}
